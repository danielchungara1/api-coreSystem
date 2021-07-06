package com.tplate.coresystem.shared.access;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@Builder
// Goal: represent a response returned by the API.
public class ResponseDto {
    private String message;
    private Object data;

    // Custom Setter for Builder
    public static class ResponseDtoBuilder {

        private static final ModelMapper modelMapper = new ModelMapper();

        public ResponseDtoBuilder data(Object data, Class dto){
            this.data = modelMapper.map(data, dto);
            return this;
        }

        public ResponseDtoBuilder data(Object data){
            this.data = data;
            return this;
        }

    }
}
