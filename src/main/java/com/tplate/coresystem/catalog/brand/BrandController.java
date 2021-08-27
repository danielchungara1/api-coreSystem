package com.tplate.coresystem.catalog.brand;

import com.tplate.coresystem.core.Endpoints;
import com.tplate.coresystem.core.controllers.CreatableController;
import com.tplate.coresystem.core.controllers.DeletableController;
import com.tplate.coresystem.core.controllers.SearchableController;
import com.tplate.coresystem.core.controllers.UpdatableController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.BRANDS)
public class BrandController implements
        CreatableController<
                BrandService,
                BrandRepository,
                BrandModel,
                BrandOutDto,
                BrandInDto
                >,
        UpdatableController<
                BrandService,
                BrandRepository,
                BrandModel,
                BrandOutDto,
                BrandInDto
                >,
        SearchableController<
                BrandService,
                BrandRepository,
                BrandModel,
                BrandOutDto
                >,
        DeletableController<
                BrandService,
                BrandRepository,
                BrandModel
                > {

    @Autowired
    private BrandService service;

    private final Class CLAZZ_OUT_DTO = BrandOutDto.class;

    @Override
    public BrandService getService() {
        return service;
    }

    @Override
    public Class<BrandOutDto> getClassOutDTO() {
        return this.CLAZZ_OUT_DTO;
    }

}
