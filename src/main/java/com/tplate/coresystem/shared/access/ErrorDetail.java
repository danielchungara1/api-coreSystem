package com.tplate.coresystem.shared.access;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.context.request.WebRequest;
import java.util.Date;

@Getter
@Setter
@Builder
// Goal: represent a error returned by the API.
public class ErrorDetail {

    private Date date;
    private String request;

    /**
     * Shortcut for build an ErrorDetail.
     * @param request http request that cause exception
     * @return ErrorDetail
     */
    public static ErrorDetail buildWithRequest(WebRequest request) {
        return
                ErrorDetail.builder()
                        .date(new Date())
                        .request(request.toString())
                        .build();
    }
}
