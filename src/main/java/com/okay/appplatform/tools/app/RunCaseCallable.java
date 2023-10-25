package com.okay.appplatform.tools.app;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

import static com.okay.appplatform.tools.app.PrintMessage.printMessage;


/**
 * @author zhou
 * @date 2020/8/1
 */
public class RunCaseCallable implements Callable<String> {

    private final static Logger logger = LoggerFactory.getLogger(RunCaseCallable.class);

    private String command = "";
    private String serialNumber = "";


    public RunCaseCallable(String command, String serialNumber) {
        this.command = command;
        this.serialNumber = serialNumber;

    }



    private void before(){
        logger.info("start run " + serialNumber);
    }

    private void after(){

        logger.info("end run " + serialNumber);
    }


    @Override
    public String call() throws Exception {
         before();
        final Process process;
        String cmd = "adb -s " + serialNumber + " " + command;
        process = Runtime.getRuntime().exec(cmd);
        String normal = printMessage(process.getInputStream());
        String error = printMessage(process.getErrorStream());
        int waitfor = process.waitFor();
        String result = "";
        if (!normal.equals("") || normal.length() != 0) {
            result = normal;
        } else {
            result = error;
        }

       after();

        return serialNumber + "==" + result;
    }


}
