package com.tplate.coresystem.catalog.product.image;

import com.tplate.coresystem.catalog.product.ProductModel;
import com.tplate.coresystem.catalog.product.ProductRepository;
import com.tplate.coresystem.catalog.product.ProductService;
import com.tplate.coresystem.core.BusinessException;
import com.tplate.coresystem.core.services.DeletableService;
import com.tplate.coresystem.core.services.SearchableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private ProductService productService;

    @Override
    public ImageRepository getRepository() {
        return this.repository;
    }


    @Transactional
    public ImageModel findMainImage(Long productId) {

        ProductModel productModel = this.productService.findById(productId);

        return this.repository.findMainImageByProductId(productId)
                .orElseThrow(() -> new BusinessException("Main image not exist for product id %s.".formatted(productId)));
    }

    @Transactional(rollbackOn = Exception.class)
    public ImageModel addMainImage(MultipartFile file, Long productId) throws IOException {

        ProductModel productModel = this.productService.findById(productId);

        if (this.repository.existMainImageForProductId(productId)) {
            throw new BusinessException("Main image exist for product id %s.".formatted(productId));
        }

        ImageModel model = this.buildImageByFile(file, true);

        productModel.addImage(model);

        model = this.repository.save(model);
        model.setUrl(this.getUrl(productId, model.getId()));
        return model;

    }

    @Transactional(rollbackOn = Exception.class)
    public ImageModel addAlternativeImage(MultipartFile file, Long productId) throws IOException {

        ProductModel productModel = this.productService.findById(productId);

        ImageModel model = this.buildImageByFile(file, false);

        productModel.addImage(model);

        model = this.repository.save(model);
        model.setUrl(this.getUrl(productId, model.getId()));
        return model;

    }

    @Transactional
    public List<ImageModel> findAlternativeImages(Long productId) {

        this.productService.validateIfExist(productId);

        List<ImageModel> images = this.repository.findAlternativesImagesByProductId(productId);
        images.stream().forEach(
                i -> i.setUrl(this.getUrl(productId, i.getId()))
        );
        return images;

    }

    @Transactional
    public List<ImageModel> findAllByProductId(Long productId) {

        this.productService.validateIfExist(productId);

        List<ImageModel> images = this.repository.findAllImagesByProductId(productId);
        images.stream().forEach(
                i -> i.setUrl(this.getUrl(productId, i.getId()))
        );

        return images;
    }


    @Transactional
    public ImageModel findImage(Long productId, Long imageId) {

        ProductModel productModel = this.productService.findById(productId);

        ImageModel model = this.repository.findByImageIdAndProductId(imageId, productId)
                .orElseThrow(() -> new BusinessException("Image id %s not exist for product id %s.".formatted(imageId, productId)));

        model.setUrl(this.getUrl(productId, imageId));
        return model;

    }

    /**
     * Build image url from product id and image id
     *
     * @param productId
     * @param imageId
     * @return image url
     */
    public String getUrl(Long productId, Long imageId) {
        return MvcUriComponentsBuilder
                .fromMethodName(ImageController.class, "findImage", productId, imageId)
                .build()
                .toString();
    }

    /**
     * Aux method for build an image model by file and main flag
     *
     * @param file   of image
     * @param isMain indicates if it is main or alternative
     * @return image model
     * @throws IOException
     */
    private ImageModel buildImageByFile(MultipartFile file, Boolean isMain) throws IOException {

        return ImageModel.builder()
                .name(StringUtils.cleanPath(file.getOriginalFilename()))
                .data(file.getBytes())
                .type(file.getContentType())
                .main(isMain)
                .build();
    }


    @Transactional
    public void deleteImageByProductIdAndImageId(Long productId, Long imageId) {

        if (!this.repository.existByProductIdAndImageId(productId, imageId)) {
            throw new BusinessException("Produc id %s or image id %s not exist.".formatted(productId, imageId));
        }

        this.repository.deleteById(imageId, new Date(), "System");

    }

}
