package com.tplate.coresystem.catalog.product;

import com.tplate.coresystem.core.Endpoints;
import com.tplate.coresystem.core.ResponseMessages;
import com.tplate.coresystem.core.controllers.CreatableController;
import com.tplate.coresystem.core.controllers.DeletableController;
import com.tplate.coresystem.core.controllers.SearchableController;
import com.tplate.coresystem.core.controllers.UpdatableController;
import com.tplate.coresystem.core.dtos.ResponseDto;
import com.tplate.coresystem.core.dtos.ResponsePageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

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
    private final Class CLAZZ_PAGE_DTO = ProductPageDto.class;

    @Override
    public ProductService getService() {
        return service;
    }

    @Override
    public Class<ProductOutDto> getClassOutDTO() {
        return this.CLAZZ_OUT_DTO;
    }

    @GetMapping("/page")
    @Transactional
    public ResponseDto findAll(
            @PageableDefault(sort = {"description"}, direction = Sort.Direction.ASC)
                    Pageable pageable,
            ProductSpecification specification) {

        final Object model = this.getService().findAll(pageable, specification);

        return ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(model, this.CLAZZ_PAGE_DTO)
                .build();
    }

}
