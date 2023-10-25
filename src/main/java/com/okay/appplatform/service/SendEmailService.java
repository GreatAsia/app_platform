package com.okay.appplatform.service;

import com.alibaba.fastjson.JSONObject;

public interface SendEmailService {

    /**
     * 发送邮件
     * @param request 请求参数
     */
    public Boolean sendEmail(JSONObject request);


    public Boolean sendEmailWebUi(JSONObject requestJson);

}