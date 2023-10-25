package com.okay.appplatform.tools.app;

import com.github.cosysoft.device.shell.AndroidSdk;
import com.github.cosysoft.device.shell.ShellCommand;
import com.github.cosysoft.device.shell.ShellCommandException;
import org.apache.commons.exec.CommandLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhou
 * @date 2020/10/29
 */
public class Shell {

    private static final Logger logger = LoggerFactory.getLogger(Shell.class);

    public CommandLine adbCommand(String... args) {
        CommandLine command = new CommandLine(AndroidSdk.adb());
        String[] var3 = args;
        int var4 = args.length;
        for (int var5 = 0; var5 < var4; ++var5) {
            String arg = var3[var5];
            command.addArgument(arg, false);
        }

        return command;
    }


    public String executeCommandQuietly(CommandLine command, long timeout) {
        try {
            return ShellCommand.exec(command, timeout);
        } catch (ShellCommandException var6) {
            String logMessage = String.format("Could not execute command: %s", command);
            logger.warn(logMessage, var6);
            return "";
        }
    }


}
