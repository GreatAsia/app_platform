package com.okay.appplatform.mapper.webUi;

import com.okay.appplatform.domain.ui.WebPlatform;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WebPlatformMapper {
    public int addWebPlatform(WebPlatform webPlatform);

    public int updateWebPlatform(WebPlatform webPlatform);

    public List<WebPlatform> getPlatformList(WebPlatform webPlatform);
}
