package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public abstract class JsonAssertionMethods extends JsonAssertion {
    private final @NonNull Object object;
    protected final boolean negated;
    protected final boolean ignoreCase;
    protected final boolean inAnyOrder;
    private final @NonNull List<String> excludedNodes = new ArrayList<>();

    protected JsonAssertionMethods(@NonNull JsonAssertionGroup group, @Nullable Object object, boolean negated, boolean ignoreCase, boolean inAnyOrder, @NonNull List<String> excludedNodes) {
        super(group);

        if (object == null) {
            throw new JSONException("Node does not exist at the pointer.");
        }

        this.object = object;
        this.negated = negated;
        this.ignoreCase = ignoreCase;
        this.inAnyOrder = inAnyOrder;
        this.excludedNodes.addAll(excludedNodes);
    }

    protected void addExcludedNode(@NonNull String jsonPointer) {
        this.excludedNodes.add(jsonPointer);
    }

    protected List<String> excludedNodes() {
        return List.of(excludedNodes.toArray(new String[0]));
    }

    protected Object object() {
        return object;
    }

    protected JSONObject jsonObject() {
        if (object instanceof JSONObject) {
            return (JSONObject) object;
        } else {
            throw new JSONException("Not JSONObject.");
        }
    }

    protected JSONArray jsonArray() {
        if (object instanceof JSONArray) {
            return (JSONArray) object;
        } else  {
            throw new JSONException("Not JSONArray.");
        }
    }
}
