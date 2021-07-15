package com.tplate.coresystem.shared.business.services;

import com.tplate.coresystem.shared.business.exceptions.BusinessException;
import com.tplate.coresystem.shared.business.util.StringUtil;
import com.tplate.coresystem.shared.persistence.repositories.ParametricRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ParametricService<R extends ParametricRepository> extends BaseService<R> {

    @Autowired
    protected R repository;

    /**
     * Check: description is required.
     *
     * @param description parametric.
     * @throws BusinessException if validations fails.
     */
    public void descriptionIsRequired(String description) {

        if (StringUtil.isEmpty(description)) {
            throw new BusinessException("description is required");
        }
    }

    /**
     * Check: displayName is required.
     *
     * @param displayName parametric.
     * @throws BusinessException if validations fails.
     */
    public void displayNameIsRequired(String displayName) {

        if (StringUtil.isEmpty(displayName)) {
            throw new BusinessException("displayName is required");
        }
    }

    /**
     * Check: name is required and unique.
     * Verify that a name has been entered.
     * Verify that there is no record with the same name.
     * The entire table is traversed.
     *
     * @param name parametric
     * @throws BusinessException if validations fails
     */
    public void nameIsRequiredAndUnique(String name) {

        this.nameIsRequired(name);

        if (this.repository.existsByName(name)) {
            throw new BusinessException("name exists");
        }
    }

    /**
     * Check: name is required and unique.
     * Verify that a name has been entered.
     * Verify that there is no record with the same name.
     * The entire table si traversed excluding the one with the provided id.
     *
     * @param name parametric.
     * @param id   parametric is excluded.
     * @throws BusinessException if validations fails
     */
    public void nameIsRequiredAndUnique(String name, Long id) {

        this.nameIsRequired(name);

        if (this.repository.existsByNameExcludingId(name, id)) {
            throw new BusinessException("name exists");
        }
    }


    /**
     * Check: name is required.
     * Verify that a name has been entered.
     *
     * @param name parametric.
     * @throws BusinessException if validations fails.
     */
    public void nameIsRequired(String name) {

        if (StringUtil.isEmpty(name)) {
            throw new BusinessException("name is required");
        }
    }

    /**
     * Inject repository for testing.
     * @param repository
     */
    public void setRepository(R repository) {
        super.setRepository(repository);
        this.repository = repository;
    }
}
