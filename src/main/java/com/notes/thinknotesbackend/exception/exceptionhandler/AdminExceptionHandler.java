package com.notes.thinknotesbackend.exception.exceptionhandler;

import com.notes.thinknotesbackend.util.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class AdminExceptionHandler {
    @ExceptionHandler(value= AuthorizationDeniedException.class)
    ResponseEntity<?> noNormalUserAllowed(AuthorizationDeniedException e){
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ResponseStructure.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.FORBIDDEN.value())
                        .mesg("Not Allowed")
                        .body(e.getMessage())

                        .path("/api/admin")
                        .build());
    }
}
