package com.ludynia.recruitmentTask.controller;

import com.ludynia.recruitmentTask.dto.RepositoryDto;
import com.ludynia.recruitmentTask.model.Repository;
import com.ludynia.recruitmentTask.service.GithubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class GitHubController {
    private final GithubService githubService;

    @GetMapping("/repositories/{username}")
    public List<RepositoryDto> getAllRepositories(@PathVariable("username") String username) {
        return githubService.getAllRepositories(username);
    }
}

