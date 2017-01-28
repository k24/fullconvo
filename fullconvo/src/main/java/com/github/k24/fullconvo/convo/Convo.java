package com.github.k24.fullconvo.convo;

import com.github.k24.fullconvo.option.ConvoOptions;

import java.util.Map;

/**
 * Created by k24 on 2017/01/17.
 */
public interface Convo {
    Convo NULL = new Convo() {
        @Override
        public String toJson() throws ConvoFailedException {
            return null;
        }

        @Override
        public Map<String, Object> toMap() throws ConvoFailedException {
            return null;
        }

        @Override
        public <T> T toObject(Class<T> toClass) throws ConvoFailedException {
            return null;
        }

        @Override
        public String toJson(ConvoOptions options) throws ConvoFailedException {
            return null;
        }

        @Override
        public Map<String, Object> toMap(ConvoOptions options) throws ConvoFailedException {
            return null;
        }

        @Override
        public <T> T toObject(Class<T> toClass, ConvoOptions options) throws ConvoFailedException {
            return null;
        }
    };

    String toJson() throws ConvoFailedException;

    Map<String, Object> toMap() throws ConvoFailedException;

    <T> T toObject(Class<T> toClass) throws ConvoFailedException;

    String toJson(ConvoOptions options) throws ConvoFailedException;

    Map<String, Object> toMap(ConvoOptions options) throws ConvoFailedException;

    <T> T toObject(Class<T> toClass, ConvoOptions options) throws ConvoFailedException;
}
