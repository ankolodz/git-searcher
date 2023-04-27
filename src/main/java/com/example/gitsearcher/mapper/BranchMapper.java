package com.example.gitsearcher.mapper;

import com.example.gitsearcher.dto.GitBranchDto;
import com.example.gitsearcher.model.GitHubBranchResponse;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper implements Mapper<GitBranchDto, GitHubBranchResponse> {

    @Override
    public GitBranchDto toDto(GitHubBranchResponse entity) {
        return GitBranchDto.builder()
                .name(entity.getName())
                .lastCommitSha(entity.getCommit().getSha())
                .build();
    }
}
