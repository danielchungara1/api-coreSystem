package com.tplate.coresystem.shared.access;

import com.tplate.coresystem.shared.persistence.repositories.SearchableRepository;
import com.tplate.coresystem.shared.business.services.SearchableService;
import com.tplate.coresystem.shared.access.dtos.OutDto;
import com.tplate.coresystem.shared.access.dtos.ResponseDto;
import com.tplate.coresystem.shared.persistence.models.BaseModel;
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
                .message(Messages.FETCHED)
                .data(model, this.getClassOutDTO())
                .build();

    }

    @GetMapping("")
    default public ResponseDto findAll() {

        final Object model = this.getService().findAll();

        return ResponseDto.builder()
                .message(Messages.FETCHED)
                .data(model, this.getClassOutDTO().arrayType())
                .build();
    }

}
