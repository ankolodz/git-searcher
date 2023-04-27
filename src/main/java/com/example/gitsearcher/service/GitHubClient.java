package com.example.gitsearcher.service;

import com.example.gitsearcher.model.GitHubBranchResponse;
import com.example.gitsearcher.model.GitHubRepositoryResponse;

import java.io.IOException;
import java.util.List;

public interface GitHubClient {

    List<GitHubRepositoryResponse> getUserRepository(String username) throws IOException, InterruptedException;

    List<GitHubBranchResponse> getBranches(String repositoryId);
}
