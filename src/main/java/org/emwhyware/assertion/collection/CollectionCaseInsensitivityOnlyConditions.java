package org.emwhyware.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;

import java.util.Collection;

public class CollectionCaseInsensitivityOnlyConditions extends CollectionConditions {
    public final CollectionConditions caseInsensitively;

    CollectionCaseInsensitivityOnlyConditions(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable Collection<?> actual, boolean negated, boolean ignoreCase, boolean anyOrder) {
        super(group, labelForActual, actual, negated, ignoreCase, anyOrder);
        this.caseInsensitively = new CollectionConditions(group, labelForActual, actual, negated, true, anyOrder);
    }
}
