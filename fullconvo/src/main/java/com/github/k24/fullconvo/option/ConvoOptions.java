package com.github.k24.fullconvo.option;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by k24 on 2017/01/17.
 */
public class ConvoOptions {
    public static final ConvoOptions DEFAULT = new ConvoOptions(null, null);

    @Nonnull
    public final NameOption nameOption;

    @Nonnull
    public final ValueOption valueOption;

    public ConvoOptions(@Nullable NameOption nameOption, @Nullable ValueOption valueOption) {
        this.nameOption = nameOption == null ? NameOption.NULL : nameOption;
        this.valueOption = valueOption == null ? ValueOption.NULL : valueOption;
    }
}
