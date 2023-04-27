package com.example.gitsearcher.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GitRepositoryDto {

    private String repositoryName;
    private List<GitBranchDto> branches;
}
