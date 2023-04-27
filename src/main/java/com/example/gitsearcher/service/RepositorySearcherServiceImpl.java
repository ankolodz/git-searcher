package com.example.gitsearcher.service;

import com.example.gitsearcher.dto.GitRepositoryDto;
import com.example.gitsearcher.mapper.BranchMapper;
import com.example.gitsearcher.mapper.RepositoryMapper;
import com.example.gitsearcher.model.GitHubBranchResponse;
import com.example.gitsearcher.model.GitHubRepositoryResponse;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RepositorySearcherServiceImpl implements RepositorySearcherService {

    private final GitHubClient gitHubClient;
    private final RepositoryMapper repositoryMapper;
    private final BranchMapper branchMapper;
    private final Gson gson = new Gson();

    @Override
    public List<GitRepositoryDto> getRepositories(String username) throws IOException, InterruptedException {
        List<GitHubRepositoryResponse> userRepository = gitHubClient.getUserRepository(username)
                .stream()
                .filter(r -> BooleanUtils.isFalse(r.isFork()))
                .toList();

        Map<String, CompletableFuture<HttpResponse<String>>> repositoryToBranchPromises = userRepository
                .stream()
                .map(GitHubRepositoryResponse::getName)
                .collect(Collectors.toMap(Function.identity(), repoName -> gitHubClient.getBranchesAsync(username, repoName.toString())));

        return userRepository
                .stream()
                .map(repo ->
                        getGitRepositoryDto(repositoryToBranchPromises, repo))
                .filter(Objects::nonNull)
                .toList();
    }

    private GitRepositoryDto getGitRepositoryDto(Map<String, CompletableFuture<HttpResponse<String>>> repositoryToBranchPromises, GitHubRepositoryResponse repo) {
        try {
            GitRepositoryDto repositoryDto = repositoryMapper.toDto(repo);

        String response = repositoryToBranchPromises.get(repo.getName()).get().body();
        GitHubBranchResponse[] gitBranchDtos = gson.fromJson(response, GitHubBranchResponse[].class);
        repositoryDto.setBranches(branchMapper.toDto(List.of(gitBranchDtos)));

        return repositoryDto;
        } catch (ExecutionException | InterruptedException e) {
            log.error("Error download and parse branch request", e);
        }

        return null;
    }
}
