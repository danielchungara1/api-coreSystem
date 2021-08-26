package com.tplate.coresystem.core.controllers;

import com.tplate.coresystem.core.BaseModel;
import com.tplate.coresystem.core.ResponseMessages;
import com.tplate.coresystem.core.dtos.InDto;
import com.tplate.coresystem.core.dtos.OutDto;
import com.tplate.coresystem.core.dtos.ResponseDto;
import com.tplate.coresystem.core.services.CreatableService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CreatableController<
        S extends CreatableService<R, E, I>,
        R extends JpaRepository<E, Long>,
        E extends BaseModel,
        O extends OutDto,
        I extends InDto
        > {


    public S getService();

    public Class<O> getClassOutDTO();

    @PostMapping("")
    default public ResponseDto createByDto(@RequestBody I dto) {

        Object model = this.getService().createByDtoUsingTemplateMethod(dto);
        return ResponseDto.builder()
                .message(ResponseMessages.CREATED)
                .data(model, getClassOutDTO())
                .build();
    }

}
