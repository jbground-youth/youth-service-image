package com.youth.image.domain.entity;

public class ApiResult {
    private final String status;
    private final String message;

    private ApiResult(Builder builder) {
        this.status = builder.status;
        this.message = builder.message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String status;
        private String message;

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public ApiResult build(){
            return new ApiResult(this);
        }
    }
}
