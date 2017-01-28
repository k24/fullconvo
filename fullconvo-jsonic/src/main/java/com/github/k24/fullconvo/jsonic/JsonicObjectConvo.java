package com.github.k24.fullconvo.jsonic;

import com.github.k24.fullconvo.convo.ConvoFailedException;
import com.github.k24.fullconvo.tool.ProxyFactory;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.TypeReference;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Created by k24 on 2017/01/18.
 */
public class JsonicObjectConvo extends AbstractConvo {
    private final Object object;

    public JsonicObjectConvo(@Nonnull JSON jsonic, @Nonnull Object object) {
        super(jsonic);
        this.object = object;
    }

    @Nonnull
    @Override
    public String toJson() throws ConvoFailedException {
        return jsonic.format(object);
    }

    @Nonnull
    @Override
    public Map<String, Object> toMap() throws ConvoFailedException {
        return jsonic.parse(jsonic.format(object), new TypeReference<Map<String, Object>>() {
        });
    }

    @Nonnull
    @Override
    public <T> T toObject(Class<T> toClass) throws ConvoFailedException {
        if (toClass.isInterface()) return toProxy(toClass, null);
        return jsonic.parse(jsonic.format(object), toClass);
    }

}
