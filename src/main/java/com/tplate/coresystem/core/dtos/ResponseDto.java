package com.tplate.coresystem.core.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Getter
@Setter
@Builder
// Goal: represent a response returned by the API.
public class ResponseDto {
    private String message;
    private Object data;

    // Custom Setter for Builder
    public static class ResponseDtoBuilder {

        private static ModelMapper modelMapper;

        public ResponseDtoBuilder() {
            modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setSkipNullEnabled(true)
                    .setMatchingStrategy(MatchingStrategies.LOOSE);
        }

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
