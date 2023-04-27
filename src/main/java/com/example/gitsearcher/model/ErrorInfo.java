package com.example.gitsearcher.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorInfo {

    int status;
    String message;
}
