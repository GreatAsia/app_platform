package com.okay.appplatform.mapper.webUi;

import com.okay.appplatform.domain.ui.WebCase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WebCaseMapper {
    public List<WebCase> getWebCaseList(WebCase webCase);

    public int addWebCase(WebCase webCase);


    public WebCase getWebCaseById(@Param("id") Integer id);


    public int updateTestStatusOrScreenshot(@Param("id") Integer id, @Param("testStatus") char testStatus, @Param("screenshot") String screenshot);

    public int updateWebCases(List<WebCase> webCases);

    public int deleteCase(@Param("id") Integer id);
}
