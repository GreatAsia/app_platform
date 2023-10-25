package com.okay.appplatform.domain.ui;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author zhou
 * @date 2020/12/10
 */
@Getter
@Setter

public class PadAccount {

    private int id;

    @NotNull(message = "环境名不能为空")
    private String envName;

    @NotNull(message = "账号不能为空")
    private String account;


}
