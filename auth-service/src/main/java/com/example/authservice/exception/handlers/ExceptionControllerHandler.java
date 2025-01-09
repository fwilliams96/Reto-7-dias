//package com.example.gameserviceapi.exception.handlers;
//
//import com.example.gameserviceapi.api.dto.ErrorResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@RestControllerAdvice
//@Slf4j
//public class ExceptionControllerHandler {
//
//    @ExceptionHandler(value = GameException.class)
//    ResponseEntity<ErrorResponse> handleError(GameException gameException) {
//        log.error("new Exception", gameException);
//        var errorResponse = ErrorResponse.builder()
//                .codeStatus(gameException.getHttpStatus().value())
//                .message(gameException.getMessage())
//                .build();
//        return ResponseEntity.status(gameException.getHttpStatus()).body(errorResponse);
//    }
//
//}
