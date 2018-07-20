package com.shuorigf.solarstaition.data.exception;

/**
 * Created by clx on 18/2/2.
 */

public class ResponseMessageException extends Exception{

    private String errorCode;
    private String errorMessage;

    public ResponseMessageException(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage  = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
