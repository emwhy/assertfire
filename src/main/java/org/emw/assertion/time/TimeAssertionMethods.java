package org.emw.assertion.time;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;
import org.emw.assertion.AssertionMethods;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeAssertionMethods extends AssertionMethods {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    private final @Nullable LocalTime actualLocalTime;
    public final TimeBeAssertionMethods be;

    protected TimeAssertionMethods(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable LocalTime actualLocalTime, boolean negated) {
        super(group, labelForActual, negated, false);
        this.actualLocalTime = actualLocalTime;
        this.be = new TimeBeAssertionMethods(group, labelForActual, actualLocalTime, negated);
    }

    public void be(@NonNull LocalTime expected) {
        assertCondition(partialAssertionErrorMessage() + "to be '" + expected.format(TIME_FORMATTER) + "'.", () -> {
            if (actualLocalTime == null) {
                return false;
            } else {
                return actualLocalTime.equals(expected) != negated;
            }
        });
    }

    private String partialAssertionErrorMessage() {
        if (labelForActual.isEmpty()) {
            return "Expected '" + (actualLocalTime == null ? "null" : actualLocalTime.format(TIME_FORMATTER)) + "'" + (negated?" not":"") + " ";
        } else {
            return "Expected actual value('" + (actualLocalTime == null ? "null" : actualLocalTime.format(TIME_FORMATTER)) + "') of '" + labelForActual + "'" + (negated?" not":"") + " ";
        }
    }

}
