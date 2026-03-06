package org.emw.assertion.json;

/*

assertJson(json).expect(json -> {
    json.to.contain(data);
    json.to.be("");
    json.node("").to.contain(data);
    json.node("").to.excludingNode("").caseInsensitively.not.contain(data);
    json.nodes("").to.inAnyOrder.have(data);
    json.nodes("").at(0).node("").to.inAnyOrder.have(data);
    json.nodes("").iterate((node, i) -> {
        node.to
    });
});

 */
public class WithJson {
    private String json;
    public final JsonTo to = null;

    WithJson(String json) {
        this.json = json;
    }

    public JsonNode node(String nodeText) {
        return null;
    }

    public JsonNodes nodes(String nodesText) {
        return null;
    }
}
