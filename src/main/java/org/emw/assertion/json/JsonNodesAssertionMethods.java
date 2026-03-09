package org.emw.assertion.json;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.emw.assertion.AssertionMethods;
import org.emw.assertion.collection.CollectionAssertor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.StreamSupport;

public class JsonNodesAssertionMethods extends AssertionMethods {
    public final JsonNodesBeAssertionMethods be;
    private final @Nullable JSONArray jsonArray;
    private final boolean negated;
    private final boolean ignoreCase;
    private final boolean inAnyOrder;
    protected final @NonNull List<String> excludedNodes = new ArrayList<>();

    protected JsonNodesAssertionMethods(@Nullable JSONArray jsonArray, boolean negated, boolean ignoreCase, boolean inAnyOrder, @NonNull List<String> excludedNodes) {
        super(null, "", negated, ignoreCase);
        this.jsonArray = jsonArray;
        this.negated = negated;
        this.ignoreCase = ignoreCase;
        this.inAnyOrder = inAnyOrder;
        this.excludedNodes.addAll(excludedNodes);
        this.be = new JsonNodesBeAssertionMethods(jsonArray, negated, ignoreCase, inAnyOrder, excludedNodes);
    }

    public void allMatch(@NonNull Object... expectedArray) {
        allMatch(List.of(expectedArray));
    }

    public void allMatch(@NonNull Collection<?> expectedCollection) {
        assertCondition(() -> {
            if (this.jsonArray == null) {
                throw new AssertionError("Node does not exist.");
            } else if (this.jsonArray.length() != expectedCollection.size()) {
                throw new AssertionError("Expected Json array size does not match with the actual size.");
            } else if (this.inAnyOrder) {
                final List<Object> expectedList = new ArrayList<>(expectedCollection).stream().map(JsonHelper::jsonMapper).sorted(new JsonHelper.JsonComparator()).toList();

                final JsonCollectionAssertor assertor = new JsonCollectionAssertor();

                if (expectedList.stream().allMatch(o -> o instanceof JSONArray || o instanceof JSONObject)) {
                    final List<Object> list = new ArrayList<>(this.jsonArray.toList()).stream().sorted(new JsonHelper.JsonComparator()).toList();
                    final JSONArray jArray = new JSONArray(list);

                    for (int i = 0; i < list.size(); i++) {
                        final @NonNull Object expected = expectedList.get(i) == null ? ""  : expectedList.get(i);
                        final String errorMessage = JsonHelper.matchJson(jArray.get(i), expected, this.excludedNodes, this.ignoreCase);

                        if (!errorMessage.isEmpty()) {
                            if (negated) {
                                return;
                            } else {
                                throw new AssertionError(errorMessage);
                            }
                        }
                    }
                    if (negated) {
                        throw new AssertionError("Expected Json array to not match in any order, but it does.");
                    }
                } else if (ignoreCase) {
                    assertor.expect("Json Array", jsonArray.toList()).to.inAnyOrder.caseInsensitively.allMatch(expectedList);
                } else {
                    assertor.expect("Json Array", jsonArray.toList()).to.inAnyOrder.allMatch(expectedList);
                }
            } else {
                final List<Object> expectedList = expectedCollection.stream().map(JsonHelper::jsonMapper).toList();
                final JsonCollectionAssertor assertor = new JsonCollectionAssertor();

                if (expectedList.stream().allMatch(o -> o instanceof JSONArray || o instanceof JSONObject)) {
                    for (int i = 0; i < this.jsonArray.length(); i++) {
                        final @NonNull Object expected = expectedList.get(i) == null ? ""  : expectedList.get(i);
                        final String errorMessage = JsonHelper.matchJson(this.jsonArray.get(i), expected, this.excludedNodes, this.ignoreCase);

                        if (!errorMessage.isEmpty()) {
                            if (negated) {
                                return;
                            } else {
                                throw new AssertionError(errorMessage);
                            }
                        }
                    }
                    if (negated) {
                        throw new AssertionError("Expected Json array to not match, but it does.");
                    }
                } else if (ignoreCase){
                    assertor.expect("Json Array", jsonArray.toList()).to.caseInsensitively.allMatch(expectedList);
                } else {
                    assertor.expect("Json Array", jsonArray.toList()).to.allMatch(expectedList);
                }
            }
        });

    }

