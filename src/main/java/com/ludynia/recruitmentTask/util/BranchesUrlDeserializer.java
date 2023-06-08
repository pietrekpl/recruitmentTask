package com.ludynia.recruitmentTask.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ludynia.recruitmentTask.model.Branch;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Slf4j
public class BranchesUrlDeserializer extends JsonDeserializer<List<Branch>>  {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Branch> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String branchesUrl = jsonParser.readValueAs(String.class);
        List<Branch> branches = null;

        try {
            branches = fetchBranches(branchesUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return branches;
    }

    private List<Branch> fetchBranches(String branchesUrl) throws IOException {
        URL url = new URL(branchesUrl);
        Branch[] branches = objectMapper.readValue(url, Branch[].class);
        return List.of(branches);
    }
}
