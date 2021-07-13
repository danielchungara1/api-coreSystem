package com.tplate.coresystem.shared.business;

import com.tplate.coresystem.shared.persistence.ParametricRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class ParametricValidator<R extends ParametricRepository> {

    @Autowired
    private R repository;

    /**
     * Check: description is required
     * @param description parametric
     * @throws BusinessException if validations fails
     */
    public void descriptionIsRequired(String description) {

        if (StringUtil.isEmpty(description)) {
            throw new BusinessException("description is required");
        }
    }

    /**
     * Check: displayName is required
     * @param displayName parametric
     * @throws BusinessException if validations fails
     */
    public void displayNameIsRequired(String displayName) {

        if (StringUtil.isEmpty(displayName)) {
            throw new BusinessException("displayName is required");
        }
    }

    /**
     * Check: name is required and unique.
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
     * @param name parametric
     * @param id parametric excluding in the validation
     * @throws BusinessException if validations fails
     */
    public void nameIsRequiredAndUnique(String name, Long id) {

        this.nameIsRequired(name);

        if (this.repository.existsByNameExcludingId(name, id)) {
            throw new BusinessException("name exists");
        }
    }


    /**
     * Check: name is required
     * @param name parametric
     * @throws BusinessException if validations fails
     */
    public void nameIsRequired(String name) {

        if (StringUtil.isEmpty(name)) {
            throw new BusinessException("name is required");
        }
    }

    /**
     * Check: id parametric must exist
     * @param id parametric
     * @throws BusinessException if validation fails
     */
    public void idMustExist(Long id) {

        if (!this.repository.existsById(id)) {
            throw new BusinessException("id does not exist");
        }
    }

    /**
     * Check: collection ids, all of them must exist
     * @param ids list of ids
     * @throws BusinessException if validation fails
     */
    public void idListMustExist(List<Long> ids) {
        ids.forEach(this::idMustExist);
    }

}
