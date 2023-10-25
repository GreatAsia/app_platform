package com.okay.appplatform.tools.app;


import org.apache.commons.exec.CommandLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.Callable;


/**
 * @author zhou
 * @date 2020/8/1
 */
public class InstallApkCallable implements Callable<String> {

    private File file;
    private String serialno;
    private static final Integer COMMAND_TIMEOUT = 20000;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public InstallApkCallable(File file, String serialno) {
        this.file = file;
        this.serialno = serialno;
    }

    @Override
    public String call() throws Exception {

        CommandLine command = new Shell().adbCommand("-s", serialno, "install", "-t", "-r", file.getAbsolutePath());
        String out = new Shell().executeCommandQuietly(command, (long) (COMMAND_TIMEOUT * 6));

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException var5) {
            throw new RuntimeException(var5);
        }

        if (out.contains("Failure")) {
            String[] fail = out.split("\\n");
            out = fail[fail.length - 1];
            logger.error(serialno + "==" + out);
            return serialno + ":" + out;
        }
        logger.info(serialno + " install success");
        return "Success";
    }
}
