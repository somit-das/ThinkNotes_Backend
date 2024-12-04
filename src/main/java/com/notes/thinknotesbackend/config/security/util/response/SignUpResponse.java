package com.notes.thinknotesbackend.config.security.util.response;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpResponse {
    private Integer status;
    private String message;

    public SignUpResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
