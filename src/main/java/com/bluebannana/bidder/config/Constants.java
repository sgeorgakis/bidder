package com.bluebannana.bidder.config;

public class Constants {

    public enum ErrorCode {

        NO_HANDLER_FOUND_ERROR(2, "The requested endpoint cannot be found"),
        INVALID_ARGUMENTS_ERROR(3, "The request's parameters were not valid"),
        MEDIA_TYPE_NOT_SUPPORTED_ERROR(5, "Media type not supported"),
        INTERNAL_SERVER_ERROR(6, "Something went wrong.");


        private int code;

        private String message;

        ErrorCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return this.code;
        }

        public String getMessage() {
            return this.message;
        }
    }
}
