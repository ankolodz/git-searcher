package com.example.gitsearcher.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RepositorySearcherServiceIntegrationTest {

    @Autowired
    private RepositorySearcherService repositorySearcherService;

    @Test
    void shouldReturnAllRepository() throws IOException, InterruptedException {
//        assertEquals(11, repositorySearcherService.getRepositories("ankolodz").size());
    }

    @Test
    void shouldThrowErrorWhenUserNotExist() {
        String exceptionType = "";
        try {
            repositorySearcherService.getRepositories("111notExistingUsers222");
        } catch (Exception e) {
            exceptionType = e.getClass().getName();
        }

        assertEquals(IllegalArgumentException.class.getName(), exceptionType);
    }
}
