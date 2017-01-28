package com.github.k24.fullconvo.convo;

import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Created by k24 on 2017/01/26.
 */
public class ConvoTool {
    private ConvoTool() {
        //no instance
    }

    public static <T> T createProxyWithMap(Class<T> interfaceClass, Map<String, Object> map) {
        if (!interfaceClass.isInterface())
            throw new IllegalArgumentException(interfaceClass.getName() + " is not interface");

        return null;

    }
}
