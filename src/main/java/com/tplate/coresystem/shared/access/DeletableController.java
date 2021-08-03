package com.tplate.coresystem.shared.access;

import com.tplate.coresystem.shared.access.dtos.BaseDto;
import com.tplate.coresystem.shared.access.dtos.ResponseDto;
import com.tplate.coresystem.shared.access.dtos.ResponseSimpleDto;
import com.tplate.coresystem.shared.business.services.DeletableService;
import com.tplate.coresystem.shared.business.services.SearchableService;
import com.tplate.coresystem.shared.persistence.models.BaseModel;
import com.tplate.coresystem.shared.persistence.repositories.DeletableRepository;
import com.tplate.coresystem.shared.persistence.repositories.SearchableRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
                .message(Messages.DELETED)
                .build();

    }

}
