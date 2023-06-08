package com.ludynia.recruitmentTask.controller;

import com.ludynia.recruitmentTask.dto.RepositoryDto;
import com.ludynia.recruitmentTask.request.GithubRequest;
import com.ludynia.recruitmentTask.service.GithubApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class GitHubApiController {
    private final GithubApiService githubApiService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/repositories", headers = "Accept=application/json")
    public List<RepositoryDto> getAllRepositories(@RequestBody GithubRequest githubRequest) {
        log.info("In method getAllRepositories(). Request taken: {}", githubRequest);
        String username = githubRequest.getUsername();
        return githubApiService.getAllRepositories(username);
    }
}

