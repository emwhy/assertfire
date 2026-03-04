package org.emw.assertion.time;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;

import java.time.LocalTime;

public class TimeNotBeAssertionMethods extends TimeAssertionMethods {
    public final TimeAssertionMethods not;

    protected TimeNotBeAssertionMethods(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable LocalTime actualLocalTime, boolean negated) {
        super(group, labelForActual, actualLocalTime, negated);
        this.not = new TimeAssertionMethods(group, labelForActual, actualLocalTime, !negated);
    }
}
