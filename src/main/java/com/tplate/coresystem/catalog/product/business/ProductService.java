package com.tplate.coresystem.catalog.product.business;

import com.tplate.coresystem.catalog.brand.persistence.BrandRepository;
import com.tplate.coresystem.catalog.product.persistence.ProductImageModel;
import com.tplate.coresystem.catalog.product.persistence.ProductImageRepository;
import com.tplate.coresystem.catalog.product.access.ProductInDto;
import com.tplate.coresystem.catalog.product.persistence.ProductModel;
import com.tplate.coresystem.catalog.product.persistence.ProductRepository;
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

    @Autowired
    private ProductImageRepository imageRepository;

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

        this.validateExistenceProductId(id);

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
    public List<ProductImageModel> findImagesByProductId(Long id) {

        this.validateExistenceProductId(id);

        return this.imageRepository.findAllByIdProduct(id);
    }

    @Transactional
    public ProductImageModel findImageByProductIdAndImageId(Long productId, Long imageId) {

        this.validateExistenceProductId(productId);
        this.validateExistenceImageId(imageId);

        return this.imageRepository.findById(imageId).get();
    }

    @Transactional
    public void deleteImageByProductIdAndImageId(Long productId, Long imageId){

        this.validateExistenceProductId(productId);
        this.validateExistenceImageId(imageId);

        this.imageRepository.deleteById(imageId, new Date(), "System");

    }

    @Transactional(rollbackOn = Exception.class)
    public ProductImageModel saveImage(MultipartFile file, Long productId) throws IOException {

        this.validateExistenceProductId(productId);

        if (this.imageRepository.existsByName(file.getOriginalFilename())) {
            throw  new BusinessException("Image exists.");
        }

        ProductImageModel imageModel = ProductImageModel.builder()
                .name(StringUtils.cleanPath(file.getOriginalFilename()))
                .data(file.getBytes())
                .type(file.getContentType())
                .product(this.repository.findById(productId).get())
                .build();
        return this.imageRepository.save(imageModel);
    }

    public void validateExistenceProductId(Long productId) {
        if (!this.repository.existsById(productId)) {
            throw new BusinessException("Product id %s not exist.".formatted(productId));
        }
    }

    public void validateExistenceImageId(Long imageId) {
        if (!this.imageRepository.existsById(imageId)) {
            throw new BusinessException("Image id %s not exist.".formatted(imageId));
        }
    }

    /**
     * Delete record by id
     *
     * @param id record
     * @return record
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteById(Long id) {

        if (!this.getRepository().existsById(id)) {
            throw new BusinessException("Id %s not exist".formatted(id));
        }

        this.getRepository().deleteById(id, new Date(), "System");

        this.imageRepository.deleteAllByProductId(id, new Date(), "System");

    }


}
