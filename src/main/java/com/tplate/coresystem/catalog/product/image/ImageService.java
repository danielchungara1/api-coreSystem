package com.tplate.coresystem.catalog.product.image;

import com.tplate.coresystem.catalog.product.ProductModel;
import com.tplate.coresystem.catalog.product.ProductRepository;
import com.tplate.coresystem.shared.BusinessException;
import com.tplate.coresystem.shared.services.DeletableService;
import com.tplate.coresystem.shared.services.SearchableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class ImageService implements
        SearchableService<
                ImageRepository,
                ImageModel>,
        DeletableService<
                ImageRepository,
                ImageModel
                > {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageRepository repository;

    @Override
    public ImageRepository getRepository() {
        return this.repository;
    }

//    @Transactional
//    public List<ProductImageModel> findImagesByProductId(Long id) {
//
//        this.validateExistenceProductId(id);
//
//        return this.imageRepository.findAllByIdProduct(id);
//    }
//
    @Transactional
    public ImageModel downloadMainImage(Long productId) {

        ProductModel productModel = this.productRepository
                .findById(productId)
                .orElseThrow(
                        () -> new BusinessException("Product id %s not exist.".formatted(productId))
                );

        return productModel.getImages().stream()
                .filter(imageModel -> imageModel.getMain().equals(true) && imageModel.getDeletedAt() == null)
                .findAny()
                .orElseThrow(() -> new BusinessException("Main image not exist."));
    }
//
//    @Transactional
//    public void deleteImageByProductIdAndImageId(Long productId, Long imageId){
//
//        this.validateExistenceProductId(productId);
//        this.validateExistenceImageId(imageId);
//
//        this.imageRepository.deleteById(imageId, new Date(), "System");
//
//    }

    @Transactional(rollbackOn = Exception.class)
    public ImageModel uploadImage(MultipartFile file, Long productId, ImageInDto dto) throws IOException {

        ProductModel productModel = this.productRepository
                .findById(productId)
                .orElseThrow(
                        () -> new BusinessException("Product id %s not exist.".formatted(productId))
                );

        if ( dto.getMain().equals(true)  && this.repository.existMainImageForProductId(productId)) {
            throw new BusinessException("Main image exists for this product.");
        }

        ImageModel imageModel = ImageModel.builder()
                .name(StringUtils.cleanPath(file.getOriginalFilename()))
                .data(file.getBytes())
                .type(file.getContentType())
                .main(dto.getMain())
                .build();

        this.productRepository.findById(productId).get().getImages().add(imageModel);

        return this.repository.save(imageModel);
    }

//    public void validateExistenceProductId(Long productId) {
//        if (!this.repository.existsById(productId)) {
//            throw new BusinessException("Product id %s not exist.".formatted(productId));
//        }
//    }
//
//    public void validateExistenceImageId(Long imageId) {
//        if (!this.imageRepository.existsById(imageId)) {
//            throw new BusinessException("Image id %s not exist.".formatted(imageId));
//        }
//    }
//
//    /**
//     * Delete record by id
//     *
//     * @param id record
//     * @return record
//     */
//    @Override
//    @Transactional(rollbackOn = Exception.class)
//    public void deleteById(Long id) {
//
//        if (!this.getRepository().existsById(id)) {
//            throw new BusinessException("Id %s not exist".formatted(id));
//        }
//
//        this.getRepository().deleteById(id, new Date(), "System");
//
//        this.imageRepository.deleteAllByProductId(id, new Date(), "System");
//
//    }
}
