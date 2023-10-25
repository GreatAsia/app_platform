package com.okay.appplatform.tools.app;

import com.github.cosysoft.device.android.AndroidDevice;
import com.github.cosysoft.device.android.impl.AndroidDeviceStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @author zhou
 * @date 2021/1/15
 */
public class DeviceInfo {

    private final static Logger logger = LoggerFactory.getLogger(DeviceInfo.class);
    /**
     * 获取在线的设备
     *
     * @return
     */
    public static List<AndroidDevice> getDeviceOnlineList() {


        List<AndroidDevice> list = new ArrayList<>();
        TreeSet<AndroidDevice> devices = AndroidDeviceStore.getInstance().getDevices();
        if (devices.size() == 0) {
            return list;
        }
        for (AndroidDevice a : devices) {
            if (!"OFFLINE".equals(a.getDevice().getState().toString())) {
                list.add(a);
            }else {
                logger.error("offline device=={}",a.getSerialNumber());
            }
        }
        return list;

    }
}