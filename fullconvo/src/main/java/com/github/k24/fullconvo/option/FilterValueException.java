package com.github.k24.fullconvo.option;

import com.github.k24.fullconvo.convo.ConvoFailedException;

/**
 * Created by k24 on 2017/01/17.
 */
public class FilterValueException extends ConvoFailedException {
    public FilterValueException() {
    }

    public FilterValueException(String message) {
        super(message);
    }

    public FilterValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilterValueException(Throwable cause) {
        super(cause);
    }
}
