package com.tplate.coresystem.catalog.imageProduct.business;

import com.tplate.coresystem.catalog.imageProduct.persistence.ImageProductModel;
import com.tplate.coresystem.catalog.imageProduct.persistence.ImageProductRepository;
import com.tplate.coresystem.catalog.product.persistence.ProductRepository;
import com.tplate.coresystem.shared.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
public class ImageProductService {

    @Autowired
    private ImageProductRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional(rollbackOn = Exception.class)
    public void store(MultipartFile imageFile, Long idProduct) throws IOException {

        ImageProductModel model = ImageProductModel.builder()
                .name(StringUtils.cleanPath(imageFile.getOriginalFilename()))
                .data(imageFile.getBytes())
                .type(imageFile.getContentType())
                .product(this.productRepository
                        .findById(idProduct)
                        .orElseThrow(()-> new BusinessException("Product id %s not exist".formatted(idProduct))))
                .build();

        this.repository.save(model);
    }

    @Transactional
    public ImageProductModel getFile(Long id) {

        return this.repository
                .findById(id)
                .orElseThrow(()-> new BusinessException("Image Id %s not exist".formatted(id)));

    }

}
