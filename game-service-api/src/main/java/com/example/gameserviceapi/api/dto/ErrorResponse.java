package com.example.gameserviceapi.api.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ErrorResponse {

    private Integer codeStatus;
    private String message;

}
