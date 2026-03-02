package org.emwhyware.assertion.collection;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emwhyware.assertion.AssertionGroup;

import java.util.Arrays;
import java.util.Collection;

public final class CollectionAssertionGroup {
    private final AssertionGroup group;

    public CollectionAssertionGroup(@NonNull AssertionGroup group) {
        this.group = group;
    }

    public CollectionTo expect(@Nullable Object[] actual) {
        return expect("", actual);
    }

    public CollectionTo expect(@NonNull String labelForActual, @Nullable Object[] actual) {
        return new CollectionTo(group, labelForActual, actual == null ? null : Arrays.stream(actual).toList(), false, false, false);
    }

    public CollectionTo expect(@Nullable Collection<?> actual) {
        return expect("", actual);
    }

    public CollectionTo expect(@NonNull String labelForActual, @Nullable Collection<?> actual) {
        return new CollectionTo(group, labelForActual, actual, false, false, false);
    }
}
