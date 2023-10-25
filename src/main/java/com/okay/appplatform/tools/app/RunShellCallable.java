package com.okay.appplatform.tools.app;


import com.github.cosysoft.device.android.AndroidDevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;


/**
 * @author zhou
 * @date 2020/8/1
 */
public class RunShellCallable implements Callable<String> {

    private String command = "";
    private AndroidDevice device;
    private final static Logger logger = LoggerFactory.getLogger(RunCaseCallable.class);

    public RunShellCallable(String command, AndroidDevice androidDevice) {
        this.command = command;
        this.device = androidDevice;
    }

    private void before(){
        logger.info("start run " + device.getSerialNumber());
    }

    private void after(){

        logger.info("end run " + device.getSerialNumber());
    }


    @Override
    public String call() throws Exception {
        before();
        String result = device.runAdbCommand(command);
        after();
        return device.getSerialNumber() + "==" + result;
    }


}
