package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionMethods;
import org.json.JSONArray;

import java.util.List;

public class JsonNodesBeAssertionMethods extends AssertionMethods {
    private final @Nullable JSONArray jsonArray;
    private final boolean negated;
    private final boolean ignoreCase;
    private final boolean inAnyOrder;
    private final @NonNull List<String> excludedNodes;

    protected JsonNodesBeAssertionMethods(@Nullable JSONArray jsonArray, boolean negated, boolean ignoreCase, boolean inAnyOrder, @NonNull List<String> excludedNodes) {
        super(null, "", negated, ignoreCase);
        this.jsonArray = jsonArray;
        this.negated = negated;
        this.ignoreCase = ignoreCase;
        this.inAnyOrder = inAnyOrder;
        this.excludedNodes = excludedNodes;
    }

    public void empty() {
        assertCondition(() -> {
            if (jsonArray == null) {
                throw new AssertionError("Node does not exist.");
            } else {
                if (negated) {
                    if (jsonArray.isEmpty()) {
                        throw new AssertionError("Expected non-empty Json array.");
                    }
                } else {
                    if (!jsonArray.isEmpty()) {
                        throw new AssertionError("Expected empty Json array.");
                    }
                }
            }
        });
    }

    public void sizeOf(int expectedSize) {
        assertCondition(() -> {
            if (this.jsonArray == null) {
                throw new AssertionError("Node does not exist.");
            } else {
                if (negated) {
                    if (expectedSize == this.jsonArray.length()) {
                        throw new AssertionError("Expected Json array size to not be equal to Json array size.");
                    }
                } else {
                    if (expectedSize != this.jsonArray.length()) {
                        throw new AssertionError("Expected Json array size to be equal to Json array size.");
                    }
                }
            }
        });
    }
}
