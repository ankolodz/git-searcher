package com.example.gitsearcher.service;

import com.example.gitsearcher.model.GitHubRepositoryResponse;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface GitHubClient {

    List<GitHubRepositoryResponse> getUserRepository(String username) throws IOException, InterruptedException;

    CompletableFuture<HttpResponse<String>> getBranchesAsync(String username, String repositoryName);
}
