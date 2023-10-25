package com.okay.appplatform.mapper.ui;

import com.okay.appplatform.domain.report.UiPadSerialnoList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UiPadSerialnoListMapper {

    public void insertUiPadSerialnoList(UiPadSerialnoList uiPadSerialnoList);

    void insertUiPadSerialnoS(List<UiPadSerialnoList> uiPadSerialnoList);
    public void updateUiPadSerialnoList(UiPadSerialnoList uiPadSerialnoList);

    public void deleterUiPadSerialnoList(int id);

    public UiPadSerialnoList findUiPadSerialnoListById(int id);

    public List<UiPadSerialnoList> findUiPadSerialnoListByRunId(int id);

    public UiPadSerialnoList findUiPadSerialnoListBySerialno(String serialno);

    public List<UiPadSerialnoList> findUiPadSerialnoList();

    public UiPadSerialnoList findUiPadSerialnoListByRunIdAndSerialno(int id, String serialno);


}
