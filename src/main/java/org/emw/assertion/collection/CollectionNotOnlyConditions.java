package org.emw.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;

import java.util.Collection;

public class CollectionNotOnlyConditions extends CollectionConditions {
    public final CollectionConditions not;

    CollectionNotOnlyConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable Collection<?> actual, boolean negated, boolean ignoreCase, boolean anyOrder) {
        super(group, labelForActual, actual, negated, ignoreCase, anyOrder);
        this.not = new CollectionConditions(group, labelForActual, actual, !negated, ignoreCase, anyOrder);
    }
}
