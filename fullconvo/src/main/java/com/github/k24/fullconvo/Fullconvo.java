package com.github.k24.fullconvo;

import com.github.k24.fullconvo.convo.Convo;
import com.github.k24.fullconvo.convo.JsonConvoFactory;
import com.github.k24.fullconvo.convo.MapConvoFactory;
import com.github.k24.fullconvo.convo.ObjectConvoFactory;

import java.util.Map;

/**
 * Created by k24 on 2017/01/17.
 */
public final class Fullconvo {
    private final JsonConvoFactory jsonConvoFactory;
    private final MapConvoFactory mapConvoFactory;
    private final ObjectConvoFactory objectConvoFactory;

    Fullconvo(Builder builder) {
        this.jsonConvoFactory = builder.jsonConvoFactory;
        this.mapConvoFactory = builder.mapConvoFactory;
        this.objectConvoFactory = builder.objectConvoFactory;
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
        JsonConvoFactory jsonConvoFactory;
        MapConvoFactory mapConvoFactory;
        ObjectConvoFactory objectConvoFactory;

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
