package com.tplate.coresystem.shared.access.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Getter
@Setter
@Builder
// Goal: represent a response returned by the API.
public class ResponseSimpleDto {

    private String message;

}
