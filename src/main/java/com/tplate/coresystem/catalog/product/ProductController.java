package com.tplate.coresystem.catalog.product;

import com.tplate.coresystem.shared.Endpoints;
import com.tplate.coresystem.shared.controllers.CreatableController;
import com.tplate.coresystem.shared.controllers.DeletableController;
import com.tplate.coresystem.shared.controllers.SearchableController;
import com.tplate.coresystem.shared.controllers.UpdatableController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.PRODUCTS)
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

}
