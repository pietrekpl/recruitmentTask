package com.ludynia.recruitmentTask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ludynia.recruitmentTask.model.Branch;
import com.ludynia.recruitmentTask.model.Owner;
import com.ludynia.recruitmentTask.util.BranchesUrlDeserializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Slf4j

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {
    @JsonProperty("name")
    private String repositoryName;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("branches_url")
    @JsonDeserialize(using = BranchesUrlDeserializer.class)
    private List<Branch> branches;
    @JsonProperty("fork")
    private boolean fork;


}
