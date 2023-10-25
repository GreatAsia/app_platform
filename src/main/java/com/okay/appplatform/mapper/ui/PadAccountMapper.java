package com.okay.appplatform.mapper.ui;


import com.okay.appplatform.domain.ui.PadAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PadAccountMapper {


    void insertAccount(PadAccount padAccount);

    void insertAccounts(List<PadAccount> padAccountList);

    void deleteAccount(int id);

    void deleteAllAccount(String envName);

    void updateAccount(PadAccount padAccount);

    void updateAccounts(List<PadAccount> padAccountList);

    PadAccount findAccountById(int id);

    List<PadAccount> findAccountByEnvName(String envName);

    List<PadAccount> findAccountList();

}
