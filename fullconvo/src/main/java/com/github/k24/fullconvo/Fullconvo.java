package com.github.k24.fullconvo;

import com.github.k24.fullconvo.convo.*;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;

/**
 * Created by k24 on 2017/01/17.
 */
public final class Fullconvo {
    private final FeltConvoFactory feltConvoFactory;
    private final JsonConvoFactory jsonConvoFactory;
    private final MapConvoFactory mapConvoFactory;
    private final ObjectConvoFactory objectConvoFactory;

    Fullconvo(Builder builder) {
        this.feltConvoFactory = builder.feltConvoFactory;
        this.jsonConvoFactory = builder.jsonConvoFactory;
        this.mapConvoFactory = builder.mapConvoFactory;
        this.objectConvoFactory = builder.objectConvoFactory;
    }

    public Convo felt(@Nonnull String format, InputStream inputStream) {
        if (inputStream == null) return Convo.NULL;
        return feltConvoFactory.from(format, inputStream);
    }

    public Convo felt(@Nonnull String format, Reader reader) {
        if (reader == null) return Convo.NULL;
        return feltConvoFactory.from(format, reader);
    }

    public Convo felt(@Nonnull String format, String string) {
        if (string == null) return Convo.NULL;
        return feltConvoFactory.from(format, string);
    }

    public Convo json(String json) {
        if (json == null) return Convo.NULL;
        return jsonConvoFactory.from(json);
    }

    public Convo map(Map<String, Object> map) {
        if (map == null) return Convo.NULL;
        return mapConvoFactory.from(map);
    }

    public Convo object(Object object) {
        if (object == null) return Convo.NULL;
        return objectConvoFactory.from(object);
    }

    public static class Builder {
        FeltConvoFactory feltConvoFactory;
        JsonConvoFactory jsonConvoFactory;
        MapConvoFactory mapConvoFactory;
        ObjectConvoFactory objectConvoFactory;

        public Builder feltConvoFactory(FeltConvoFactory feltConvoFactory) {
            this.feltConvoFactory = feltConvoFactory;
            return this;
        }

        public Builder jsonConvoFactory(JsonConvoFactory jsonConvoFactory) {
            this.jsonConvoFactory = jsonConvoFactory;
            return this;
        }

        public Builder mapConvoFactory(MapConvoFactory mapConvoFactory) {
            this.mapConvoFactory = mapConvoFactory;
            return this;
        }

        public Builder objectConvoFactory(ObjectConvoFactory objectConvoFactory) {
            this.objectConvoFactory = objectConvoFactory;
            return this;
        }

        public Fullconvo build() {
            return new Fullconvo(this);
        }
    }
}
