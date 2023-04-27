package com.example.gitsearcher.controller;

import com.example.gitsearcher.dto.GitRepositoryDto;
import com.example.gitsearcher.service.RepositorySearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("github/")
public class RepositorySearcherController {

    private final RepositorySearcherService repositorySearcherService;

    @GetMapping(name = "search", consumes = "application/json")
    public ResponseEntity<List<GitRepositoryDto>> searchRepositoryByUser(@RequestParam String username) throws IOException, InterruptedException {

        return ResponseEntity.ok(repositorySearcherService.getRepositories(username));
    }
}
