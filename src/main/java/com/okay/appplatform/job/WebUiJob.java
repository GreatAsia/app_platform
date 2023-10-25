package com.okay.appplatform.job;


import com.alibaba.fastjson.JSONObject;
import com.okay.appplatform.common.constant.Receiver;
import com.okay.appplatform.domain.ui.WebCase;
import com.okay.appplatform.domain.ui.WebReport;
import com.okay.appplatform.impl.webUi.WebUiThreader;
import com.okay.appplatform.mapper.webUi.WebCaseMapper;
import com.okay.appplatform.mapper.webUi.WebReportMapper;
import com.okay.appplatform.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class WebUiJob extends BaseJob {

    @Autowired
    WebUiThreader webUiThreader;

    @Resource
    WebCaseMapper WebCaseMapper;

    @Resource
    WebReportMapper webReportMapper;

    @Resource
    SendEmailService sendEmailService;


    @Override
    public void runMonitor(JSONObject jsonObject) {
        WebCase webCase = new WebCase();
        webCase.setPlatformId(Integer.valueOf(jsonObject.getString("platformId")));
        List<WebCase> webCases = WebCaseMapper.getWebCaseList(webCase);
        webUiThreader.excuteCaseList(webCases);
        WebReport webReport = webReportMapper.getNewWebReport();

        String sendTo = jsonObject.getString("sendTo") == null ? "" : jsonObject.getString("sendTo");
        jsonObject.put("sendTo", Receiver.QA + sendTo);
        //jsonObject.put("sendTo", "xieyangyang@okay.cn");
        jsonObject.put("webReportId", webReport.getId());
        if(webReport.getFailCaseCount()>0){
            sendEmailService.sendEmailWebUi(jsonObject);
        }
    }
}
