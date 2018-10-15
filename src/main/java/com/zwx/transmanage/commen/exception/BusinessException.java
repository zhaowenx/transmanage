package com.zwx.transmanage.commen.exception;

import com.zwx.transmanage.commen.constant.ResponseCode;

/**
 * Created by zhaowenx on 2018/8/23.
 */
public class BusinessException extends RuntimeException {

    /**
     * 序列化使用
     */
    private static final long serialVersionUID = 1L;

    private ResponseCode responseCode;

    public BusinessException(ResponseCode errorCode) {
        super(errorCode.toString());
        this.responseCode = errorCode;
    }

    public BusinessException(ResponseCode errorCode, Throwable cause) {
        super(errorCode.toString(), cause);
        this.responseCode = errorCode;
    }

    /**
     * @return the errorCode
     */
    public ResponseCode getErrorCode() {
        return responseCode;
    }
}
