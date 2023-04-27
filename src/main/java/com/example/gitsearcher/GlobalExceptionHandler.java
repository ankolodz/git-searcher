package com.example.gitsearcher;

import com.example.gitsearcher.model.ErrorInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ErrorInfo> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage());
        ErrorInfo errorInfo = ErrorInfo.builder()
                .status(404)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ErrorInfo> handleException(Exception e) {
        log.error(e.getMessage());
        ErrorInfo errorInfo = ErrorInfo.builder()
                .status(500)
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorInfo);
    }

}
