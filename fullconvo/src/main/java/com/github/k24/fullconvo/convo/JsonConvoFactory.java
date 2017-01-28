package com.github.k24.fullconvo.convo;

import javax.annotation.Nonnull;

/**
 * Created by k24 on 2017/01/17.
 */
public interface JsonConvoFactory {
    @Nonnull
    Convo from(@Nonnull String json);
}
