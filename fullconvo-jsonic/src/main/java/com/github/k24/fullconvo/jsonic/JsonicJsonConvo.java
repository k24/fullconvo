package com.github.k24.fullconvo.jsonic;

import com.github.k24.fullconvo.convo.ConvoFailedException;
import com.github.k24.fullconvo.option.ConvoOptions;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import net.arnx.jsonic.TypeReference;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Map;

/**
 * Created by k24 on 2017/01/18.
 */
public class JsonicJsonConvo extends AbstractConvo {
    private final String json;

    public JsonicJsonConvo(@Nonnull JSON jsonic, @Nonnull String json) {
        super(jsonic);
        this.json = json;
    }

    @Override
    public String toJson() throws ConvoFailedException {
        if (!json.isEmpty()) {
            try {
                JSON.validate(json);
            } catch (JSONException e) {
                throw new ConvoFailedException(e);
            }
        }
        return json;
    }

    @Override
    public String toJson(ConvoOptions options) throws ConvoFailedException {
        if (json.isEmpty()) return json; // Avoid to return "{}"
        return super.toJson(options);
    }

    @Nonnull
    @Override
    public Map<String, Object> toMap() throws ConvoFailedException {
        if (json.isEmpty()) return Collections.emptyMap();
        return jsonic.parse(json, new TypeReference<Map<String, Object>>() {
        });
    }

    @Nonnull
    @Override
    public <T> T toObject(Class<T> toClass) throws ConvoFailedException {
        if (toClass.isInterface()) return toProxy(toClass, null);
        return jsonic.parse(json, toClass);
    }
}
