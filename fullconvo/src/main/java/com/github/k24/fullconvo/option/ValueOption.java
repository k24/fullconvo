package com.github.k24.fullconvo.option;

/**
 * Created by k24 on 2017/01/17.
 */
public interface   ValueOption {
    ValueOption NULL = new ValueOption() {
        @Override
        public Object filterValue(Object value) throws FilterValueException {
            return value;
        }
    };


    /**
     * Filter value in conversion.
     *
     * @param value to convert
     * @return null means to ignore, others are a destiny value
     * @throws FilterNameException thrown if forbidden value passed
     */
    Object filterValue(Object value) throws FilterValueException;
}
