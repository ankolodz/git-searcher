package com.example.gitsearcher.dto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GitBranchDto {
    private String name;
    private String lastCommitSha;
}
