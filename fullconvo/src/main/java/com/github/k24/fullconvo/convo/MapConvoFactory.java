package com.github.k24.fullconvo.convo;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * Created by k24 on 2017/01/17.
 */
public interface MapConvoFactory {
    @Nonnull
    Convo from(@Nonnull Map<String, Object> map);
}
