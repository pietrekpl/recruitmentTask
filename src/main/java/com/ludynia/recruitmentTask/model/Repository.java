package com.ludynia.recruitmentTask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ludynia.recruitmentTask.util.BranchesUrlDeserializer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

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
