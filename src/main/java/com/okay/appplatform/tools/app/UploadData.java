package com.okay.appplatform.tools.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author zhou
 * @date 2020/12/16
 */
public class UploadData {

    private final static Logger logger = LoggerFactory.getLogger(UploadData.class);
    private int runId;
    private List<Future<String>> futureList;

    public UploadData(int runId, List<Future<String>> futureList) {
        this.runId = runId;
        this.futureList = futureList;
    }

    public void submit() {

        for (Future<String> future : futureList) {
            String result = "";
            try {
                result = future.get();


                logger.info("运行结果== {}", result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }


    }


}
