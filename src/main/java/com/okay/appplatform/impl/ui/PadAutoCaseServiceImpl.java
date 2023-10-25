package com.okay.appplatform.impl.ui;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okay.appplatform.domain.ui.PadAutoCase;
import com.okay.appplatform.mapper.ui.PadAutoCaseMapper;
import com.okay.appplatform.service.ui.PadAutoCaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;



@Service("PadAutoCaseService")
public class PadAutoCaseServiceImpl implements PadAutoCaseService {

    @Resource
    PadAutoCaseMapper padAutoCaseMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertCase(PadAutoCase padAutoCase) {
        padAutoCaseMapper.insertCase(padAutoCase);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCase(int id) {
        padAutoCaseMapper.deleteCase(id);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCase(PadAutoCase padAutoCase) {
        padAutoCaseMapper.updateCase(padAutoCase);

    }

    @Override
    public PadAutoCase findCaseById(int id) {
        return padAutoCaseMapper.findCaseById(id);
    }

    @Override
    public List<PadAutoCase> findCaseByName(String name) {
        return padAutoCaseMapper.findCaseByName(name);
    }

    @Override
    public PageInfo findCaseList(int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);

        List<PadAutoCase> padAutoCaseList =  padAutoCaseMapper.findCaseList();
        PageInfo pageInfo = new PageInfo(padAutoCaseList);

        return pageInfo;
    }

    @Override
    public List<PadAutoCase> findCaseList() {
        return padAutoCaseMapper.findCaseList();
    }
}
