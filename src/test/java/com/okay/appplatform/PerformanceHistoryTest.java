package com.okay.appplatform;



import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.okay.appplatform.domain.report.PerformanceHistory;
import com.okay.appplatform.service.performance.ReportService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PerformanceHistoryTest {

    private static final Logger logger = LoggerFactory.getLogger(PerformanceHistoryTest.class);

    @Resource
    ReportService reportService;


    @Test
    public void findReport() {
        PerformanceHistory performanceHistory = reportService.findReport(1);
        logger.info("report:" + JSONObject.toJSONString(performanceHistory));
        Assert.assertTrue("Fail:id为空", 1 == performanceHistory.getId());

    }

    @Test
    public void InsertReport() {
        PerformanceHistory performanceHistory = new PerformanceHistory();
        performanceHistory.setName("ROM");
        performanceHistory.setRunId(1);
        performanceHistory.setType("内存");
        performanceHistory.setPreSize(90);
        performanceHistory.setUnit("MB");
        performanceHistory.setSerialno("W170600670");
        performanceHistory.setResult("PASS");
        performanceHistory.setRunTime("2018:12:19 15:33:01");
        logger.info("InsertReport:" + JSONObject.toJSONString(performanceHistory));
        reportService.insertReport(performanceHistory);
    }


    @Test
    public void UpdateReport() {
        PerformanceHistory performanceHistory = new PerformanceHistory();
        performanceHistory.setId(1);
        performanceHistory.setType("内存");
        performanceHistory.setName("app");
        performanceHistory.setRunId(1);
        performanceHistory.setPreSize(50);
        performanceHistory.setUnit("MB");
        performanceHistory.setResult("FAIL");
        performanceHistory.setSerialno("W170600662");
        performanceHistory.setRunTime("2018:11:08 15:33:01");
        logger.info("UpdateReport:" + JSONObject.toJSONString(performanceHistory));
        reportService.updateReport(performanceHistory);
    }


    @Test
    public void DeleteReport() {

        reportService.deleteReport(1);
    }

    @Test
    public void findReportByRunId() {
        List<PerformanceHistory> performanceHistoryList = reportService.findReportByRunId(1);
        PerformanceHistory performanceHistory = performanceHistoryList.get(0);
        logger.info("reportListsize:" + JSONObject.toJSONString(performanceHistoryList));
        logger.info("findReportByRunId:" + JSONObject.toJSONString(performanceHistory));

    }

    @Test
    public void getLastRunId() {
        Integer runid = reportService.getLastRunId();
        if (runid == null) {
            System.out.println("result=true");
        }
        logger.info("getLastRunId:" + runid);
    }


    @Test
    public void findRunIdList() {
        List<PerformanceHistory> performanceHistoryList = reportService.findRunIdList();
        for (PerformanceHistory performanceHistory : performanceHistoryList) {
            logger.info("findRunIdList:" + performanceHistory.getRunId());

        }
    }

    @Test
    public void finRunIdListReport() {
        PageInfo reports = reportService.findRunIdList(1, 10);

        logger.info("reports--" + JSONObject.toJSONString(reports));
    }


    @Test
    public void findSerialnoList() {
        PageInfo serialnoList = reportService.findSerialnoList(1, 10);
        logger.info("serialnoList:" + JSONObject.toJSONString(serialnoList));

    }


    @Test
    public void findReportBySerialno() {
        PageInfo serialnoList = reportService.findReportBySerialno(1, 10, "W170600665");
        for (Object serialno : serialnoList.getList()) {
            logger.info("findReportBySerialno:" + JSONObject.toJSONString(serialno));

        }
    }


    @Test
    public void deleteReportBySerialno() {

        reportService.deleteReportBySerialno("W170600667");
    }


    @Test
    public void searchSerialno() {

        List<PerformanceHistory> serialnoList = reportService.sreachSerialno("W170600666");
        logger.info("serialnoSize==" + serialnoList.size());
        logger.info("serialnoInfo==" + JSONObject.toJSONString(serialnoList));
    }
}
