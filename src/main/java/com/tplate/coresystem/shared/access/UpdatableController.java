package com.tplate.coresystem.shared.access;

import com.tplate.coresystem.shared.access.dtos.InDto;
import com.tplate.coresystem.shared.access.dtos.OutDto;
import com.tplate.coresystem.shared.access.dtos.ResponseDto;
import com.tplate.coresystem.shared.business.services.CreatableService;
import com.tplate.coresystem.shared.business.services.UpdatableService;
import com.tplate.coresystem.shared.persistence.models.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface UpdatableController<
        S extends UpdatableService<R, E, I>,
        R extends JpaRepository<E, Long>,
        E extends BaseModel,
        O extends OutDto,
        I extends InDto
        > {


    public S getService();
    public Class<O> getClassOutDTO();


    @PutMapping("/{id}")
    default public ResponseDto updateByDtoAndId(@RequestBody I dto, @PathVariable Long id) {

        E model = this.getService().updateByDtoAndIdUsingTemplateMethod(dto, id);
        return ResponseDto.builder()
                .message(Messages.UPDATED)
                .data(model, getClassOutDTO())
                .build();
    }

}
