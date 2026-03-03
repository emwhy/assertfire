package org.emw.assertion.string;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;

public class StringAllConditions extends StringConditions {
    public final StringCaseSensitivelyConditions caseInsensitively;
    public final StringNotConditions not;

    StringAllConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable String actual, boolean negated, boolean ignoreCase) {
        super(group, labelForActual, actual, negated, ignoreCase);
        this.caseInsensitively = new StringCaseSensitivelyConditions(group, labelForActual, actual, negated, true);
        this.not = new StringNotConditions(group, labelForActual, actual, !negated, ignoreCase);
    }
}
