package com.github.k24.fullconvo.test;

import com.google.common.io.CharStreams;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by k24 on 2016/12/25.
 */
public class Resources {
    public static String readAsString(String name) throws IOException {
        return CharStreams.toString(readAsReader(name));
    }

    public static Reader readAsReader(String name) {
        return new InputStreamReader(Resources.class.getResourceAsStream(name));
    }

    public static String singleTypesAsString() throws IOException {
        return Resources.readAsString("/" + "simple" + "/" + "SingleTypes.json");
    }

    private static String collectionTypesAsString() throws IOException {
        return Resources.readAsString("/" + "simple" + "/" + "CollectionTypes.json");
    }
}
