package com.example.gitsearcher.service;

import com.example.gitsearcher.model.GitHubBranchResponse;
import com.google.gson.Gson;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StaticResponse {
    public static String EXAMPLE_BRANCH_RESPONSE = "[ { \"name\": \"master\", \"commit\": { \"sha\": \"c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc\", \"url\": \"https://api.github.com/repos/octocat/Hello-World/commits/c5b97d5ae6c19d5c5df71a34c7fbeeda2479ccbc\" }, \"protected\": true, \"protection\": { \"required_status_checks\": { \"enforcement_level\": \"non_admins\", \"contexts\": [ \"ci-test\", \"linter\" ] } }, \"protection_url\": \"https://api.github.com/repos/octocat/hello-world/branches/master/protection\" } ]";

    public static GitHubBranchResponse[] getExample(){
        final Gson gson = new Gson();

        return gson.fromJson(EXAMPLE_BRANCH_RESPONSE, GitHubBranchResponse[].class);
    }
}
