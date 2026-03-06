package org.emw.assertion.json;

public class JsonAssertion {
    private final String json;

    protected JsonAssertion(String json) {
        this.json = json;
    }

    public void expect(JsonAssertionAction action) {
        WithJson withJson = new WithJson(json);

        action.withJson(withJson);
    }

    public interface JsonAssertionAction {
        void withJson(WithJson json);
    }
}
