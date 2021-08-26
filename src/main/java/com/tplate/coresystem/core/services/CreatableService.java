package com.tplate.coresystem.core.services;

import com.tplate.coresystem.core.dtos.InDto;
import com.tplate.coresystem.core.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface CreatableService<
        R extends JpaRepository<E, Long>,
        E extends BaseModel,
        I extends InDto
        > {


    public R getRepository();

    public void validateDtoGeneral(I dto);

    public void validateDtoForCreate(I dto);

    public E buildModelByDto(I dto);

    @Transactional(rollbackOn = Exception.class)
    default public E createByDtoUsingTemplateMethod(I dto) {
        // Validation
        this.validateDtoGeneral(dto);
        this.validateDtoForCreate(dto);
        // Building model
        E model = this.buildModelByDto(dto);
        // Save
        return this.getRepository().save(model);
    }


}
