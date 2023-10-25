package com.okay.appplatform.service.webUi;

import com.okay.appplatform.domain.ui.WebReport;
import com.okay.appplatform.domain.ui.WebReportDesc;

import java.util.List;

public interface WebReportService {
    public List<WebReport> getWebReportList(WebReport webReport);

    public List<WebReportDesc> getWebReportDescList(WebReportDesc webReportDesc);
}
