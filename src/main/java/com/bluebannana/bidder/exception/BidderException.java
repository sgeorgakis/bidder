package com.bluebannana.bidder.exception;

public class BidderException extends RuntimeException {

    private int code;

    public BidderException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
