package com.ludynia.recruitmentTask.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OwnerDto {
    @JsonProperty("login")
    private String login;
}
