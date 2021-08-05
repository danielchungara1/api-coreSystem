package com.tplate.coresystem.shared.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@Getter
@Setter
@Builder
// Goal: represent a error returned by the API.
public class ErrorDetailDto {

    private Date date;
    private String request;

    /**
     * Shortcut for build an ErrorDetailDto.
     * @param request http request that cause exception
     * @return ErrorDetailDto
     */
    public static ErrorDetailDto buildWithRequest(WebRequest request) {
        return
                ErrorDetailDto.builder()
                        .date(new Date())
                        .request(request.toString())
                        .build();
    }
}
