package com.youth.image.domain.entity;

public class ApiException extends Exception{
    private ErrorStatus error;

    public ApiException(ErrorStatus error) {
        super(error.getMessage());
        this.error = error;
    }

    public ErrorStatus getError() {
        return error;
    }
}
