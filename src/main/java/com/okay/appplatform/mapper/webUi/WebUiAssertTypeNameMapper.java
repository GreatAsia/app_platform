package com.okay.appplatform.mapper.webUi;

import com.okay.appplatform.domain.ui.AssertTypeName;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WebUiAssertTypeNameMapper {
    public List<AssertTypeName> getAssertTypeListName(AssertTypeName assertTypeName);

    public AssertTypeName getAssertById(@Param("id") Integer id);
}
