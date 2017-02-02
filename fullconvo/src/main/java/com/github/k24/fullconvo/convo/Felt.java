package com.github.k24.fullconvo.convo;

import com.github.k24.fullconvo.option.ConvoOptions;

import javax.annotation.Nonnull;

/**
 * Created by k24 on 2017/01/30.
 */
public interface Felt {
    String FORMAT_JSON = "application/json";

    void output(@Nonnull String format, @Nonnull Appendable appendable) throws FeltFailedException;

    String output(@Nonnull String format) throws FeltFailedException;

    void output(@Nonnull String format, @Nonnull Appendable appendable, ConvoOptions options) throws FeltFailedException;

    String output(@Nonnull String format, ConvoOptions options) throws FeltFailedException;
}
