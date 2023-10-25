package com.okay.appplatform.request;

import com.okay.appplatform.domain.middle.RequestSampler;
import com.okay.appplatform.domain.middle.ResponseSampler;

public interface Request {

    public ResponseSampler post(RequestSampler requestSampler);

    public ResponseSampler get(RequestSampler requestSampler);
}
