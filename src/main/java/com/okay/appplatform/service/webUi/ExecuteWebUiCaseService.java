package com.okay.appplatform.service.webUi;

import com.okay.appplatform.domain.ui.WebCase;

import java.util.List;

public interface ExecuteWebUiCaseService {


    public boolean executeCase(Integer id);

    public boolean executeCaseList(List<WebCase> webCases);
}
