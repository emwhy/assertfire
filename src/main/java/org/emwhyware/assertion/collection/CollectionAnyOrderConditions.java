package org.emwhyware.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;

import java.util.Collection;

public class CollectionAnyOrderConditions extends CollectionConditions {
    public final CollectionCaseInsensitivityOnlyConditions not;
    public final CollectionNotOnlyConditions caseInsensitively;

    CollectionAnyOrderConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable Collection<?> actual, boolean negated, boolean ignoreCase, boolean anyOrder) {
        super(group, labelForActual, actual, negated, ignoreCase, anyOrder);
        this.not = new CollectionCaseInsensitivityOnlyConditions(group, labelForActual, actual, !negated, ignoreCase, anyOrder);
        this.caseInsensitively = new CollectionNotOnlyConditions(group, labelForActual, actual, negated, true, anyOrder);
    }
}
