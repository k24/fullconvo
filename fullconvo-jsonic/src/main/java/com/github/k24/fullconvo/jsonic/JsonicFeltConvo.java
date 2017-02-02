package com.github.k24.fullconvo.jsonic;

import com.github.k24.fullconvo.convo.ConvoFailedException;
import net.arnx.jsonic.JSON;
import net.arnx.jsonic.JSONException;
import net.arnx.jsonic.TypeReference;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * Created by k24 on 2017/01/31.
 */
public class JsonicFeltConvo extends AbstractConvo {
    @Nonnull
    private final String format;
    @Nonnull
    private final Reader reader;

    private Map<String, Object> cache;
    private Throwable exception;

    public JsonicFeltConvo(@Nonnull JSON jsonic, @Nonnull String format, @Nonnull Reader reader) {
        super(jsonic);
        this.format = format;
        this.reader = reader;
    }

    @Override
    public String toJson() throws ConvoFailedException {
        return jsonic.format(toMap());
    }

    @Override
    public Map<String, Object> toMap() throws ConvoFailedException {
        if (exception != null) throw new ConvoFailedException(exception);
        try {
            if (cache != null) return cache; // Use cache if has read
            cache = jsonic.parse(reader, new TypeReference<Map<String, Object>>() {
            });
            return cache;
        } catch (IOException e) {
            exception = e;
            throw new ConvoFailedException(e);
        } catch (JSONException e) {
            exception = e;
            throw new ConvoFailedException(e);
        }
    }

    @Override
    public <T> T toObject(Class<T> toClass) throws ConvoFailedException {
        if (exception != null) throw new ConvoFailedException(exception);
        try {
            if (toClass.isInterface()) return toProxy(toClass, null);
            if (cache != null) return jsonic.parse(jsonic.format(cache), toClass); // Use cache if has read
            return jsonic.parse(reader, toClass);
        } catch (IOException e) {
            exception = e;
            throw new ConvoFailedException(e);
        } catch (JSONException e) {
            exception = e;
            throw new ConvoFailedException(e);
        }
    }
}
