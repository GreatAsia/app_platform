package com.okay.appplatform.tools;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


@Component
public class EditFile {
    private final static Logger logger = LoggerFactory.getLogger(EditFile.class);

    public static boolean deleteFile(File file) {
        boolean result = file.delete();
        int count = 0;
        while (!result && count++ < 10) {
            System.gc();
            result = file.delete();
        }
        if (result = true) {
            logger.info("delete success");
        } else {
            logger.error(file + " delete fail");
        }

        return result;
    }


    /**
     * 创建文件
     *
     * @param fileName
     * @param detail
     */
    public static  void createFile(String fileName, String detail) {

        File file = new File(System.getProperty("user.dir") + File.separator + fileName + ".txt");
        FileWriter fileWriter = null;

        try {
            if (file.exists()) {
                deleteFile(file);

            } else {
                file.createNewFile();
            }
            String[] infoList = detail.split(";");

            fileWriter = new FileWriter(file, true);
            for (String info : infoList) {
                fileWriter.write(info);
                fileWriter.write("\r\n");
                fileWriter.flush();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
