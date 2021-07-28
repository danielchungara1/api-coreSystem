package com.tplate.coresystem.shared.business.services;

import com.tplate.coresystem.shared.business.exceptions.BusinessException;
import com.tplate.coresystem.shared.persistence.models.BaseModel;
import com.tplate.coresystem.shared.persistence.repositories.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Slf4j
public abstract class BaseService<R extends BaseRepository> {

    @Autowired
    protected R repository;

    /**
     * Delete record (soft-delete).
     * Audit information must be saved.
     *
     * @param id record
     */
    @Transactional(rollbackOn = Exception.class)
    public void deleteById(Long id) {

        this.idMustExist(id);

        this.repository.deleteById(id, new Date(), "System");

        log.info(">>> Deleting entity with id {}", id);

    }

    /**
     * Check: id must exist.
     * @param id record.
     * @throws BusinessException if validation fails
     */
    @Transactional(rollbackOn = Exception.class)
    public void idMustExist(Long id) {

        if (!this.repository.existsById(id)) {
            throw new BusinessException("id does not exist");
        }
    }

    /**
     * Check: id list must exist.
     * @param ids records.
     * @throws BusinessException if validation fails.
     */
    @Transactional(rollbackOn = Exception.class)
    public void idListMustExist(List<Long> ids) {
        ids.forEach(this::idMustExist);
    }

    /**
     * Get record by id (soft-deleted records are not included)
     *
     * @param id record
     * @return record
     */
    @Transactional(rollbackOn = Exception.class)
    public <E extends BaseModel> E getById(Long id) {
        return (E) this.repository.getById(id);
    }

    /**
     * Goal: Find all records.
     *
     * @return all records
     */
    @Transactional(rollbackOn = Exception.class)
    public List<? extends BaseModel> findAll() {
        return this.repository.findAll();
    }

    /**
     * Inject repository for testing.
     * @param repository
     */
    public void setRepository(R repository) {
        this.repository = repository;
    }

}
