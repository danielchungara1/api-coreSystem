package com.tplate.coresystem.core.services;

import com.tplate.coresystem.core.BaseModel;
import com.tplate.coresystem.core.BusinessException;
import com.tplate.coresystem.core.repositories.SearchableRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface SearchableService<
        R extends SearchableRepository<E>,
        E extends BaseModel
        > {

    public R getRepository();

    public final static int MAX_SIZE_RESULTS = 256; // TODO: Load from properties.

    /**
     * Find all records.
     *
     * @return all records
     */
    @Transactional(rollbackOn = Exception.class)
    default public List<E> findAll() {

        if (this.getRepository().findAll().size() > MAX_SIZE_RESULTS) {
            throw new BusinessException("The amount of results exceeds the maximum supported, use paging instead.");
        }

        return this.getRepository().findAll();

    }

    /**
     * Find record by id (soft-deleted records are not included)
     *
     * @param id record
     * @return record
     */
    @Transactional(rollbackOn = Exception.class)
    default public E findById(Long id) {

        return this.getRepository().findById(id)
                .orElseThrow(() -> new BusinessException("Id %s not exist.".formatted(id)));

    }

}
