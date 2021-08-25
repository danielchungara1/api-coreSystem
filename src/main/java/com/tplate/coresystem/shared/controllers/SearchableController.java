package com.tplate.coresystem.shared.controllers;

import com.tplate.coresystem.shared.BaseModel;
import com.tplate.coresystem.shared.ResponseMessages;
import com.tplate.coresystem.shared.dtos.OutDto;
import com.tplate.coresystem.shared.dtos.ResponseDto;
import com.tplate.coresystem.shared.repositories.SearchableRepository;
import com.tplate.coresystem.shared.services.SearchableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;

public interface SearchableController<
        S extends SearchableService<R, E>,
        R extends SearchableRepository<E>,
        E extends BaseModel,
        O extends OutDto
        > {


    public S getService();
    public Class<O> getClassOutDTO();


    @GetMapping("/{id}")
    @Transactional
    default public ResponseDto findById(@PathVariable Long id) {

        final Object model = this.getService().findById(id);

        return ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(model, this.getClassOutDTO())
                .build();

    }

    @GetMapping("")
    @Transactional
    default public ResponseDto findAll() {

        final Object model = this.getService().findAll();

        return ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(model, this.getClassOutDTO().arrayType())
                .build();
    }

}
