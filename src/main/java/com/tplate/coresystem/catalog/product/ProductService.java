package com.tplate.coresystem.catalog.product;

import com.tplate.coresystem.catalog.brand.BrandRepository;
import com.tplate.coresystem.catalog.product.image.ImageService;
import com.tplate.coresystem.core.BusinessException;
import com.tplate.coresystem.core.services.CreatableService;
import com.tplate.coresystem.core.services.DeletableService;
import com.tplate.coresystem.core.services.SearchableService;
import com.tplate.coresystem.core.services.UpdatableService;
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
    private ImageService imageService;

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

        return this.mapModelByDto(new ProductModel(), dto);

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
    public ProductModel findById(Long productId) {

        ProductModel model = this.repository
                .findById(productId)
                .orElseThrow(
                        () -> new BusinessException("Product id %s not exist.".formatted(productId))
                );
        model.getImages().stream()
                .forEach(imageModel -> imageModel.setUrl(this.imageService.getUrl(productId, imageModel.getId())));
        return model;

    }

    @Transactional
    public List<ProductModel> findAll() {

        List<ProductModel> products = this.repository.findAll();
        products.forEach(
                p -> p.getImages().stream().forEach(
                        i -> i.setUrl(this.imageService.getUrl(p.getId(), i.getId()))
                )
        );
        return products;

    }

    @Transactional
    public void validateIfExist(Long productId) {

        if (!this.repository.existsById(productId)) {
            throw new BusinessException("Product if %s not exist.".formatted(productId));
        }

    }
}
