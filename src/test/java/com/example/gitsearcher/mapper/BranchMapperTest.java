package com.example.gitsearcher.mapper;

import com.example.gitsearcher.dto.GitBranchDto;
import com.example.gitsearcher.model.GitHubBranchResponse;
import org.junit.jupiter.api.Test;

import static com.example.gitsearcher.service.StaticResponse.getExample;
import static org.assertj.core.api.Assertions.assertThat;

public class BranchMapperTest {

    @Test
    void shouldCorrectMapBranch(){
        BranchMapper branchMapper = new BranchMapper();
        GitHubBranchResponse response = getExample()[0];

        GitBranchDto result = branchMapper.toDto(response);

        GitBranchDto correctResult = new GitBranchDto("master", "c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc");

        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(correctResult);
    }
}
