package com.okay.appplatform.mapper.ui;


import com.okay.appplatform.domain.ui.DeviceInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * @author zhou
 * @date 2020/10/29
 */
@Mapper
public interface DeviceInfoMapper {

    void insertDevice(DeviceInfo deviceInfo);

    void insertDevices(List<DeviceInfo> deviceInfoList);

    void updateDevice(DeviceInfo deviceInfo);

    void deleteAllDevice();

    void deleteDevice(int id);

    DeviceInfo findById(int id);

    DeviceInfo findBySerialno(String serialno);

    List<DeviceInfo> findByFromAndStatus(String run_from, String status);

    List<DeviceInfo> findList();


}
