package com.tplate.coresystem.catalog.product.business;

import com.tplate.coresystem.catalog.brand.persistence.BrandRepository;
import com.tplate.coresystem.catalog.imageProduct.persistence.ImageProductModel;
import com.tplate.coresystem.catalog.imageProduct.persistence.ImageProductRepository;
import com.tplate.coresystem.catalog.product.access.ProductInDto;
import com.tplate.coresystem.catalog.product.persistence.ProductModel;
import com.tplate.coresystem.catalog.product.persistence.ProductRepository;
import com.tplate.coresystem.shared.BusinessException;
import com.tplate.coresystem.shared.dtos.ResponseDto;
import com.tplate.coresystem.shared.services.CreatableService;
import com.tplate.coresystem.shared.services.DeletableService;
import com.tplate.coresystem.shared.services.SearchableService;
import com.tplate.coresystem.shared.services.UpdatableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    private ImageProductRepository imageRepository;

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

        if (!this.getRepository().existsById(id)) {
            throw new BusinessException("Product id %s not exist".formatted(id));
        }

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

    @Transactional
    public List<ImageProductModel> findImagesByIdProduct(Long id) {

        if (!this.repository.existsById(id)) {
            throw new BusinessException("Product id %s not exist.".formatted(id));
        }

        return this.imageRepository.findAllByIdProduct(id);
    }
}
