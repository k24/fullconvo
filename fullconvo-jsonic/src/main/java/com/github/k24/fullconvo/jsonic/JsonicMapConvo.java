package com.github.k24.fullconvo.jsonic;

import com.github.k24.fullconvo.convo.ConvoFailedException;
import net.arnx.jsonic.JSON;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Map;

/**
 * Created by k24 on 2017/01/18.
 */
public class JsonicMapConvo extends AbstractConvo {
    private final Map<String, Object> map;

    public JsonicMapConvo(@Nonnull JSON jsonic, @Nonnull Map<String, Object> map) {
        super(jsonic);
        this.map = map;
    }

    @Nonnull
    @Override
    public String toJson() throws ConvoFailedException {
        return jsonic.format(map);
    }

    @Nonnull
    @Override
    public Map<String, Object> toMap() throws ConvoFailedException {
        return Collections.unmodifiableMap(map);
    }

    @Nonnull
    @Override
    public <T> T toObject(Class<T> toClass) throws ConvoFailedException {
        if (toClass.isInterface()) return toProxy(toClass, null);
        return jsonic.parse(jsonic.format(map), toClass);
    }
}
