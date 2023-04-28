package com.example.gitsearcher.service;

import com.example.gitsearcher.model.GitHubRepositoryResponse;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class GitHubClientImpl implements GitHubClient {

    @Override
    public List<GitHubRepositoryResponse> getUserRepository(String username) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://api.github.com/users/{USERNAME}/repos".replace("{USERNAME}", username);

        HttpRequest.Builder requestBuilder = HttpRequest
                .newBuilder(URI.create(url))
                .GET();
        fillHeader(requestBuilder);

        HttpRequest request = requestBuilder.build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 404){
            throw new IllegalArgumentException(String.format("Username %s not exist", username));
        }

        if(response.statusCode() >= 400){
            throw new RuntimeException(String.format("Api error occurred status %s message %s", response.statusCode(), response.body()));
        }
        Gson gson = new Gson();

        GitHubRepositoryResponse[] repositories =  gson.fromJson(response.body(), GitHubRepositoryResponse[].class);
        return List.of(repositories);
    }

    private void fillHeader(HttpRequest.Builder request) {
        request.header("X-GitHub-Api-Version", "2022-11-28");
        request.header("Accept", "application/vnd.github+json");
    }

    @Override
    public CompletableFuture<HttpResponse<String>> getBranchesAsync(String username, String repositoryName) {
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://api.github.com/repos/{USERNAME}/{REPO}/branches"
                .replace("{USERNAME}", username)
                .replace("{REPO}", repositoryName);

        HttpRequest.Builder requestBuilder = HttpRequest
                .newBuilder(URI.create(url))
                .GET();
        fillHeader(requestBuilder);

        HttpRequest request = requestBuilder.build();


        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }


}
