package com.github.k24.fullconvo.jsonic;

import com.github.k24.fullconvo.convo.Convo;
import com.github.k24.fullconvo.convo.ConvoFailedException;
import com.github.k24.fullconvo.convo.Felt;
import com.github.k24.fullconvo.convo.FeltFailedException;
import com.github.k24.fullconvo.option.ConvoOptions;
import com.github.k24.fullconvo.tool.ProxyFactory;
import net.arnx.jsonic.JSON;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by k24 on 2017/01/18.
 */
abstract class AbstractConvo implements Convo, Felt {
    protected final JSON jsonic;

    protected AbstractConvo(JSON jsonic) {
        this.jsonic = jsonic;
    }

    @Nonnull
    @Override
    public Felt felt() {
        return this;
    }

    @Override
    public String output(@Nonnull String format) throws FeltFailedException {
        if (!FORMAT_JSON.equals(format)) throw new FeltFailedException(format + " Not Supported");
        try {
            return toJson();
        } catch (ConvoFailedException e) {
            throw new FeltFailedException(e);
        }
    }

    @Override
    public void output(@Nonnull String format, @Nonnull Appendable appendable) throws FeltFailedException {
        if (!FORMAT_JSON.equals(format)) throw new FeltFailedException(format + " Not Supported");
        try {
            jsonic.format(toMap(), appendable);
        } catch (IOException e) {
            throw new FeltFailedException(e);
        } catch (ConvoFailedException e) {
            throw new FeltFailedException(e);
        }
    }

    @Override
    public String output(@Nonnull String format, ConvoOptions convoOptions) throws FeltFailedException {
        if (!FORMAT_JSON.equals(format)) throw new FeltFailedException(format + " Not Supported");
        try {
            return toJson(convoOptions);
        } catch (ConvoFailedException e) {
            throw new FeltFailedException(e);
        }
    }

    @Override
    public void output(@Nonnull String format, @Nonnull Appendable appendable, ConvoOptions convoOptions) throws FeltFailedException {
        if (!FORMAT_JSON.equals(format)) throw new FeltFailedException(format + " Not Supported");
        try {
            jsonic.format(toMap(convoOptions), appendable);
        } catch (IOException e) {
            throw new FeltFailedException(e);
        } catch (ConvoFailedException e) {
            throw new FeltFailedException(e);
        }
    }

    @Override
    public String toJson(ConvoOptions options) throws ConvoFailedException {
        if (options == null) {
            return toJson();
        }
        Map<String, Object> result = filterToMap(options);
        return jsonic.format(result);
    }

    @Override
    public Map<String, Object> toMap(ConvoOptions options) throws ConvoFailedException {
        if (options == null) return toMap();
        return filterToMap(options);
    }

    @Override
    public <T> T toObject(Class<T> toClass, ConvoOptions options) throws ConvoFailedException {
        if (options == null) return toObject(toClass);
        if (toClass.isInterface()) return toProxy(toClass, options);
        return jsonic.parse(jsonic.format(filterToMap(options)), toClass);
    }

    protected <T> T toProxy(Class<T> toClass, ConvoOptions options) throws ConvoFailedException {
        Map<String, Object> map = options == null ? toMap() : filterToMap(options);
        return ProxyFactory.DEFAULT.createProxyWithMap(toClass, map);
    }

    private LinkedHashMap<String, Object> filterToMap(ConvoOptions options) throws ConvoFailedException {
        Map<String, Object> map = toMap();
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>(map.size());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String name = options.nameOption.filterName(entry.getKey());
            if (name == null) continue;
            Object value = options.valueOption.filterValue(entry.getValue());
            if (value == null) continue;
            result.put(name, value);
        }
        return result;
    }
}
