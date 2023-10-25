package com.okay.appplatform.impl.webUi;

import com.okay.appplatform.domain.ui.WebReport;
import com.okay.appplatform.domain.ui.WebReportDesc;
import com.okay.appplatform.mapper.webUi.WebReportDescMapper;
import com.okay.appplatform.mapper.webUi.WebReportMapper;
import com.okay.appplatform.service.webUi.WebReportService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("WebReportService")
public class WebReportServiceImpl implements WebReportService {

    @Resource
    WebReportMapper webReportMapper;
    @Resource
    WebReportDescMapper webReportDescMapper;

    @Override
    public List<WebReport> getWebReportList(WebReport webReport) {
        return webReportMapper.getWebReportList(webReport);
    }

    @Override
    public List<WebReportDesc> getWebReportDescList(WebReportDesc webReportDesc) {
        return webReportDescMapper.getWebReportDescList(webReportDesc);
    }
}
