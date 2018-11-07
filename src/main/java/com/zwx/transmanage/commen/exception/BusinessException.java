package com.zwx.transmanage.commen.exception;

import com.zwx.transmanage.commen.constant.ResponseCode;

/**
 * Created by zhaowenx on 2018/8/23.
 */
//某些业务需要进行业务回滚。但spring的事务只针对RuntimeException的进行回滚操作。所以需要回滚就要继承RuntimeException。
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
