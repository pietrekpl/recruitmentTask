package com.ludynia.recruitmentTask.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubApiConfig {

    @Value("${github.base.apikey}")
    private String apikey;

    @Bean
    public RequestInterceptor apiKeyInterceptor() {
        return template -> template.header("Authorization", "Bearer "+ apikey);
    }


}
