package com.notes.thinknotesbackend.util;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseStructure <T> {
    private LocalDateTime timestamp;
    private int status;
    private String mesg;
    private T body;
    private String path;
}
