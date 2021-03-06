package com.github.k24.fullconvo.jsonic;

import com.github.k24.fullconvo.Fullconvo;
import com.github.k24.fullconvo.convo.*;
import net.arnx.jsonic.JSON;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.Map;

/**
 * Created by k24 on 2017/01/18.
 */
public class JsonicFullconvo {
    private JsonicFullconvo() {
        //no instance
    }

    public static Fullconvo create() {
        return create(Holder.JSON_INSTANCE);
    }

    public static Fullconvo create(final JSON jsonic) {
        return new Fullconvo.Builder()
                .feltConvoFactory(new FeltConvoFactory() {
                    @Nonnull
                    @Override
                    public Convo from(@Nonnull String format, @Nonnull InputStream inputStream) {
                        return from(format, new InputStreamReader(inputStream));
                    }

                    @Nonnull
                    @Override
                    public Convo from(@Nonnull String format, @Nonnull Reader reader) {
                        return new JsonicFeltConvo(jsonic, format, reader);
                    }

                    @Nonnull
                    @Override
                    public Convo from(@Nonnull String format, @Nonnull String string) {
                        return from(format, new StringReader(string));
                    }
                })
                .jsonConvoFactory(new JsonConvoFactory() {
                    @Override
                    public Convo from(String json) {
                        return new JsonicJsonConvo(jsonic, json);
                    }
                })
                .mapConvoFactory(new MapConvoFactory() {
                    @Override
                    public Convo from(Map<String, Object> map) {
                        return new JsonicMapConvo(jsonic, map);
                    }
                })
                .objectConvoFactory(new ObjectConvoFactory() {
                    @Override
                    public Convo from(Object object) {
                        return new JsonicObjectConvo(jsonic, object);
                    }
                })
                .build();
    }

    public static Convo json(String json) {
        return Holder.DEFAULT_INSTANCE.json(json);
    }

    public static Convo map(Map<String, Object> map) {
        return Holder.DEFAULT_INSTANCE.map(map);
    }

    public static Convo object(Object object) {
        return Holder.DEFAULT_INSTANCE.object(object);
    }

    private static class Holder {
        static final JSON JSON_INSTANCE;
        static final Fullconvo DEFAULT_INSTANCE;

        static {
            JSON json = new JSON();
            json.setSuppressNull(true);
            JSON_INSTANCE = json;
            DEFAULT_INSTANCE = create(json);
        }
    }
}
