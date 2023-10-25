package com.okay.appplatform.impl.ui;

import com.github.cosysoft.device.android.AndroidDevice;
import com.okay.appplatform.domain.report.UiPadCaseList;
import com.okay.appplatform.domain.report.UiPadRunIdList;
import com.okay.appplatform.domain.report.UiPadSerialnoList;
import com.okay.appplatform.domain.ui.DeviceInfo;
import com.okay.appplatform.mapper.ui.*;
import com.okay.appplatform.service.ui.DeviceInfoService;
import com.okay.appplatform.tools.app.RunCaseCallable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.okay.appplatform.tools.GetTime.elapsedTime;
import static com.okay.appplatform.tools.GetTime.getStartTime;
import static com.okay.appplatform.tools.MyThreadPool.submitTask;
import static com.okay.appplatform.tools.MyThreadPool.submitTasks;
import static com.okay.appplatform.tools.app.DeviceInfo.getDeviceOnlineList;


/**
 * @author zhou
 * @date 2020/12/8
 */
@Service("DeviceInfoService")
public class DeviceInfoServiceImpl implements DeviceInfoService {

    private final static Logger logger = LoggerFactory.getLogger(DeviceInfoService.class);
    private static final String PADRUNNER = " com.okay.studentpad_ui.test/android.support.test.runner.AndroidJUnitRunner";
    private static final String ROMRUNNER = " com.okay.autotest.test/android.support.test.runner.AndroidJUnitRunner";
    private static final String MONKEYCLASS = "shell am instrument -w -e packageS packages -e runTime runtime  -e class  com.okay.studentpad_ui.testcase.monkey.MonkeyTest#monkey";
    private static final String RUNUI = "shell am instrument -w -e class ";
    //RunId列表
    private static int id;
    private static int totalDevice;
    private static int passDevice;
    private static int failDevice;
    private static int errorDevice;
    private static Long runIdElapsedTime = 0L;
    private static Long runIdStartTime = 0L;
    private static String runType = "";

    protected static final String PICPATH = "/sdcard/uiautomator/img/.";
    protected static final String TESTLOGPATH = "/sdcard/uiautomator/log/test.log";
    private static final String CRASHLOGPATH = "/sdcard/OKcrash.log";
    //序列号列表
    private static String serialno;
    private static String version;
    private static int totalCase;

    private static int errorCase;
    private static Long serialnoElapsedTime = 0L;
    private static Long seriaLnoStartTime = 0L;
    private static String serialnoPassRate;
    private static String romVersion = "";
    private static String apkVersion = "";
    private static String netWork = "";
    private static String env = "";
    //case列表
    private static String caseName;
    private static String caseDesc;
    private static String caseResult;
    private static String caseElapsedTime;
    private static String errorLog;
    private static String caseModule = "";

    private static int TIMEOUT = 30;
    private static int isFirst = 0;
    private static String modelName = "NA";

    @Resource
    UiPadRunIdListMapper uiPadRunIdListMapper;
    @Resource
    PadAutoCaseMapper padAutoCaseMapper;
    @Resource
    RomAutoCaseMapper romAutoCaseMapper;
    @Resource
    UiPadCaseListMapper uiPadCaseListMapper;
    @Resource
    UiPadSerialnoListMapper uiPadSerialnoListMapper;


    @Resource
    DeviceInfoMapper deviceInfoMapper;
    private String deviceElapseTime = "";
    private String deviceStartTime = "";
    private String devicePassRate = "";
    private String moduleName = "";
    private String methodName = "";
    @Value("${web.upload-path}")
    private String resourcePath;

    @Override
    public void insertDevice(DeviceInfo deviceInfo) {
        deviceInfoMapper.insertDevice(deviceInfo);
    }

    @Override
    public void insertDevices(List<DeviceInfo> deviceInfoList) {
        deviceInfoMapper.insertDevices(deviceInfoList);
    }

    @Override
    public void updateDevice(DeviceInfo deviceInfo) {
        deviceInfoMapper.updateDevice(deviceInfo);
    }

    @Override
    public void deleteAllDevice() {
        deviceInfoMapper.deleteAllDevice();
    }

    @Override
    public void deleteDevice(int id) {
        deviceInfoMapper.deleteDevice(id);
    }

