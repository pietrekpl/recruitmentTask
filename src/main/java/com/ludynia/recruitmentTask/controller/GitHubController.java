package com.ludynia.recruitmentTask.controller;

import com.ludynia.recruitmentTask.dto.RepositoryDto;
import com.ludynia.recruitmentTask.request.GithubRequest;
import com.ludynia.recruitmentTask.service.GithubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class GitHubController {
    private final GithubService githubService;

    @PostMapping("/repositories")
    public List<RepositoryDto> getAllRepositories(@RequestBody GithubRequest githubRequest) {
        String username = githubRequest.getUsername();
        return githubService.getAllRepositories(username);
    }
}

