package com.tplate.coresystem.shared.controllers;

import com.tplate.coresystem.shared.ResponseMessages;
import com.tplate.coresystem.shared.dtos.InDto;
import com.tplate.coresystem.shared.dtos.OutDto;
import com.tplate.coresystem.shared.dtos.ResponseDto;
import com.tplate.coresystem.shared.services.CreatableService;
import com.tplate.coresystem.shared.BaseModel;
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
