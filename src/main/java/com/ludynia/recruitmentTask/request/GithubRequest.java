package com.ludynia.recruitmentTask.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GithubRequest {
    @JsonProperty("username")
    private String username;
}
