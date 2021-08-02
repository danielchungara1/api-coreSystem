package com.tplate.coresystem.shared.access;

import com.google.common.reflect.TypeToken;
import com.tplate.coresystem.shared.access.dtos.BaseDto;
import com.tplate.coresystem.shared.access.dtos.ResponseDto;
import com.tplate.coresystem.shared.business.services.BaseService;
import com.tplate.coresystem.shared.persistence.models.BaseModel;
import com.tplate.coresystem.shared.persistence.repositories.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
public abstract class BaseController<
        S extends BaseService<R, E, D>,
        R extends BaseRepository<E>,
        E extends BaseModel,
        D extends BaseDto
        > {

    @Autowired
    protected S service;

    protected final Class<? super D> CLAZZ_DTO = new TypeToken<D>(getClass()) {
    }.getRawType();

    @GetMapping("/{id}")
    public ResponseDto findById(@PathVariable Long id) {

        final Object model = this.service.findById(id);

        return ResponseDto.builder()
                .message(Messages.FETCHED)
                .data(model, CLAZZ_DTO)
                .build();
    }

    @GetMapping("")
    public ResponseDto findAll() {

        final Object model = this.service.findAll();

        return ResponseDto.builder()
                .message(Messages.FETCHED)
                .data(model, CLAZZ_DTO.arrayType())
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseDto deleteById(@PathVariable Long id) {

        this.service.deleteById(id);

        return ResponseDto.builder()
                .message(Messages.DELETED)
                .build();
    }

    @PostMapping("")
    public ResponseDto create(@RequestBody D dto) {

        this.service.create(dto);

        return ResponseDto.builder()
                .message(Messages.CREATED)
                .build();
    }

    @PutMapping("/{id}")
    public ResponseDto update(@RequestBody D dto, @PathVariable Long id) {

        this.service.update(dto, id);

        return ResponseDto.builder()
                .message(Messages.UPDATED)
                .build();
    }


}
