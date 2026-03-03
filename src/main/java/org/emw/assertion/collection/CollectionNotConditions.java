package org.emw.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;

import java.util.Collection;

public class CollectionNotConditions extends CollectionConditions {
    public final CollectionCaseInsensitivityOnlyConditions inAnyOrder;
    public final CollectionAnyOrderOnlyConditions caseInsensitively;

    CollectionNotConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable Collection<?> actual, boolean negated, boolean ignoreCase, boolean anyOrder) {
        super(group, labelForActual, actual, negated, ignoreCase, anyOrder);
        this.inAnyOrder = new CollectionCaseInsensitivityOnlyConditions(group, labelForActual, actual, negated, ignoreCase, true);
        this.caseInsensitively = new CollectionAnyOrderOnlyConditions(group, labelForActual, actual, negated, true, anyOrder);
    }
}
