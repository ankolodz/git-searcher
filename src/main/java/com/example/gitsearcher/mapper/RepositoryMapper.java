package com.example.gitsearcher.mapper;

import com.example.gitsearcher.dto.GitRepositoryDto;
import com.example.gitsearcher.model.GitHubRepositoryResponse;
import org.springframework.stereotype.Component;

@Component
public class RepositoryMapper implements Mapper<GitRepositoryDto, GitHubRepositoryResponse> {

    @Override
    public GitRepositoryDto toDto(GitHubRepositoryResponse entity) {
        return GitRepositoryDto.builder()
                .repositoryName(entity.getName())
                .build();
    }
}
