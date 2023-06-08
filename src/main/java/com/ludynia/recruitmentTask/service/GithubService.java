package com.ludynia.recruitmentTask.service;


import com.ludynia.recruitmentTask.dto.BranchDto;
import com.ludynia.recruitmentTask.dto.CommitDto;
import com.ludynia.recruitmentTask.dto.OwnerDto;
import com.ludynia.recruitmentTask.dto.RepositoryDto;
import com.ludynia.recruitmentTask.exception.UserNotFoundException;
import com.ludynia.recruitmentTask.model.Branch;
import com.ludynia.recruitmentTask.model.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {

    @Value("${github.api.url}")
    private  String github_api_url;

    @Value("${github.api.api-key}")
    private  String apiKey;

    private final RestTemplate restTemplate;
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    public List<RepositoryDto> getAllRepositories(String username) {

        if (!isUsernameExists(username)) {
            throw new UserNotFoundException("User with username " + username + " does not exist on Github.");
        }


        String apiUrl = github_api_url + "/users/" + username + "/repos";
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION_HEADER,BEARER_PREFIX+apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Repository[]> repositoryResponse = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                Repository[].class
        );
        Repository[] repositories = repositoryResponse.getBody();

        List<RepositoryDto> result = new ArrayList<>();
        if (repositories != null) {
            for (Repository repository : repositories) {
                if (!repository.isFork()) {
                    RepositoryDto repositoryDto = new RepositoryDto();
                    repositoryDto.setRepositoryName(repository.getRepositoryName());

                    OwnerDto ownerDto = new OwnerDto();
                    ownerDto.setLogin(repository.getOwner().getLogin());
                    repositoryDto.setOwner(ownerDto);

                    List<BranchDto> branchDtos = fetchBranches(repository.getRepositoryName(), username);
                    repositoryDto.setBranches(branchDtos);

                    result.add(repositoryDto);
                }
            }
        }

        return result;
    }
    private boolean isUsernameExists(String username){
        String apiUrl = github_api_url + "/users/" + username;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            return response.getStatusCode() == HttpStatus.OK;
        } catch (HttpClientErrorException.NotFound ex) {
            return false;
        }
    }

    private List<BranchDto> fetchBranches(String repositoryName, String username) {
        String apiUrl = github_api_url + "/repos/" + username + "/" + repositoryName + "/branches";
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION_HEADER,BEARER_PREFIX+apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Branch[]> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, Branch[].class);
        Branch[] branches = response.getBody();

        List<BranchDto> branchDtos = new ArrayList<>();
        if (branches != null) {
            for (Branch branch : branches) {
                BranchDto branchDto = new BranchDto();
                branchDto.setName(branch.getName());

                CommitDto commitDto = new CommitDto();
                commitDto.setSha(branch.getCommit().getSha());
                branchDto.setCommit(commitDto);

                branchDtos.add(branchDto);
            }
        }

        return branchDtos;
    }
}

