package com.ludynia.recruitmentTask.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.ludynia.recruitmentTask.model.Branch;
import com.ludynia.recruitmentTask.model.Commit;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BranchesUrlDeserializer extends JsonDeserializer<List<Branch>> {


    @Override
    public List<Branch> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.readValueAsTree();

        List<Branch> branches = new ArrayList<>();
        if (node.isArray()) {
            for (JsonNode branchNode : node) {
                Branch branch = new Branch();
                branch.setName(branchNode.get("name").asText());

                JsonNode commitNode = branchNode.get("commit");
                if (commitNode != null) {
                    Commit commit = new Commit();
                    commit.setSha(commitNode.get("sha").asText());
                    branch.setCommit(commit);
                }

                branches.add(branch);
            }
        }
        return branches;
    }
}
