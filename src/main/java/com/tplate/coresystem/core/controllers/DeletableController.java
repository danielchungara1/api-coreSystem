package com.tplate.coresystem.core.controllers;

import com.tplate.coresystem.core.BaseModel;
import com.tplate.coresystem.core.ResponseMessages;
import com.tplate.coresystem.core.dtos.ResponseSimpleDto;
import com.tplate.coresystem.core.repositories.DeletableRepository;
import com.tplate.coresystem.core.services.DeletableService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface DeletableController<
        S extends DeletableService<R, E>,
        R extends DeletableRepository<E>,
        E extends BaseModel
        > {


    public S getService();

    @DeleteMapping("/{id}")
    default public ResponseSimpleDto deleteById(@PathVariable Long id) {

        this.getService().deleteById(id);

        return ResponseSimpleDto.builder()
                .message(ResponseMessages.DELETED)
                .build();

    }

}
