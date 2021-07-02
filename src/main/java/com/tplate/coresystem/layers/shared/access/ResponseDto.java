package com.tplate.coresystem.layers.shared.access;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@Builder
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

    }
}