    public void be(@NonNull String expected) {
        assertCondition(() -> {
            final Object expectedObject = JsonHelper.isJsonArray(expected) ? new JSONArray(expected) : expected;

            if (this.jsonArray == null) {
                throw new AssertionError("Node does not exist.");
            } else if (expectedObject instanceof JSONArray) {
                final String errorMessage = JsonHelper.matchJson(this.jsonArray, expectedObject, this.excludedNodes, this.ignoreCase);

                if (negated) {
                    if (errorMessage.isEmpty()) {
                        throw new AssertionError("Expected Json to not match, but they match.");
                    }
                } else {
                    if (!errorMessage.isEmpty()) {
                        throw new AssertionError(errorMessage);
                    }
                }
            } else {
                if (!negated) {
                    throw new AssertionError("Expected Json to match.");
                }
            }
        });
    }

    public void findJson(@NonNull String containedJsonText) {
        if (JsonHelper.isJson(containedJsonText)) {
            findJson(new JSONObject(containedJsonText));
        } else if (JsonHelper.isJsonArray(containedJsonText)) {
            findJson(new JSONArray(containedJsonText));
        } else {
            throw new AssertionError("Not Json.");
        }
    }

    public void findJson(@NonNull JSONObject containedJson) {
        assertCondition(() -> {
            if (this.jsonArray == null) {
                throw new AssertionError("Node does not exist.");
            } else if (JsonHelper.findJson(this.jsonArray, containedJson, this.excludedNodes, this.ignoreCase) == negated) {
                throw new AssertionError("Expected the actual Json data to contain the expected Json data.");
            }
        });
    }

    public void findJson(@NonNull JSONArray containedJson) {
        assertCondition(() -> {
            if (this.jsonArray == null) {
                throw new AssertionError("Node does not exist.");
            } else if (JsonHelper.findJson(this.jsonArray, containedJson, this.excludedNodes, this.ignoreCase) == negated) {
                throw new AssertionError("Expected the actual Json data to contain the expected Json data.");
            }
        });
    }

    public void contain(@NonNull Object... expectedArray) {
        contain(List.of(expectedArray));
    }

    public void contain(@NonNull Collection<?> expectedCollection) {
        assertCondition(() -> {
            if (this.jsonArray == null) {
                throw new AssertionError("Node does not exist.");
            } else {
                final List<Object> expectedList = expectedCollection.stream().map(JsonHelper::jsonMapper).toList();
                final JsonCollectionAssertor assertor = new JsonCollectionAssertor();
                int foundCount = 0;

                if (expectedList.stream().allMatch(o -> o instanceof JSONArray || o instanceof JSONObject)) {
                    for (Object expected : expectedList) {
                        final boolean found = StreamSupport.stream(jsonArray.spliterator(), false).anyMatch(o -> JsonHelper.matchJson(o, expected, this.excludedNodes, this.ignoreCase).isEmpty());

                        if (found) {
                            foundCount++;
                        }
                    }

                    if (negated) {
                        if (foundCount == expectedList.size()) {
                            throw new AssertionError("Expected Json array to not have expected Json data, but it does.");
                        }
                    } else {
                        if (foundCount != expectedList.size()) {
                            throw new AssertionError("Expected Json array to have expected Json data, but it does not.");
                        }
                    }
                } else if (ignoreCase){
                    assertor.expect("Json Array", jsonArray.toList()).to.caseInsensitively.contain(expectedList);
                } else {
                    assertor.expect("Json Array", jsonArray.toList()).to.contain(expectedList);
                }
            }

        });
    }


    private class JsonCollectionAssertor implements CollectionAssertor {
        private JsonCollectionAssertor() {}
    }
}