    @Override
    public synchronized void runUiTest(List<String> ids, String runFrom) {

        List<AndroidDevice> deviceList = getDeviceOnlineList();
        if (deviceList.size() != 0) {
            String caseName = "";
            String command = "";
            totalDevice = 0;
            passDevice = 0;
            failDevice = 0;
            runIdStartTime = System.currentTimeMillis();
            totalCase = ids.size();
            totalDevice = deviceList.size();

            List<UiPadSerialnoList> serialnoList = new ArrayList<>();
            //先生成一个新的runId，然后后面更新
            UiPadRunIdList uiPadRunIdList = new UiPadRunIdList();
            uiPadRunIdList.setTotalDevice(totalDevice);
            uiPadRunIdList.setPassDevice(1);
            uiPadRunIdList.setFailDevice(1);
            uiPadRunIdList.setErrorDevice(1);
            uiPadRunIdList.setElapsedTime("");
            uiPadRunIdList.setStartTime("");
            uiPadRunIdList.setPassRate("");
            uiPadRunIdList.setRunType("");
            uiPadRunIdListMapper.insertUiPadRunIdList(uiPadRunIdList);
            Integer runId = uiPadRunIdListMapper.getLastId();

            for (String id : ids) {

                //存储每条用例的测试记录
                int caseId = Integer.parseInt(id);
                if ("app".equals(runFrom)) {
                    caseName = padAutoCaseMapper.findCaseById(caseId).getCaseDesc();
                    command = RUNUI + caseName + PADRUNNER;
                }
                if ("rom".equals(runFrom)) {
                    caseName = romAutoCaseMapper.findCaseById(caseId).getCaseDesc();
                    command = RUNUI + caseName + ROMRUNNER;
                }

                List<RunCaseCallable> callableList = new ArrayList<>();

                for (AndroidDevice a : deviceList) {
                    RunCaseCallable runCaseCallable = new RunCaseCallable(command, a.getSerialNumber());
                    callableList.add(runCaseCallable);
                }
                List<Future<String>> futureList = submitTasks(callableList);
                for (Future<String> future : futureList) {
                    String result = "";
                    int passCase = 0;
                    int failCase = 0;
                    String serialno = "";
                    String logInfo = "";

                    try {
                        try {
                            result = future.get(TIMEOUT, TimeUnit.MINUTES);
                        } catch (TimeoutException e) {
                            logger.error("获取结果超时=={}",e.getMessage());
                            e.printStackTrace();
                        }
                        String[] resultInfo = result.split("==");

                        if (resultInfo.length > 1) {
                            serialno = resultInfo[0];
                            logInfo = resultInfo[1];
                        }
                        UiPadCaseList uiPadCaseList = new UiPadCaseList();
                        uiPadCaseList.setRunId(runId);
                        uiPadCaseList.setSerialno(serialno);
                        String uploadFilePath = runId + "/" + serialno + "/";
                        buildTestResult(uiPadCaseList, logInfo, uploadFilePath);
                        //上传Case数据
                        uiPadCaseListMapper.insertUiPadCaseList(uiPadCaseList);
                        boolean isCreate = true;
                        for (UiPadSerialnoList list : serialnoList) {

                            if (serialno.equals(list.getSerialno())) {

                                if ("true".equals(uiPadCaseList.getResult())) {
                                    list.setPassCase(list.getPassCase() + 1);
                                } else {
                                    list.setFailCase(list.getFailCase() + 1);
                                }

                                Long elapsedTime = (list.getElapsedTimeForLong() + uiPadCaseList.getElapsedTimeForLong());
                                list.setElapsedTime(elapsedTime(elapsedTime));
                                list.setElapsedTimeForLong(elapsedTime);
                                list.setPassRate(String.format("%.2f", (((float) list.getPassCase() / totalCase) * 100)) + "%");
                                isCreate = false;
                            }

                        }
                        if (isCreate) {

                            if ("true".equals(uiPadCaseList.getResult())) {
                                passCase++;
                            } else {
                                failCase++;
                            }

                            UiPadSerialnoList uiPadSerialnoList = new UiPadSerialnoList();
                            //设备列表测试结果统计
                            uiPadSerialnoList.setRunId(runId);
                            uiPadSerialnoList.setSerialno(serialno);
                            uiPadSerialnoList.setVersion(modelName);
                            uiPadSerialnoList.setTotalCase(totalCase);
                            uiPadSerialnoList.setPassCase(passCase);
                            uiPadSerialnoList.setFailCase(failCase);
                            uiPadSerialnoList.setErrorCase(errorCase);
                            uiPadSerialnoList.setStartTime(uiPadCaseList.getStartTime());
                            uiPadSerialnoList.setElapsedTime(uiPadCaseList.getElapsedTime());
                            uiPadSerialnoList.setElapsedTimeForLong(uiPadCaseList.getElapsedTimeForLong());
                            uiPadSerialnoList.setPassRate(String.format("%.2f", (((float) passCase / totalCase) * 100)) + "%");
                            uiPadSerialnoList.setRomVersion(romVersion);
                            uiPadSerialnoList.setApkVersion(apkVersion);
                            uiPadSerialnoList.setEnv(env);
                            uiPadSerialnoList.setNetWork(netWork);
                            serialnoList.add(uiPadSerialnoList);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }


            }
            uiPadSerialnoListMapper.insertUiPadSerialnoS(serialnoList);

            for (UiPadSerialnoList list : serialnoList) {

                if (list.getFailCase() > 0) {
                    failDevice++;
                } else {
                    passDevice++;
                }
            }

            String elapsed = elapsedTime(System.currentTimeMillis() - runIdStartTime);
            String rate = String.format("%.2f", (((float) passDevice / totalDevice) * 100)) + "%";
            //获取runId相关的信息
            UiPadRunIdList runIdList = new UiPadRunIdList();
            runIdList.setId(runId);
            runIdList.setTotalDevice(totalDevice);
            runIdList.setPassDevice(passDevice);
            runIdList.setFailDevice(failDevice);
            runIdList.setErrorDevice(errorDevice);
            runIdList.setStartTime(getStartTime(runIdStartTime));
            runIdList.setElapsedTime(elapsed);
            runIdList.setPassRate(rate);
            runIdList.setRunType(runType);
            uiPadRunIdListMapper.updateUiPadRunIdList(runIdList);
            logger.info("运行完成");
        }

    }

    @Override
    public void runShell(String cmd) {

        List<AndroidDevice> deviceList = getDeviceOnlineList();
        if (deviceList.size() != 0) {
            List<RunCaseCallable> callableList = new ArrayList<>();
            for (AndroidDevice a : deviceList) {
                RunCaseCallable runCaseCallable = new RunCaseCallable(cmd, a.getSerialNumber());
                callableList.add(runCaseCallable);
            }
            submitTasks(callableList);
        }


    }


    @Override
    public DeviceInfo findById(int id) {
        return deviceInfoMapper.findById(id);
    }

    @Override
    public DeviceInfo findBySerialno(String serialno) {
        return deviceInfoMapper.findBySerialno(serialno);
    }

    @Override
    public List<DeviceInfo> findByFromAndStatus(String run_from, String status) {
        return deviceInfoMapper.findByFromAndStatus(run_from, status);
    }

    @Override
    public List<DeviceInfo> findList() {
        return deviceInfoMapper.findList();
    }


    /**
     * @param uiPadCaseList
     * @param logInfo
     * @param uploadFilePath
     * @return
     */
    public UiPadCaseList buildTestResult(UiPadCaseList uiPadCaseList, String logInfo, String uploadFilePath) {

        String[] content = logInfo.split("\n");
        List<String> lineContents = Arrays.asList(content);
        String errorReason = "";
        String title = "NA";
        Long startTime = 0L;
        Long endTime = 0L;
        boolean isPass = true;

        for (String lineContent : lineContents) {
            lineContent.trim();
            if (lineContent.length() == 0) {
                continue;
            }
            try {
                if (lineContent.contains("startTime=")) {
                    startTime = Long.parseLong(lineContent.split("=")[1].trim());
                    if (isFirst == 0) {
                        seriaLnoStartTime = startTime;
                    } else {
                        if (seriaLnoStartTime == 0) {
                            seriaLnoStartTime = startTime;
                        }
                    }
                    continue;
                }
                if (lineContent.contains("methodName=")) {
                    methodName = lineContent.split("=")[1].trim();
                    continue;
                }
                if (lineContent.contains("runType=")) {
                    runType = lineContent.split("=")[1].trim();
                    continue;
                }
                if (lineContent.contains("endTime=")) {
                    endTime = Long.parseLong(lineContent.split("=")[1].trim());
                    continue;
                }

                if (lineContent.contains("title=")) {
                    title = lineContent.split("=")[1];
                    continue;
                }

                if (lineContent.contains("systemVersion=")) {
                    romVersion = lineContent.split("=")[1];
                    continue;
                }
                if (lineContent.contains("apkVersion=")) {
                    apkVersion = lineContent.split("=")[1];
                    continue;
                }
                if (lineContent.contains("wifiName=")) {
                    netWork = lineContent.split("=")[1];
                    continue;
                }
                if (lineContent.contains("environmentName=")) {
                    env = lineContent.split("=")[1];
                    continue;
                }

                if (lineContent.contains("model=")) {
                    modelName = lineContent.split("=")[1];
                    continue;
                }

                if (lineContent.contains("module=")) {
                    caseModule = lineContent.split("=")[1];
                    continue;
                }

                if (lineContent.contains("OK (1 test)")) {
                    isPass = true;
                    continue;
                }

                if (lineContent.contains("AssertionFailedError")) {
                    isPass = false;
                    continue;
                }
                if (lineContent.contains("Process crashed")) {
                    isPass = false;
                    continue;
                }

                if (lineContent.contains("Error in")) {
                    isPass = false;
                    continue;
                }

                if (lineContent.contains("UiObjectNotFoundException")) {
                    isPass = false;
                    continue;
                }

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("获取信息错误==" + e.getLocalizedMessage());
            }
        }

        if (!isPass) {
            errorReason = logInfo;
        }
        isFirst++;
        Long elapsedTime = (endTime - startTime);

        uiPadCaseList.setCaseName(methodName);
        uiPadCaseList.setStartTime(getStartTime(startTime));
        uiPadCaseList.setElapsedTime(elapsedTime(elapsedTime));
        uiPadCaseList.setElapsedTimeForLong(elapsedTime);
        uiPadCaseList.setCaseDesc(title);
        uiPadCaseList.setCaseModule(moduleName);
        uiPadCaseList.setResult(isPass + "");
        uiPadCaseList.setCaseModule(caseModule);

        //读取截图文件
        if (isPass == false) {

            String picPath = resourcePath + "/file/" + uploadFilePath + methodName;
            String command = "-s " + uiPadCaseList.getSerialno() + " pull " + PICPATH + " " + picPath;
            logger.info("pull picture == {}", command);
            File picPathFile = new File(picPath);
            if (!picPathFile.exists()) {
                picPathFile.mkdirs();
            }
            try {
                RunCaseCallable runCaseCallable = new RunCaseCallable(command, uiPadCaseList.getSerialno());
                //创建一个 ThreadPoolExecutor 对象
                Future<String> future = submitTask(runCaseCallable);
                future.get(TIMEOUT, TimeUnit.MINUTES);

            } catch (Exception var5) {
                throw new RuntimeException(var5);
            }

            String uploadPicturePath = "";
            StringBuffer filePath = new StringBuffer();

            if (picPathFile.exists()) {
                File pictureDirs[] = picPathFile.listFiles();
                if (pictureDirs.length != 0) {
                    for (File f : pictureDirs) {
                        String pictureName = f.getName();
                        uploadPicturePath = uploadFilePath + methodName + "/" + pictureName;
                        filePath.append(uploadPicturePath).append(";");
                    }
                }
            }
            uiPadCaseList.setPicturePath(filePath.toString());
        }

        //读取测试日志
        if (isPass == false) {

            String logPath = resourcePath + "/file/" + uploadFilePath + methodName;
            String command = " pull " + TESTLOGPATH + " " + logPath;
            File logPathFile = new File(logPath);
            if (!logPathFile.exists()) {
                logPathFile.mkdirs();
            }
            try {
                RunCaseCallable runCaseCallable = new RunCaseCallable(command, uiPadCaseList.getSerialno());

                Future<String> future = submitTask(runCaseCallable);
                Thread.sleep(2000L);
            } catch (InterruptedException var5) {
                throw new RuntimeException(var5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String testLogPath = "";
            File testLogFile = new File(logPath);
            if (testLogFile.exists()) {

                String testLogName = "test.log";
                testLogPath = uploadFilePath + methodName + "/" + testLogName;
            }

            uiPadCaseList.setTestLogPath(testLogPath);
        }


        //读取crash文件
        String exceptionLog = "";
        try {
            String command = " shell " + "cat " + CRASHLOGPATH;
            RunCaseCallable runCaseCallable = new RunCaseCallable(command, uiPadCaseList.getSerialno());
            Future<String> future = submitTask(runCaseCallable);
            String[] result = future.get(1, TimeUnit.MINUTES).split("==");
            if(result.length >1){
                exceptionLog = result[1];
                if (!exceptionLog.contains("No such file or directory")) {
                        exceptionLog = "\n开发日志:\n" + exceptionLog;
                        uiPadCaseList.setResult(false + "");
                }
            }
        } catch (Exception var5) {
            throw new RuntimeException(var5);
        }

        if (errorReason.equals("") || errorReason.length() == 0) {
            errorReason = "";
        } else {
            errorReason = "\n测试日志:\n" + errorReason;
        }

        uiPadCaseList.setErrorLog(exceptionLog + errorReason);

        return uiPadCaseList;

    }

}
