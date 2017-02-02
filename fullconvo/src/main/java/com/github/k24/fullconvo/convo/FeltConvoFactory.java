package com.github.k24.fullconvo.convo;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.io.Reader;

/**
 * Created by k24 on 2017/01/30.
 */
public interface FeltConvoFactory {
    @Nonnull
    Convo from(@Nonnull String format, @Nonnull InputStream inputStream);

    @Nonnull
    Convo from(@Nonnull String format, @Nonnull Reader reader);

    @Nonnull
    Convo from(@Nonnull String format, @Nonnull String string);
}
