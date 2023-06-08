package com.ludynia.recruitmentTask.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class GithubRequest {
    @JsonProperty("username")
    @NotBlank
    @NotEmpty
    private String username;
}
