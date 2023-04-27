package com.example.gitsearcher.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class GitHubRepositoryResponse {
    int id;
    String node_id;
    String name;
    String full_name;
    Owner owner;

    @JsonProperty("private")
    boolean myprivate;
    String html_url;
    String description;
    boolean fork;
    String url;
    String archive_url;
    String assignees_url;
    String blobs_url;
    String branches_url;
    String collaborators_url;
    String comments_url;
     String commits_url;
     String compare_url;
     String contents_url;
     String contributors_url;
     String deployments_url;
     String downloads_url;
     String events_url;
     String forks_url;
     String git_commits_url;
     String git_refs_url;
     String git_tags_url;
     String git_url;
     String issue_comment_url;
     String issue_events_url;
     String issues_url;
     String keys_url;
     String labels_url;
     String languages_url;
     String merges_url;
     String milestones_url;
     String notifications_url;
     String pulls_url;
     String releases_url;
     String ssh_url;
     String stargazers_url;
     String statuses_url;
     String subscribers_url;
     String subscription_url;
     String tags_url;
     String teams_url;
     String trees_url;
     String clone_url;
     String mirror_url;
     String hooks_url;
     String svn_url;
     String homepage;
     Object language;
     int forks_count;
     int stargazers_count;
     int watchers_count;
     int size;
     String default_branch;
     int open_issues_count;
     boolean is_template;
     ArrayList<String> topics;
     boolean has_issues;
     boolean has_projects;
     boolean has_wiki;
     boolean has_pages;
     boolean has_downloads;
     boolean has_discussions;
     boolean archived;
     boolean disabled;
     String visibility;
     Date pushed_at;
     Date created_at;
     Date updated_at;
     Permissions permissions;
     SecurityAndAnalysis security_and_analysis;

     @Data
     public static class Owner{
         String login;
         int id;
         String node_id;
         String avatar_url;
         String gravatar_id;
         String url;
         String html_url;
         String followers_url;
         String following_url;
         String gists_url;
         String starred_url;
         String subscriptions_url;
         String organizations_url;
         String repos_url;
         String events_url;
         String received_events_url;
         String type;
         boolean site_admin;
    }

    @Data
     public static class Permissions{
         boolean admin;
         boolean push;
         boolean pull;
    }

    @Data
    public static class SecurityAndAnalysis{
        AdvancedSecurity advanced_security;
        SecretScanning secret_scanning;
        SecretScanningPushProtection secret_scanning_push_protection;
    }

    @Data
    public static class SecretScanning{
        String status;
    }

    @Data
    public static class SecretScanningPushProtection{
        String status;
    }

    @Data
    public static class AdvancedSecurity{
        String status;
    }
}
