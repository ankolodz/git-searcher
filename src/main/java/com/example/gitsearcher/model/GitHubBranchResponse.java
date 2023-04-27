package com.example.gitsearcher.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class GitHubBranchResponse {
    String name;
    Commit commit;
    @JsonProperty("protected")
    boolean myprotected;
    Protection protection;
    String protection_url;

    @Data
    public static class Commit{
        public String sha;
        public String url;
    }

    @Data
    public static class Protection{
        public RequiredStatusChecks required_status_checks;
    }

    @Data
    public static class RequiredStatusChecks{
        public String enforcement_level;
        public ArrayList<String> contexts;
    }
}
