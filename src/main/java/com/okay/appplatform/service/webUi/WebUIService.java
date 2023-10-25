package com.okay.appplatform.service.webUi;

import com.alibaba.fastjson.JSONObject;
import com.okay.appplatform.domain.ui.*;
import com.okay.appplatform.exception.myException.DataException;

import java.util.List;

public interface WebUIService {
    public List<WebCase> getListWebCase(WebCase webCase);

    public Boolean addWebCase(WebCase webCase);

    public List<WebPlatform> getPlatformById(Integer id);

    public List<AssertType> getAssertType(AssertType assertType);

    public List<AssertTypeName> getAssertTypeName(AssertTypeName assertTypeName);

    public JSONObject getWebCaseForUpdate(WebCase webCase) throws DataException;

    public boolean deleteCase(Integer id);

    public void seleniumExecuteByWebCase(WebCase webCase, WebReportDesc webReportDesc);

    public boolean addPlatform(WebPlatform webPlatform);

    public boolean updatePlatform(WebPlatform webPlatform);

    public List<WebPlatform> getPlatformList(WebPlatform webPlatform);
}
