package com.example.gitsearcher.service;

import com.example.gitsearcher.dto.GitRepositoryDto;
import com.example.gitsearcher.mapper.RepositoryMapper;
import com.example.gitsearcher.model.GitHubRepositoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RepositorySearcherServiceImpl implements RepositorySearcherService {

    private final GitHubClient gitHubClient;
    private final RepositoryMapper repositoryMapper;


    @Override
    public List<GitRepositoryDto> getRepositories(String username) throws IOException, InterruptedException {
        List<GitHubRepositoryResponse> userRepository = gitHubClient.getUserRepository(username);
        return repositoryMapper.toDto(userRepository);
    }
}
