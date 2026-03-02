package org.emwhyware.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;

import java.util.Collection;

public class CollectionCaseInsensitivelyConditions extends CollectionConditions{
    public final CollectionAnyOrderOnlyConditions not;
    public final CollectionNotOnlyConditions inAnyOrder;

    CollectionCaseInsensitivelyConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable Collection<?> actual, boolean negated, boolean ignoreCase, boolean anyOrder) {
        super(group, labelForActual, actual, negated, ignoreCase, anyOrder);
        this.not = new CollectionAnyOrderOnlyConditions(group, labelForActual, actual, !negated, ignoreCase, anyOrder);
        this.inAnyOrder = new CollectionNotOnlyConditions(group, labelForActual, actual, negated, ignoreCase, true);
    }
}
