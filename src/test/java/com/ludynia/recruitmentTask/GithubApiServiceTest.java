package com.ludynia.recruitmentTask;

import com.ludynia.recruitmentTask.controller.GitHubController;
import com.ludynia.recruitmentTask.dto.RepositoryDto;
import com.ludynia.recruitmentTask.exception.UserNotFoundException;
import com.ludynia.recruitmentTask.service.GithubApiService;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GithubApiServiceTest {

    @Autowired
    private GithubApiService githubApiService;

    @Test
    public void shouldReturnNotNullRepositoriesForExistingUser() {
        //given
        String username = "twitter";
        //when
        List<RepositoryDto> repositories = githubApiService.getAllRepositories(username);
        //then
        assertNotNull(repositories);
    }

    @Test()
    public void whenUsernameNotFoundShouldThrowUsernameNotFoundException() {
        //given
        String username = ".";
        //when + then
        assertThrows(UserNotFoundException.class, () -> {
            githubApiService.getAllRepositories(username);
        });

    }
}
