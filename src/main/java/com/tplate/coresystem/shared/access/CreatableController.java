package com.tplate.coresystem.shared.access;

import com.tplate.coresystem.shared.access.dtos.InDto;
import com.tplate.coresystem.shared.access.dtos.OutDto;
import com.tplate.coresystem.shared.access.dtos.ResponseDto;
import com.tplate.coresystem.shared.business.services.CreatableService;
import com.tplate.coresystem.shared.persistence.models.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CreatableController<
        S extends CreatableService<R, E, I>,
        R extends JpaRepository<E, Long>,
        E extends BaseModel,
        O extends OutDto,
        I extends InDto
        > {


    public S getService();
    public Class<O> getClassOutDTO();


    @PostMapping("")
    default public ResponseDto createByDto(@RequestBody I dto) {

        Object model = this.getService().createByDtoTemplateMethod(dto);
        return ResponseDto.builder()
                .message(Messages.CREATED)
                .data(model, getClassOutDTO())
                .build();
    }

}
