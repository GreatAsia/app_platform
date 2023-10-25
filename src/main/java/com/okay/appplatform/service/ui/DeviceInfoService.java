package com.okay.appplatform.service.ui;


import com.okay.appplatform.domain.ui.DeviceInfo;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * @author zhou
 * @date 2020/10/29
 */
public interface DeviceInfoService {

    void insertDevice(DeviceInfo deviceInfo);

    void insertDevices(List<DeviceInfo> deviceInfoList);

    void updateDevice(DeviceInfo deviceInfo);

    void deleteAllDevice();

    void deleteDevice(int id);

    @Async
    void runUiTest(List<String> ids, String from);

    @Async
    void runShell(String cmd);

    DeviceInfo findById(int id);

    DeviceInfo findBySerialno(String serialno);

    List<DeviceInfo> findByFromAndStatus(String run_from, String status);

    List<DeviceInfo> findList();


}
