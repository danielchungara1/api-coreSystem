package com.tplate.coresystem.catalog.product;

import com.tplate.coresystem.catalog.brand.persistence.BrandRepository;
import com.tplate.coresystem.shared.BusinessException;
import com.tplate.coresystem.shared.services.CreatableService;
import com.tplate.coresystem.shared.services.DeletableService;
import com.tplate.coresystem.shared.services.SearchableService;
import com.tplate.coresystem.shared.services.UpdatableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ProductService implements
        SearchableService<
                ProductRepository,
                ProductModel>,
        DeletableService<
                ProductRepository,
                ProductModel
                >,
        CreatableService<
                ProductRepository,
                ProductModel,
                ProductInDto
                >,
        UpdatableService<
                ProductRepository,
                ProductModel,
                ProductInDto
                > {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private BrandRepository brandRepository;

    @Override
    public ProductRepository getRepository() {
        return this.repository;
    }

    @Override
    public void validateDtoGeneral(ProductInDto dto) {

        if (dto.getBrandId() != null && !this.brandRepository.existsById(dto.getBrandId())) {
            throw new BusinessException("brand id %s not exist".formatted(dto.getBrandId()));
        }

    }

    @Override
    public void validateDtoForCreate(ProductInDto dto) {

        if (this.getRepository().existsByCode(dto.getCode())) {
            throw new BusinessException("code exists");
        }
    }

    @Override
    public void validateDtoAndIdForUpdate(ProductInDto dto, Long id) {

        if (this.getRepository().existsByCodeExcludingId(dto.getCode(), id)) {
            throw new BusinessException("code exists");
        }
    }

    @Override
    public ProductModel buildModelByDto(ProductInDto dto) {

        return
                this.mapModelByDto(new ProductModel(), dto);
    }

    @Override
    public ProductModel mapModelByDto(ProductModel model, ProductInDto dto) {

        model.setCode(dto.getCode());
        model.setDescription(dto.getDescription());
        model.setPrice(dto.getPrice());
        model.setStock(dto.getStock());
        model.setBrand(this.brandRepository.findById(dto.getBrandId()).orElse(null));

        return model;
    }


}
