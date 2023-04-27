package com.example.gitsearcher.service;

import com.example.gitsearcher.dto.GitRepositoryDto;

import java.io.IOException;
import java.util.List;

public interface RepositorySearcherService {

        List<GitRepositoryDto> getRepositories(String username) throws IOException, InterruptedException;

}
