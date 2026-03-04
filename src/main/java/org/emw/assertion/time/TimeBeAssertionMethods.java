package org.emw.assertion.time;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionGroup;
import org.emw.assertion.AssertionMethods;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeBeAssertionMethods extends AssertionMethods {
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
    private final @Nullable LocalTime actualLocalTime;

    protected TimeBeAssertionMethods(@Nullable AssertionGroup group, @NonNull String labelForActual, @Nullable LocalTime actualLocalTime, boolean negated) {
        super(group, labelForActual, negated, false);
        this.actualLocalTime = actualLocalTime;
    }

    public void before(@NonNull LocalTime expected) {
        assertCondition(partialAssertionErrorMessage() + "to be before '" + expected.format(TIME_FORMATTER) + "'.", () -> {
            if (actualLocalTime == null) {
                return false;
            } else {
                return actualLocalTime.isBefore(expected) != negated;
            }
        });
    }

    public void after(@NonNull LocalTime expected) {
        assertCondition(partialAssertionErrorMessage() + "to be after '" + expected.format(TIME_FORMATTER) + "'.", () -> {
            if (actualLocalTime == null) {
                return false;
            } else {
                return actualLocalTime.isAfter(expected) != negated;
            }
        });
    }

    public void sameOrBefore(@NonNull LocalTime expected) {
        assertCondition(partialAssertionErrorMessage() + "to be the same or before '" + expected.format(TIME_FORMATTER) + "'.", () -> {
            if (actualLocalTime == null) {
                return false;
            } else {
                return (actualLocalTime.equals(expected) || actualLocalTime.isBefore(expected)) != negated;
            }
        });
    }

    public void sameOrAfter(@NonNull LocalTime expected) {
        assertCondition(partialAssertionErrorMessage() + "to be the same or after '" + expected.format(TIME_FORMATTER) + "'.", () -> {
            if  (actualLocalTime == null) {
                return false;
            } else {
                return (actualLocalTime.equals(expected) || actualLocalTime.isAfter(expected)) != negated;
            }
        });
    }
    
    public void between(@NonNull LocalTime start, @NonNull LocalTime end) {
        assertCondition(partialAssertionErrorMessage() + "to be between '" + start.format(TIME_FORMATTER) + "' and '" + end.format(TIME_FORMATTER) + "'.", () -> {
            if (actualLocalTime == null) {
                return false;
            } else {
                return ((actualLocalTime.equals(start) || actualLocalTime.isAfter(start)) && (actualLocalTime.isBefore(end) || actualLocalTime.equals(end))) != negated;
            }
        });
    }

    public void withinPastHours(int hours) {
        final LocalTime now = LocalTime.now();
        final LocalTime targetDateTime = now.minusHours(hours);

        assertCondition(partialAssertionErrorMessage() + "to be within past " + hours + " hours from '" +  now.format(TIME_FORMATTER) + "'.", () -> {
            if  (actualLocalTime == null) {
                return false;
            } else {
                return ((actualLocalTime.equals(targetDateTime) || actualLocalTime.isAfter(targetDateTime)) && (actualLocalTime.isBefore(now) || actualLocalTime.equals(now))) != negated;
            }
        });
    }

    public void moreThanHoursInFuture(int hours) {
        final LocalTime now = LocalTime.now();
        final LocalTime targetDateTime = now.plusHours(hours);

        assertCondition(partialAssertionErrorMessage() + "to be more than " + hours + " hours in future from '" + now.format(TIME_FORMATTER) + "'.", () -> {
            if (actualLocalTime == null) {
                return false;
            } else {
                return actualLocalTime.isAfter(targetDateTime) != negated;
            }
        });
    }

    public void moreThanHoursInPast(int hours) {
        final LocalTime now = LocalTime.now();
        final LocalTime targetDateTime = now.minusHours(hours);

        assertCondition(partialAssertionErrorMessage() + "to be more than " + hours + " hours in past from '" + now.format(TIME_FORMATTER) + "'.", () -> {
            if (actualLocalTime == null) {
                return false;
            } else {
                return actualLocalTime.isBefore(targetDateTime) != negated;
            }
        });
    }

    public void nullValue() {
        assertCondition(partialAssertionErrorMessage() + "to be null.", () -> {
            return negated != (actualLocalTime == null);
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
