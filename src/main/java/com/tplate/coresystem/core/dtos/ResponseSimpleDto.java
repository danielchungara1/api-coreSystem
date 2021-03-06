package com.tplate.coresystem.core.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
// Goal: represent a response returned by the API.
public class ResponseSimpleDto {

    private String message;

}
