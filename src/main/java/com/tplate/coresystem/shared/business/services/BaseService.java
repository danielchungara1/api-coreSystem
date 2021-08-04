//package com.tplate.coresystem.shared.business.services;
//
//import com.tplate.coresystem.shared.access.dtos.OutDto;
//import com.tplate.coresystem.shared.business.exceptions.BusinessException;
//import com.tplate.coresystem.shared.persistence.models.BaseModel;
//import com.tplate.coresystem.shared.persistence.repositories.BaseRepository;
//import com.tplate.coresystem.shared.persistence.repositories.DeletableRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.transaction.Transactional;
//import java.util.Date;
//import java.util.List;
//
//@Slf4j
//public abstract class BaseService<
//        R extends DeletableRepository<E>,
//        E extends BaseModel,
//        D extends OutDto
//        > {
//
//    @Autowired
//    protected R repository;
//
//    /**
//     * Delete record (soft-delete).
//     * Audit information must be saved.
//     *
//     * @param id record
//     */
//    @Transactional(rollbackOn = Exception.class)
//    public void deleteById(Long id) {
//        if (!this.repository.existsById(id)) {
//            throw new BusinessException("Id %s not exist".formatted(id));
//        }
//        this.repository.deleteById(id, new Date(), "System");
//
//    }
//
//    /**
//     * Goal: Find all records.
//     *
//     * @return all records
//     */
//    @Transactional(rollbackOn = Exception.class)
//    public List<E> findAll() {
//        return this.repository.findAll();
//    }
//
//    /**
//     * Find record by id (soft-deleted records are not included)
//     *
//     * @param id record
//     * @return record
//     */
//    @Transactional(rollbackOn = Exception.class)
//    public E findById(Long id) {
//
//        return this.repository.findById(id)
//                .orElseThrow(() -> new BusinessException("Id %s not exist.".formatted(id)));
//
//    }
//
//    /**
//     * Create record by dto
//     * @param dto data
//     */
//    public abstract void create(D dto);
//
//    /**
//     * Update record by dto & id
//     * @param dto data
//     * @param id record
//     */
//    public abstract void update(D dto, Long id);
//
//}
