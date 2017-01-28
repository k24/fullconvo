package com.github.k24.fullconvo.option;

/**
 * Created by k24 on 2017/01/17.
 */
public interface NameOption {
    NameOption NULL = new NameOption() {
        @Override
        public String filterName(String name) throws FilterNameException {
            return name;
        }
    };

    /**
     * Filter name in conversion.
     *
     * @param name to convert
     * @return null means to ignore, others are a destiny name
     * @throws FilterNameException thrown if forbidden name passed
     */
    String filterName(String name) throws FilterNameException;
}
