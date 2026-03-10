package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;

public abstract class JsonAssertion {
    protected final JsonAssertionGroup group;

    protected JsonAssertion(JsonAssertionGroup group) {
        this.group = group;
    }

    protected interface AssertionAction {
        void doAssertiveAction();
    }

    protected final void assertCondition(@NonNull AssertionAction assertion) {
        try {
            assertion.doAssertiveAction();
        } catch (Throwable th) {
            this.addToGroup(th);
        }
    }

    protected final void addToGroup(@NonNull Throwable throwable) {
        this.group.addThrowable(throwable);
    }
}
