package com.okay.appplatform.mapper.webUi;

import com.okay.appplatform.domain.ui.AssertType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WebUiAssertTypeMapper {
    public List<AssertType> getAssertTypeList(AssertType assertType);
    public AssertType getAssertTypeById(@Param("id") Integer id);
}
