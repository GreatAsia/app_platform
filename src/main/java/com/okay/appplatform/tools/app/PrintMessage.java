package com.okay.appplatform.tools.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class PrintMessage {

    private final static Logger logger = LoggerFactory.getLogger(PrintMessage.class);


    public static String printMessage(final InputStream input) {

        StringBuffer resultLog = new StringBuffer();
        try {
            Reader reader = new InputStreamReader(input, "UTF-8");
                BufferedReader bf = new BufferedReader(reader);
                String line = null;

            while ((line = bf.readLine()) != null) {
                        if(!"".equals(line) || line.length() != 0){
                            resultLog.append(line).append("\n");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        return resultLog.toString();
    }


}
