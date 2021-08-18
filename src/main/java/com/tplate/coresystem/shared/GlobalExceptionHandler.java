package com.tplate.coresystem.shared;

import com.tplate.coresystem.shared.dtos.ErrorDetailDto;
import com.tplate.coresystem.shared.dtos.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@Controller
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    // Business Runtime Exceptions
    @ExceptionHandler({
            BusinessException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto badRequestExceptionHandler(BusinessException e, WebRequest request) {
        return ResponseDto.builder()
                .message(e.getMessage())
                .data(ErrorDetailDto.buildWithRequest(request))
                .build();
    }

    // Invalid Path Variable URL
    @ExceptionHandler({
            MethodArgumentTypeMismatchException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e, WebRequest request) {
        log.error(e.getMessage());
        return ResponseDto.builder()
                .message("Invalid URL.")
                .data(ErrorDetailDto.buildWithRequest(request))
                .build();
    }

    // Invalid Json Body
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
    })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e , WebRequest request) {
        log.error(e.getMessage());
        final String message = "Invalid JSON body.";
        return ResponseDto.builder()
                .message(message)
                .data(ErrorDetailDto.buildWithRequest(request))
                .build();
    }

    // Invalid Dto
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e , WebRequest request) {
        log.error(e.getMessage());
        return ResponseDto.builder()
                .message(e.hasErrors() ? e.getAllErrors().get(0).getDefaultMessage() : "Method Argument invalid.")
                .data(ErrorDetailDto.buildWithRequest(request))
                .build();
    }

    // URL Not Found
    @ExceptionHandler({NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseDto notFoundExceptionHandler(Exception e, WebRequest request) {
        log.error(e.getMessage());
        return ResponseDto.builder()
                .message("Resource not found.")
                .data(ErrorDetailDto.buildWithRequest(request))
                .build();
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto handleMaxSizeException(MaxUploadSizeExceededException e, WebRequest request) {
        log.error(e.getMessage());
        return ResponseDto.builder()
                .message("File too large.")
                .data(ErrorDetailDto.buildWithRequest(request))
                .build();
    }

    // Internal Server Error
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto allExceptionHandler(Exception e , WebRequest request) {
        e.printStackTrace();
        log.error(">>> Internal Server Error {}.", e.getMessage());
        return ResponseDto.builder()
                .message("Unexpected Error.")
                .data(ErrorDetailDto.buildWithRequest(request))
                .build();
    }

}
