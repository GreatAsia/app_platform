package com.okay.appplatform.request;

import com.alibaba.fastjson.JSONObject;
import com.okay.appplatform.domain.middle.RequestSampler;
import com.okay.appplatform.domain.middle.ResponseSampler;
import io.restassured.response.Response;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.okay.appplatform.tools.ReplaceHtml.replaceHtml;
import static com.okay.appplatform.tools.UicodeBackslashU.unicodeToCn;
import static io.restassured.RestAssured.given;

public class PostJsonRequest extends RequestBase {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    ResponseSampler responseSampler = new ResponseSampler();
    private final static int CODE = 200;

    @Override
    public ResponseSampler post(RequestSampler requestSampler) {
        Response response = null;
        JudeHeader(requestSampler);
        logger.info("[caseName]==" + requestSampler.getCaseName());
        logger.info("[requestUrl]==" + requestSampler.getUrl());
        logger.info("[requestId]==" + requestSampler.getRequestId());
        logger.info("[requestHeaders]==" + requestSampler.getHeaders());
        logger.info("[requestCookies]==" + requestSampler.getCookies());

        String params = JSONObject.toJSONString(requestSampler.getBody());
        logger.info("[requestInfo]==" + params);
        try {
            response = given()
                    .cookies(requestSampler.getCookies())
                    .headers(requestSampler.getHeaders())
                    .contentType(requestSampler.getContentType())
                    .body(params)
                    .when()
                    .post(requestSampler.getUrl());


            if (response.getStatusCode() == CODE) {
                responseSampler.setCookies(response.getCookies());
                logger.info("[responseHeaders]==" + JSONObject.toJSONString(response.getHeaders()));
                logger.info("[responseCookies]==" + JSONObject.toJSONString(response.getCookies()));
                logger.info("[responseInfo]==" + response.asString());

            }else {

                logger.error("[requestInfo]==" + requestSampler.getBody());
                logger.error("[responseInfo]==" + response.asString() );
            }
                responseSampler.setRequestId(requestSampler.getRequestId());
                responseSampler.setResponseCode(response.getStatusCode());
            String result = replaceHtml(response.asString());
            if (result.contains("\\u")) {
                result = unicodeToCn(result);
            }
            responseSampler.setResponse(result);


        } catch (Exception e) {
            logger.error("[requestFail]==" + e.getLocalizedMessage());
            logger.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();

        }

        return responseSampler;

    }
}
