package com.okay.appplatform.controller.ui;


import com.alibaba.fastjson.JSONObject;
import com.github.cosysoft.device.android.AndroidDevice;
import com.okay.appplatform.domain.RetResponse;
import com.okay.appplatform.domain.RetResult;
import com.okay.appplatform.domain.ui.DeviceInfo;
import com.okay.appplatform.service.ui.DeviceInfoService;
import com.okay.appplatform.tools.app.InstallApkCallable;
import com.okay.appplatform.tools.app.RunShellCallable;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static com.okay.appplatform.tools.EditFile.createFile;
import static com.okay.appplatform.tools.MyThreadPool.submitTasks;
import static com.okay.appplatform.tools.app.DeviceInfo.getDeviceOnlineList;


@RequestMapping(value = "/device")
@Controller
public class DeviceController {


    private final static Logger logger = LoggerFactory.getLogger(DeviceController.class);
    private static final String PADRUNNER = " com.okay.studentpad_ui.test/android.support.test.runner.AndroidJUnitRunner";
    private static final String MONKEYCLASS = "shell am instrument -w -e packageS packages -e runTime runtime  -e class  com.okay.studentpad_ui.testcase.monkey.MonkeyTest#monkey";
    private static String path = System.getProperty("user.dir") + File.separator;
    private static final Long TIMEOUT = 5L;

    @Resource
    DeviceInfoService deviceInfoService;



