package com.okay.appplatform.mapper.webUi;

import com.okay.appplatform.domain.ui.WebPlatform;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebCasePlatformMapper {
    public List<WebPlatform> getPlatformById(@Param("id") Integer id);
}
