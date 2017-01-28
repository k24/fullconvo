package com.github.k24.fullconvo.convo;

/**
 * Created by k24 on 2017/01/17.
 */
public class ConvoFailedException extends Exception {
    public ConvoFailedException() {
    }

    public ConvoFailedException(String message) {
        super(message);
    }

    public ConvoFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvoFailedException(Throwable cause) {
        super(cause);
    }
}
