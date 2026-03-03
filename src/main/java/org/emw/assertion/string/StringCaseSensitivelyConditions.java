package org.emw.assertion.string;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;

public class StringCaseSensitivelyConditions extends StringConditions {
    public final StringConditions not;

    StringCaseSensitivelyConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable String actual, boolean negated, boolean ignoreCase) {
        super(group, labelForActual, actual, negated, ignoreCase);
        this.not = new StringConditions(group, labelForActual, actual, !negated, ignoreCase);
    }
}
