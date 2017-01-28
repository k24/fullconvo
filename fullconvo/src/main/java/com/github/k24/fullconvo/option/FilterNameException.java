package com.github.k24.fullconvo.option;

import com.github.k24.fullconvo.convo.ConvoFailedException;

/**
 * Created by k24 on 2017/01/17.
 */
public class FilterNameException extends ConvoFailedException {
    public FilterNameException() {
    }

    public FilterNameException(String message) {
        super(message);
    }

    public FilterNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilterNameException(Throwable cause) {
        super(cause);
    }
}
