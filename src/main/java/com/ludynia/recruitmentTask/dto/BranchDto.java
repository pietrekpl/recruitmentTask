package com.ludynia.recruitmentTask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ludynia.recruitmentTask.model.Commit;
import lombok.Data;

@Data
public class BranchDto {
    @JsonProperty("name")
    private String name;
    @JsonProperty("commit")
    private CommitDto commit;
}
