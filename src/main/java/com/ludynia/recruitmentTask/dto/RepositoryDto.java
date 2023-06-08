package com.ludynia.recruitmentTask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.ludynia.recruitmentTask.util.BranchesUrlDeserializer;
import lombok.Data;

import java.util.List;
@Data
public class RepositoryDto {
    @JsonProperty("name")
    private String repositoryName;
    @JsonProperty("owner")
    private OwnerDto owner;
    @JsonProperty("branches_url")
    @JsonDeserialize(using = BranchesUrlDeserializer.class)
    private List<BranchDto> branches;
}
