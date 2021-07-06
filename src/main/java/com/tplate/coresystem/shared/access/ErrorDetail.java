package com.tplate.coresystem.shared.access;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@Getter
@Setter
@Builder
public class ErrorDetail {

    private Date date;
    private String request;

    public static Object buildWithRequest(WebRequest request) {
        return
                ErrorDetail.builder()
                        .date(new Date())
                        .request(request.toString())
                        .build();
    }
}