    @RequestMapping(value = "/getlist", method = RequestMethod.POST)
    @ResponseBody
    public  RetResult<Object> deviceList() {

        List<AndroidDevice> deviceList = getDeviceOnlineList();
        if (deviceList.size() == 0) {
            return RetResponse.makeErrRsp("没有设备连接");
        }
        deviceInfoService.deleteAllDevice();
        StringBuffer deviceListFile = new StringBuffer();
        List<Map> mapList = new ArrayList<>();
        Map map = new HashedMap();
        List<DeviceInfo> deviceInfoList = new ArrayList<>();
        for (AndroidDevice a : deviceList) {

            map.put(a.getDevice().getSerialNumber(), a.getDevice().getState().toString());
                mapList.add(map);

                DeviceInfo deviceInfo = new DeviceInfo();
                deviceInfo.setSerialno(a.getDevice().getSerialNumber());
                deviceInfo.setStatus(a.getDevice().getState().toString());
                deviceInfo.setRun_from("usb");
                deviceInfoList.add(deviceInfo);

            deviceListFile.append(a.getDevice().getSerialNumber());
            deviceListFile.append(";");

        }
        deviceInfoService.insertDevices(deviceInfoList);
        createFile("devicelist", deviceListFile.toString());

        return RetResponse.makeOKRsp(JSONObject.toJSON(mapList));

    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public RetResult<Object> insert(DeviceInfo deviceInfo) {
        deviceInfoService.insertDevice(deviceInfo);
        return RetResponse.makeOKRsp();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public RetResult<Object> update(DeviceInfo deviceInfo) {
        deviceInfoService.updateDevice(deviceInfo);
        return RetResponse.makeOKRsp();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @ResponseBody
    public RetResult<Object> delete(int id) {
        deviceInfoService.deleteDevice(id);
        return RetResponse.makeOKRsp();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    @ResponseBody
    public String findById(int id) {
        DeviceInfo deviceInfo = deviceInfoService.findById(id);
        return JSONObject.toJSONString(deviceInfo);
    }

    @RequestMapping(value = "/findBySerialno", method = RequestMethod.GET)
    @ResponseBody
    public String findBySerialno(String serialno) {
        DeviceInfo deviceInfo = deviceInfoService.findBySerialno(serialno);
        return JSONObject.toJSONString(deviceInfo);
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public String list() {
        List<DeviceInfo> deviceInfo = deviceInfoService.findList();
        return JSONObject.toJSONString(deviceInfo);
    }


    @RequestMapping(value = "/run/local", method = RequestMethod.GET)
    @ResponseBody
    public RetResult<Object> runUiTest(int caseId, String runFrom) {

        List<String> idList = new ArrayList<>();
        idList.add(caseId + "");

        deviceInfoService.runUiTest(idList, runFrom);
        return RetResponse.makeOKRsp();


    }

    @RequestMapping(value = "/run/local/more", method = RequestMethod.GET)
    @ResponseBody
    public RetResult<Object> runUiTestMore(String caseIds, String runFrom) {

        String[] caseArr = caseIds.split(",");
        List<String> idList = Arrays.asList(caseArr);
        deviceInfoService.runUiTest(idList, runFrom);

        return RetResponse.makeOKRsp();


    }


    @RequestMapping(value = "/apk/install", method = RequestMethod.GET)
    @ResponseBody
    public  RetResult<Object> installApk(String apkName) {

        if (apkName == null || apkName.length() == 0) {
            return RetResponse.makeErrRsp("apkName is null");
        }
        List<AndroidDevice> deviceList = getDeviceOnlineList();
        if (deviceList.size() == 0) {
            return RetResponse.makeErrRsp("没有设备连接");
        }
        File apkFile = new File(path);
        if ("local".equals(apkName)) {
            File local[] = apkFile.listFiles();
            for (File file : local) {
                if (file.getName().endsWith("apk")) {
                    apkFile = file;
                    break;
                }

            }
        } else {
            apkFile = new File(path + apkName);
        }
        if (!apkFile.exists()) {
            logger.error("apkFile error== {}", apkFile.getAbsolutePath());
            return RetResponse.makeErrRsp(apkName + "不存在");
        }
        List<InstallApkCallable> callableList = new ArrayList<>();
        for (AndroidDevice a : deviceList) {
            InstallApkCallable installApkCallable = new InstallApkCallable(apkFile, a.getSerialNumber());
            callableList.add(installApkCallable);
        }
        List<Future<String>> futureList = submitTasks(callableList);
        List<String> error = new ArrayList<>();
        for (Future<String> future : futureList) {
            String result = "";
            try {
                result = future.get(TIMEOUT, TimeUnit.MINUTES);
                if (!result.equals("Success")) {
                    error.add(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (error.size() == 0) {
            logger.info("[文件删除]==" + apkFile.delete());
            return RetResponse.makeOKRsp();
        } else {
            logger.error("[installError]==" + JSONObject.toJSONString(error));
            logger.info("[文件删除]==" + apkFile.delete());
            return RetResponse.makeErrRsp(error.toString());
        }


    }


    @RequestMapping(value = "/run/shell", method = RequestMethod.POST)
    @ResponseBody
    public  RetResult<Object> runshell(@RequestBody Map<String,Object> param) {
        String cmd = (String)param.get("cmd");
        List<AndroidDevice> deviceList = getDeviceOnlineList();
        if (deviceList.size() == 0) {
            return RetResponse.makeErrRsp("没有设备连接");
        }
        List<RunShellCallable> callableList = new ArrayList<>();
        logger.info("cmd=={}", cmd);
        for (AndroidDevice a : deviceList) {
                RunShellCallable runShellCallable = new RunShellCallable(cmd, a);
                callableList.add(runShellCallable);
        }
        List<Future<String>> futureList = submitTasks(callableList);
        List<String> error = new ArrayList<>();
        for (Future<String> future : futureList) {
            String result = "";
            try {
                    result = future.get(TIMEOUT, TimeUnit.MINUTES);
                if (result.contains("error") || result.contains("fail") || result.contains("Can't find")) {
                    error.add(result);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (error.size() == 0) {
            return RetResponse.makeOKRsp();
        } else {
            logger.error("[runError]==" + JSONObject.toJSONString(error));
            return RetResponse.makeErrRsp(error.toString());
        }

    }


    @ApiOperation(value = "运行monkey", notes = "运行monkey")
    @GetMapping(value = "/monkey/run")
    @ResponseBody
    public RetResult<Object> runMonkey(@Validated String packageS, String runTime) {

        if(packageS == null ||packageS.equals("")){
            packageS = "null";
        }
        String cmd = MONKEYCLASS.replace("packages", packageS).replace("runtime", runTime) + PADRUNNER;
        logger.info("cmd== {}", cmd);
        deviceInfoService.runShell(cmd);

        return RetResponse.makeOKRsp();

    }


    @PostMapping("/filesupload")
    @ResponseBody
    public RetResult<Object> uploadFiles(@RequestParam(value = "files", required = false) MultipartFile file) {

        String fileName = file.getOriginalFilename();
        String filePath = System.getProperty("user.dir");
        File apkFile = new File(filePath + File.separator + fileName);
        if (apkFile.exists()) {
            apkFile.delete();
        }
        if (!file.isEmpty()) {
            BufferedOutputStream out = null;
            try {
                out = new BufferedOutputStream(new FileOutputStream(apkFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return RetResponse.makeErrRsp("文件上传失败==" + e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                return RetResponse.makeErrRsp("文件上传失败==" + e.getMessage());
            }

        } else {
            return RetResponse.makeErrRsp("文件内容为空==" + fileName);
        }
        return RetResponse.makeOKRsp("上传成功==" + fileName);

    }


}
