package com.okay.appplatform.controller.ui;


import com.alibaba.fastjson.JSONObject;
import com.github.cosysoft.device.android.AndroidDevice;
import com.okay.appplatform.domain.RetResponse;
import com.okay.appplatform.domain.RetResult;
import com.okay.appplatform.domain.ui.PadAccount;
import com.okay.appplatform.service.ui.DeviceInfoService;
import com.okay.appplatform.service.ui.PadAccountService;
import com.okay.appplatform.tools.app.RunShellCallable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static com.okay.appplatform.tools.EditFile.createFile;
import static com.okay.appplatform.tools.MyThreadPool.submitTasks;
import static com.okay.appplatform.tools.app.DeviceInfo.getDeviceOnlineList;


@Api(description = "Pad自动化账号接口")
@Controller
@RequestMapping(value = "/uiPad/account")
public class UiPadAccountController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final static Long TIMEOUT = 1L;

    @Resource
    PadAccountService padAccountService;
    @Resource
    DeviceInfoService deviceInfoService;


    @ApiOperation(value = "获取PAD账号列表", notes = "获取PAD账号列表")
    @GetMapping(value = "/getlist")
    @ResponseBody
    public String getPadAccountlist(@Validated String envName) {

        List<PadAccount> padAccountList = padAccountService.findAccountByEnvName(envName);

        return JSONObject.toJSONString(padAccountList);
    }


    @ApiOperation(value = "批量添加PAD账号", notes = "批量添加PAD账号")
    @GetMapping(value = "/inserts")
    @ResponseBody
    public  RetResult<Object> insertPadAccounts(@Validated String envName, String studentList) {

        List<PadAccount> padAccountList = new ArrayList<>();
        String[] accountList = studentList.split(";");
        for (String acc : accountList) {
            PadAccount padAccount = new PadAccount();
            padAccount.setAccount(acc);
            padAccount.setEnvName(envName);
            padAccountList.add(padAccount);
        }
        padAccountService.deleteAllAccount(envName);
        padAccountService.insertAccounts(padAccountList);
        createFile("students_" + envName, studentList);

        return RetResponse.makeOKRsp();

    }

    @ApiOperation(value = "推送PAD账号", notes = "推送PAD账号")
    @GetMapping(value = "/push")
    @ResponseBody
    public  RetResult<Object> pushPadAccounts(@Validated String envName) {


        List<AndroidDevice> deviceList = getDeviceOnlineList();
        if(deviceList.size() == 0){
            RetResponse.makeErrRsp("没有设备连接");
        }

        File studentList = new File(System.getProperty("user.dir") + File.separator + "students_" + envName + ".txt");
        File devicelist = new File(System.getProperty("user.dir") + File.separator + "devicelist.txt");
        if (!studentList.exists()) {
            return RetResponse.makeErrRsp("students_" + envName + ".txt不存在");
        }
        if (!devicelist.exists()) {
            return RetResponse.makeErrRsp("devicelist.txt不存在");
        }

        List<RunShellCallable> callableList = new ArrayList<>();

        for (AndroidDevice a : deviceList) {
            String commandStudent = " push " + studentList + " /sdcard";
            String commandDevice = " push " + devicelist + " /sdcard";

            RunShellCallable studentCallable = new RunShellCallable(commandStudent, a);
            RunShellCallable deviceCallable = new RunShellCallable(commandDevice, a);
            callableList.add(studentCallable);
            callableList.add(deviceCallable);
        }

        List<Future<String>> futureList = submitTasks(callableList);
        List<String> error = new ArrayList<>();
        for (Future<String> future : futureList) {
            String result = "";
            try {
                try {
                    result = future.get(TIMEOUT, TimeUnit.MINUTES);
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
                if (result.contains("error") || result.contains("fail") || result.contains("Can't find")) {
                    error.add(result);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        if (error.size() == 0) {
            return RetResponse.makeOKRsp();
        } else {
            logger.error("[installError]==" + JSONObject.toJSONString(error));
            return RetResponse.makeErrRsp(error.toString());
        }

    }


    @ApiOperation(value = "批量更新PAD账号", notes = "批量更新PAD账号")
    @PostMapping(value = "/updates")
    @ResponseBody
    public RetResult<Object> updatePadAccounts(@Validated @RequestBody List<PadAccount> padAccount) {

        padAccountService.updateAccounts(padAccount);
        return RetResponse.makeOKRsp();
    }

    @ApiOperation(value = "删除PAD账号", notes = "删除PAD账号")
    @PostMapping(value = "/delete")
    @ResponseBody
    public RetResult<Object> deleteAccount(@Validated int id) {

        padAccountService.deleteAccount(id);
        return RetResponse.makeOKRsp();
    }


}
