package com.okay.appplatform.impl.ui;

import com.okay.appplatform.domain.ui.PadAccount;
import com.okay.appplatform.mapper.ui.PadAccountMapper;
import com.okay.appplatform.service.ui.PadAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhou
 * @date 2020/12/10
 */

@Service("PadAccountService")
public class PadAccountServiceImpl implements PadAccountService {

    @Resource
    PadAccountMapper padAccountMapper;

    @Override
    public void insertAccount(PadAccount padAccount) {
        padAccountMapper.insertAccount(padAccount);
    }

    @Override
    public void insertAccounts(List<PadAccount> padAccountList) {
        padAccountMapper.insertAccounts(padAccountList);
    }

    @Override
    public void deleteAccount(int id) {
        padAccountMapper.deleteAccount(id);
    }

    @Override
    public void updateAccounts(List<PadAccount> padAccountList) {
        padAccountMapper.updateAccounts(padAccountList);
    }

    @Override
    public void updateAccount(PadAccount padAccount) {
        padAccountMapper.updateAccount(padAccount);
    }

    @Override
    public void deleteAllAccount(String envName) {
        padAccountMapper.deleteAllAccount(envName);
    }

    @Override
    public PadAccount findAccountById(int id) {
        return padAccountMapper.findAccountById(id);
    }

    @Override
    public List<PadAccount> findAccountByEnvName(String envName) {
        return padAccountMapper.findAccountByEnvName(envName);
    }

    @Override
    public List<PadAccount> findAccountList() {
        return padAccountMapper.findAccountList();
    }
}
