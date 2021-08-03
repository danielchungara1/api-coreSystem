package com.tplate.coresystem.shared.business.services;

import com.tplate.coresystem.shared.access.dtos.BaseDto;
import com.tplate.coresystem.shared.business.exceptions.BusinessException;
import com.tplate.coresystem.shared.persistence.models.BaseModel;
import com.tplate.coresystem.shared.persistence.repositories.DeletableRepository;
import com.tplate.coresystem.shared.persistence.repositories.SearchableRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

public interface DeletableService<
        R extends DeletableRepository<E>,
        E extends BaseModel
        > {

    public R getRepository();

    /**
     * Delete record by id
     *
     * @param id record
     * @return record
     */
    @Transactional(rollbackOn = Exception.class)
    default public void deleteById(Long id) {

        if (!this.getRepository().existsById(id)) {
            throw new BusinessException("Id %s not exist".formatted(id));
        }

        this.getRepository().deleteById(id, new Date(), "System");

    }

}
