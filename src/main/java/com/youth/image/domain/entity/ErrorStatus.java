package com.youth.image.domain.entity;


import org.springframework.http.HttpStatus;

public enum ErrorStatus {
    OBJECT_EXCEPTION(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "image file has errors."),
    RUNTIME_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "server has errors."),
    DATABASE_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "database has errors."),
    AWS_S3_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "amazon s3 has errors.");

    private final HttpStatus status;
    private final String message;

    ErrorStatus(HttpStatus status, String message){
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
