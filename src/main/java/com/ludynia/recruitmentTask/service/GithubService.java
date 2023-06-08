package com.ludynia.recruitmentTask.service;


import com.ludynia.recruitmentTask.dto.BranchDto;
import com.ludynia.recruitmentTask.dto.CommitDto;
import com.ludynia.recruitmentTask.dto.OwnerDto;
import com.ludynia.recruitmentTask.dto.RepositoryDto;
import com.ludynia.recruitmentTask.model.Branch;
import com.ludynia.recruitmentTask.model.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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

        List<RepositoryDto> responseList = new ArrayList<>();
        for (Repository repository : repositories) {
            RepositoryDto repositoryDto = new RepositoryDto();
            repositoryDto.setRepositoryName(repository.getRepositoryName());

            OwnerDto ownerDto = new OwnerDto();
            ownerDto.setLogin(repository.getOwner().getLogin());
            repositoryDto.setOwner(ownerDto);

            List<BranchDto> branchDtos = new ArrayList<>();
            // Fetch branches for the repository
            List<Branch> branches = fetchBranches(repository.getRepositoryName(), username);
            for (Branch branch : branches) {
                BranchDto branchDto = new BranchDto();
                branchDto.setName(branch.getName());

                CommitDto commitDto = new CommitDto();
                commitDto.setSha(branch.getCommit().getSha());
                branchDto.setCommit(commitDto);

                branchDtos.add(branchDto);
            }
            repositoryDto.setBranches(branchDtos);

            responseList.add(repositoryDto);
        }

        return responseList;
    }

    private List<Branch> fetchBranches(String repositoryName, String username) {
        String apiUrl = github_api_url + "/repos/" + username + "/" + repositoryName + "/branches";
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION_HEADER,BEARER_PREFIX+apiKey);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Branch[]> branchResponse = restTemplate.exchange(
                apiUrl,
                HttpMethod.GET,
                entity,
                Branch[].class
        );
        Branch[] branches = branchResponse.getBody();
        assert branches != null;
        return Arrays.asList(branches);
    }
}
