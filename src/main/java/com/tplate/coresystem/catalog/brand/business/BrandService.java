package com.tplate.coresystem.catalog.brand.business;

import com.tplate.coresystem.catalog.brand.access.BrandInDto;
import com.tplate.coresystem.catalog.brand.persistence.BrandModel;
import com.tplate.coresystem.catalog.brand.persistence.BrandRepository;
import com.tplate.coresystem.shared.BusinessException;
import com.tplate.coresystem.shared.StringUtil;
import com.tplate.coresystem.shared.services.CreatableService;
import com.tplate.coresystem.shared.services.DeletableService;
import com.tplate.coresystem.shared.services.SearchableService;
import com.tplate.coresystem.shared.services.UpdatableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandService implements
        SearchableService<
                BrandRepository,
                BrandModel>,
        DeletableService<
                BrandRepository,
                BrandModel
                >,
        CreatableService<
                BrandRepository,
                BrandModel,
                BrandInDto
                >,
        UpdatableService<
                BrandRepository,
                BrandModel,
                BrandInDto
                > {

    @Autowired
    private BrandRepository repository;


    @Override
    public BrandRepository getRepository() {
        return this.repository;
    }

    @Override
    public void validateDtoGeneral(BrandInDto dto) {
        if (StringUtil.isEmpty(dto.getName())) {
            throw new BusinessException("name is required");
        }
    }

    @Override
    public void validateDtoForCreate(BrandInDto dto) {
        if (this.getRepository().existsByName(dto.getName())) {
            throw new BusinessException("name exists");
        }
    }

    @Override
    public void validateDtoAndIdForUpdate(BrandInDto dto, Long id) {

        if (!this.getRepository().existsById(id)) {
            throw new BusinessException("Brand id %s not exist".formatted(id));
        }

        if (this.getRepository().existsByNameExcludingId(dto.getName(), id)) {
            throw new BusinessException("name exists");
        }

    }

    @Override
    public BrandModel buildModelByDto(BrandInDto dto) {
        return
                this.mapModelByDto(new BrandModel(), dto);
    }

    @Override
    public BrandModel mapModelByDto(BrandModel model, BrandInDto dto) {

        model.setName(dto.getName());

        return model;
    }

}
