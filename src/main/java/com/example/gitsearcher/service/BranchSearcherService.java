package com.example.gitsearcher.service;

import com.example.gitsearcher.model.GitHubBranchResponse;

import java.util.List;

public interface BranchSearcherService {
    List<GitHubBranchResponse> getBranchByIds(String username, List<String> ids);
}
