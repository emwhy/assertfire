package org.emw.assertion.json;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONPointerException;

import java.util.List;

public class JsonNode extends JsonAssertion {
    public final JsonNodeAllAssertionMethods to;
    protected final @Nullable Object nodeObject;

    protected JsonNode(@NonNull JsonAssertionGroup group, @Nullable Object nodeObject) {
        super(group);
        this.nodeObject = nodeObject;
        this.to = new JsonNodeAllAssertionMethods(this.group, nodeObject, false, false, List.of());
    }

    public JsonNode node(String jsonPointer) {
        try {
            if (nodeObject instanceof JSONObject) {
                final Object o = ((JSONObject) nodeObject).query(jsonPointer);

                return new JsonNode(this.group, o);
            } else {
                throw new JSONException("Already at a data node. Cannot traverse further: data = '" + nodeObject + "'");
            }
        } catch (JSONPointerException ex) {
            throw new JSONException("Cannot find the node: " + jsonPointer, ex);
        }
    }

    public JsonNodes nodes(String jsonPointer) {
        try {
            if (nodeObject instanceof JSONObject) {
                final Object o = ((JSONObject) nodeObject).query(jsonPointer);

                if (o == null) {
                    throw new JSONException("Cannot find JSON node: '" + jsonPointer + "'");
                } else if (o instanceof JSONArray) {
                    return new JsonNodes(this.group, (JSONArray) o);
                } else {
                    throw new JSONException("The node is not Json array.");
                }
            } else {
                throw new JSONException("Already at a data node. Cannot traverse further: data = '" + nodeObject + "'");
            }
        } catch (JSONException ex) {
            throw new JSONException("Cannot find the node: " + jsonPointer, ex);
        }

    }
}
