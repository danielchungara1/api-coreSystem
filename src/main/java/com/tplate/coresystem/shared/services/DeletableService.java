package com.tplate.coresystem.shared.services;

import com.tplate.coresystem.shared.BaseModel;
import com.tplate.coresystem.shared.BusinessException;
import com.tplate.coresystem.shared.repositories.DeletableRepository;

import javax.transaction.Transactional;
import java.util.Date;

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
