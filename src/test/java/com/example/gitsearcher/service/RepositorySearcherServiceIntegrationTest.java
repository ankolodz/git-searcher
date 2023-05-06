package com.example.gitsearcher.service;

import com.example.gitsearcher.model.GitHubRepositoryResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.example.gitsearcher.service.StaticResponse.EXAMPLE_BRANCH_RESPONSE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class RepositorySearcherServiceIntegrationTest {

    @MockBean
    GitHubClient gitHubClient;

    @Autowired
    RepositorySearcherService repositorySearcherService;

    @Test
    void downloadOnlyForkRepositoryAndNotDownloadBranches() throws IOException, InterruptedException {
        when(gitHubClient.getUserRepository(any()))
                .thenReturn(getMockedRepository());

        repositorySearcherService.getRepositories("test");

        verify(gitHubClient, times(0)).getBranchesAsync(any(), any());

    }

    @Test
    void shouldAskAboutBranches() throws IOException, InterruptedException, ExecutionException {
        when(gitHubClient.getUserRepository(any()))
                .thenReturn(getMockedNotForkRepository());

        CompletableFuture<HttpResponse<String>> futureResponseMock = mock(CompletableFuture.class);
        HttpResponse<String> httpResponse = mock(HttpResponse.class);

        when(futureResponseMock.get()).thenReturn(httpResponse);
        when(httpResponse.body()).thenReturn(EXAMPLE_BRANCH_RESPONSE);

        when(gitHubClient.getBranchesAsync(any(), any()))
                .thenReturn(futureResponseMock);

        repositorySearcherService.getRepositories("test");

        verify(gitHubClient, times(2)).getBranchesAsync(any(), any());
    }

    private List<GitHubRepositoryResponse> getMockedNotForkRepository() {
        GitHubRepositoryResponse repo1 = new GitHubRepositoryResponse();
        repo1.setName("repo1");
        repo1.setFork(false);

        GitHubRepositoryResponse repo2 = new GitHubRepositoryResponse();
        repo2.setName("repo2");
        repo2.setFork(false);

        return List.of(repo1, repo2);
    }

    private List<GitHubRepositoryResponse> getMockedRepository() {
        GitHubRepositoryResponse repo1 = new GitHubRepositoryResponse();
        repo1.setFork(true);

        GitHubRepositoryResponse repo2 = new GitHubRepositoryResponse();
        repo2.setFork(true);

        return List.of(repo1, repo2);
    }
}
