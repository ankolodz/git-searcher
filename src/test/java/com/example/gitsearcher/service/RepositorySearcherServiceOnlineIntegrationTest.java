package com.example.gitsearcher.service;

import org.junit.jupiter.api.Test;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.stop.Stop.stopQuietly;

@SpringBootTest
class RepositorySearcherServiceOnlineIntegrationTest {

    private static final ClientAndServer proxy = ClientAndServer.startClientAndServer();

    @Autowired
    private RepositorySearcherService repositorySearcherService;

    @BeforeClass
    public static void startProxy() {
        System.setProperty("https://api.github.com", "127.0.0.1");
    }

    @AfterClass
    public static void stopProxy() {
        stopQuietly(proxy);
        System.clearProperty("https://api.github.com");
    }

    @BeforeTest
    public void startMockServer() {
        proxy.reset();
    }

    @Test
    void shouldReturnAllRepository() throws IOException, InterruptedException {
        proxy.when(request()
                        .withPath("users/ankolodz/repos"))
                .respond(
                        response().withBody(StaticResponse.EXAMPLE_REPOSITORY_RESPONSE)
                );

        proxy.when(request().withPath("/branches"))
                .respond(response().withBody(StaticResponse.EXAMPLE_BRANCH_RESPONSE));

        assertEquals(9, repositorySearcherService.getRepositories("ankolodz").size());
    }

    @Test
    void shouldThrowErrorWhenUserNotExist() {
        String exceptionType = "";
        try {
            proxy.when(request()
                            .withPath("users/111notExistingUsers222/repos"))
                    .respond(
                            response().withStatusCode(404)
                    );

            repositorySearcherService.getRepositories("111notExistingUsers222");
        } catch (Exception e) {
            exceptionType = e.getClass().getName();
        }

        assertEquals(IllegalArgumentException.class.getName(), exceptionType);
    }
}
