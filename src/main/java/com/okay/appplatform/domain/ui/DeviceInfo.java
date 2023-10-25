package com.okay.appplatform.domain.ui;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author zhou
 * @date 2020/10/29
 */
@Getter
@Setter
public class DeviceInfo {

    private int id;

    @NotNull(message = "序列号不能为空")
    private String serialno;

    @NotNull(message = "IP不能为空")
    private String ip;

    @NotNull(message = "设备状态不能为空")
    private String status;

    @NotNull(message = "来源位置不能为空")
    @NotBlank(message = "来源位置不能为空")
    private String run_from;


}
