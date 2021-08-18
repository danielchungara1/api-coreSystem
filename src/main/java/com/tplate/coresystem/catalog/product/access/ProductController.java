package com.tplate.coresystem.catalog.product.access;

import com.tplate.coresystem.catalog.imageProduct.access.ImageProductOutDto;
import com.tplate.coresystem.catalog.imageProduct.persistence.ImageProductModel;
import com.tplate.coresystem.catalog.product.persistence.ProductRepository;
import com.tplate.coresystem.catalog.product.business.ProductService;
import com.tplate.coresystem.catalog.product.persistence.ProductModel;
import com.tplate.coresystem.shared.Endpoints;
import com.tplate.coresystem.shared.ResponseMessages;
import com.tplate.coresystem.shared.controllers.CreatableController;
import com.tplate.coresystem.shared.controllers.DeletableController;
import com.tplate.coresystem.shared.controllers.SearchableController;
import com.tplate.coresystem.shared.controllers.UpdatableController;
import com.tplate.coresystem.shared.dtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Endpoints.PRODUCTS)
@ConditionalOnExpression("${controller.product.enabled:false}")
public class ProductController implements
        CreatableController<
                ProductService,
                ProductRepository,
                ProductModel,
                ProductOutDto,
                ProductInDto
                        >,
        UpdatableController<
                ProductService,
                ProductRepository,
                ProductModel,
                ProductOutDto,
                ProductInDto
                        >,
        SearchableController<
                ProductService,
                ProductRepository,
                ProductModel,
                ProductOutDto
                        >,
        DeletableController<
                ProductService,
                ProductRepository,
                ProductModel
                        > {

    @Autowired
    private ProductService service;

    private final Class CLAZZ_OUT_DTO = ProductOutDto.class;

    @Override
    public ProductService getService() {
        return service;
    }

    @Override
    public Class<ProductOutDto> getClassOutDTO() {
        return this.CLAZZ_OUT_DTO;
    }

    @GetMapping("/{id}/images")
    public ResponseDto getImages(@PathVariable Long id) {

        List<ImageProductModel> images = this.service.findImagesByIdProduct(id);

        return ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(images, ProductImageOutDto[].class)
                .build();

    }

}
