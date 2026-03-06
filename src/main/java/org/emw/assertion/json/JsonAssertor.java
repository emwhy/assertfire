package org.emw.assertion.json;

public interface JsonAssertor {
    default JsonAssertion assertJson(String json) {
        return new JsonAssertion(json);
    }
}
