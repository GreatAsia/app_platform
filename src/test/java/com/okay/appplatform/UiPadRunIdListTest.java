package com.okay.appplatform;



import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.okay.appplatform.domain.report.UiPadRunIdList;
import com.okay.appplatform.domain.ui.PadAccount;
import com.okay.appplatform.service.ui.PadAccountService;
import com.okay.appplatform.service.ui.UiPadRunIdListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UiPadRunIdListTest {


    private static final Logger logger = LoggerFactory.getLogger(UiPadRunIdListTest.class);

    @Resource
    UiPadRunIdListService uiPadRunIdListService;
    @Resource
    PadAccountService padAccountService;


    @Test
    public void insertUiPadRunIdList() {
        UiPadRunIdList uiPadRunIdList = new UiPadRunIdList();
        uiPadRunIdList.setTotalDevice(3);
        uiPadRunIdList.setPassDevice(1);
        uiPadRunIdList.setFailDevice(1);
        uiPadRunIdList.setErrorDevice(1);
        uiPadRunIdList.setElapsedTime("12分30秒");
        uiPadRunIdList.setStartTime("2019-02-28 11:03:12");
        uiPadRunIdList.setPassRate("0.0%");
        logger.info("report:" + JSONObject.toJSONString(uiPadRunIdList));
        uiPadRunIdListService.insertUiPadRunIdList(uiPadRunIdList);

    }

    @Test
    public void updateUiPadRunIdList() {
        UiPadRunIdList uiPadRunIdList = new UiPadRunIdList();
        uiPadRunIdList.setId(26);
        uiPadRunIdList.setTotalDevice(5);
        uiPadRunIdList.setPassDevice(3);
        uiPadRunIdList.setFailDevice(1);
        uiPadRunIdList.setErrorDevice(1);
        uiPadRunIdList.setElapsedTime("3s");
        uiPadRunIdList.setStartTime("2019-02-26");
        uiPadRunIdList.setPassRate("30%");
        logger.info("report:" + JSONObject.toJSONString(uiPadRunIdList));
        uiPadRunIdListService.updateUiPadRunIdList(uiPadRunIdList);
    }

    @Test
    public void deleteUiPadRunIdList() {

        uiPadRunIdListService.deleteUiPadById(2);
    }

    @Test
    public void findUiPadRunIdList() {

        UiPadRunIdList uiPadRunIdList = uiPadRunIdListService.findUiPadRunIdList(1);
        logger.info("[uiPadRunIdList]" + JSONObject.toJSON(uiPadRunIdList));
    }

    @Test
    public void findUiPadRunIdLists() {

        List<UiPadRunIdList> uiPadRunIdList = uiPadRunIdListService.findUiPadRunList(1, 10).getList();
        logger.info("[uiPadRunIdList]" + JSONObject.toJSON(uiPadRunIdList));
    }

    @Test
    public void testGetLastRunId() {
        int runId = uiPadRunIdListService.getLastId();
        logger.info("[runId]" + runId);
    }


    @Test
    public void testDeleteAllDevice() {

        String envName = "dev";
        String students = "81951102939;81951102938;81951102937;81951102936;81951102935";
        List<PadAccount> padAccountList = new ArrayList<>();
        String[] accountList = students.split(";");
        for (String acc : accountList) {
            PadAccount padAccount = new PadAccount();
            padAccount.setAccount(acc);
            padAccount.setEnvName(envName);
            padAccountList.add(padAccount);

        }
        padAccountService.deleteAllAccount(envName);
        padAccountService.insertAccounts(padAccountList);
    }

    @Test
    public void testDeleteDevice() {

        padAccountService.deleteAccount(1);

    }

    @Test
    public void testFindCaseList(){
      PageInfo pageInfo =  uiPadRunIdListService.findUiPadRunList(1,10);
      logger.info("pageinfo==" + JSONObject.toJSONString(pageInfo));
    }

}
