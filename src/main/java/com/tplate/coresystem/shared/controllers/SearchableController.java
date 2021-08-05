package com.tplate.coresystem.shared.controllers;

import com.tplate.coresystem.shared.ResponseMessages;
import com.tplate.coresystem.shared.repositories.SearchableRepository;
import com.tplate.coresystem.shared.services.SearchableService;
import com.tplate.coresystem.shared.dtos.OutDto;
import com.tplate.coresystem.shared.dtos.ResponseDto;
import com.tplate.coresystem.shared.BaseModel;
import org.springframework.web.bind.annotation.*;

public interface SearchableController<
        S extends SearchableService<R, E>,
        R extends SearchableRepository<E>,
        E extends BaseModel,
        O extends OutDto
        > {


    public S getService();
    public Class<O> getClassOutDTO();


    @GetMapping("/{id}")
    default public ResponseDto findById(@PathVariable Long id) {

        final Object model = this.getService().findById(id);

        return ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(model, this.getClassOutDTO())
                .build();

    }

    @GetMapping("")
    default public ResponseDto findAll() {

        final Object model = this.getService().findAll();

        return ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(model, this.getClassOutDTO().arrayType())
                .build();
    }

}
