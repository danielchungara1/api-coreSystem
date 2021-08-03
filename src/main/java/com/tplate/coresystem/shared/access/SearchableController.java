package com.tplate.coresystem.shared.access;

import com.tplate.coresystem.shared.persistence.repositories.SearchableRepository;
import com.tplate.coresystem.shared.business.services.SearchableService;
import com.tplate.coresystem.shared.access.dtos.BaseDto;
import com.tplate.coresystem.shared.access.dtos.ResponseDto;
import com.tplate.coresystem.shared.persistence.models.BaseModel;
import org.springframework.web.bind.annotation.*;

public interface SearchableController<
        S extends SearchableService<R, E, D>,
        R extends SearchableRepository<E>,
        E extends BaseModel,
        D extends BaseDto
        > {


    public S getService();
    public Class<D> getClassDTO();


    @GetMapping("/{id}")
    default public ResponseDto findById(@PathVariable Long id) {

        final Object model = this.getService().findById(id);

        return ResponseDto.builder()
                .message(Messages.FETCHED)
                .data(model, this.getClassDTO())
                .build();

    }

    @GetMapping("")
    default public ResponseDto findAll() {

        final Object model = this.getService().findAll();

        return ResponseDto.builder()
                .message(Messages.FETCHED)
                .data(model, this.getClassDTO().arrayType())
                .build();
    }

}
