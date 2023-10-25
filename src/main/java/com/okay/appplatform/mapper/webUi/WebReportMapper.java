package com.okay.appplatform.mapper.webUi;

import com.okay.appplatform.domain.ui.WebReport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebReportMapper {
    public int addWebReport(WebReport webReport);
    public List<WebReport> getWebReportList(WebReport webReport);
    public WebReport getNewWebReport();
}
