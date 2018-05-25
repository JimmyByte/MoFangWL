package com.homenet.mofangwl.net;

/**
 * Created by weijunpeng on 2018/5/22.
 */

public class APIException extends RuntimeException {
    private String errStatus;
    private String errMessage;

    public APIException(String errStatus, String errMessage) {
        this.errStatus = errStatus;
        this.errMessage = errMessage;
    }

    public String getErrorStatus() {
        return errStatus;
    }

    public String getErrorMessage() {
        return errMessage;
    }
}
