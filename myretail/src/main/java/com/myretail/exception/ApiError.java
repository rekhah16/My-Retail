package com.myretail.exception;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("apierror")
public class ApiError {

    String status;
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
