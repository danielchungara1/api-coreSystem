package com.tplate.coresystem.shared.services;

import com.tplate.coresystem.shared.dtos.InDto;
import com.tplate.coresystem.shared.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface UpdatableService<
        R extends JpaRepository<E, Long>,
        E extends BaseModel,
        I extends InDto
        > {


    public R getRepository();
    public void validateDtoGeneral(I dto);
    public void validateDtoAndIdForUpdate(I dto, Long id);
    public E mapModelByDto(E model, I dto);

    @Transactional(rollbackOn = Exception.class)
    default public E updateByDtoAndIdUsingTemplateMethod(I dto, Long id) {
        // Validation
        this.validateDtoGeneral(dto);
        this.validateDtoAndIdForUpdate(dto, id);
        // Mapping model
        E model = this.mapModelByDto(this.getRepository().getById(id), dto);
        // Update
        return this.getRepository().save(model);
    }


}
