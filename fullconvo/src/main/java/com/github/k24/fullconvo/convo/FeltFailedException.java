package com.github.k24.fullconvo.convo;

/**
 * Created by k24 on 2017/01/30.
 */
public class FeltFailedException extends ConvoFailedException {
    public FeltFailedException() {
    }

    public FeltFailedException(String message) {
        super(message);
    }

    public FeltFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public FeltFailedException(Throwable cause) {
        super(cause);
    }
}
