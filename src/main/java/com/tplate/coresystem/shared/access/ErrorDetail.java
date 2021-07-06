package com.tplate.coresystem.shared.access;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorDetail {

    private LocalDateTime date;
    private String request;

    public static Object buildWithRequest(WebRequest request) {
        return
                ErrorDetail.builder()
                        .date(LocalDateTime.now())
                        .request(request.toString())
                        .build();
    }
}
