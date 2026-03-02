package org.emw.assertion.string;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public interface StringAssertor {
    default StringTo expect(@Nullable String actual) {
        return expect("", actual);
    }

    default StringTo expect(@NonNull String labelForActual, @Nullable String actual) {
        return new StringTo(null, labelForActual, actual, false, false);
    }
}
