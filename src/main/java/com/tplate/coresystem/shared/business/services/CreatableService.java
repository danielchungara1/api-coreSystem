package com.tplate.coresystem.shared.business.services;

import com.tplate.coresystem.shared.access.dtos.InDto;
import com.tplate.coresystem.shared.access.dtos.OutDto;
import com.tplate.coresystem.shared.persistence.models.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface CreatableService<
        R extends JpaRepository<E, Long>,
        E extends BaseModel,
        I extends InDto
        > {


    public R getRepository();

    public void validateDto(I dto);

    public E buildModelByDto(I dto);

    @Transactional(rollbackOn = Exception.class)
    default public E createByDtoTemplateMethod(I dto) {
        this.validateDto(dto);
        E model = this.buildModelByDto(dto);
        return this.getRepository().save(model);
    }



}
