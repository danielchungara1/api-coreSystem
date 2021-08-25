package com.tplate.coresystem.shared.controllers;

import com.tplate.coresystem.shared.BaseModel;
import com.tplate.coresystem.shared.ResponseMessages;
import com.tplate.coresystem.shared.dtos.ResponseSimpleDto;
import com.tplate.coresystem.shared.repositories.DeletableRepository;
import com.tplate.coresystem.shared.services.DeletableService;
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
