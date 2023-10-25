package com.okay.appplatform.service.ui;


import com.okay.appplatform.domain.ui.PadAccount;

import java.util.List;


public interface PadAccountService {


    void insertAccount(PadAccount padAccount);

    void insertAccounts(List<PadAccount> padAccountList);

    void deleteAccount(int id);

    void updateAccounts(List<PadAccount> padAccountList);

    void updateAccount(PadAccount padAccount);

    void deleteAllAccount(String envName);

    PadAccount findAccountById(int id);

    List<PadAccount> findAccountByEnvName(String envName);

    List<PadAccount> findAccountList();

}
