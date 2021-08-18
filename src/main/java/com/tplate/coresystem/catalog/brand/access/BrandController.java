package com.tplate.coresystem.catalog.brand.access;

import com.tplate.coresystem.catalog.brand.business.BrandService;
import com.tplate.coresystem.catalog.brand.persistence.BrandModel;
import com.tplate.coresystem.catalog.brand.persistence.BrandRepository;
import com.tplate.coresystem.shared.Endpoints;
import com.tplate.coresystem.shared.controllers.CreatableController;
import com.tplate.coresystem.shared.controllers.DeletableController;
import com.tplate.coresystem.shared.controllers.SearchableController;
import com.tplate.coresystem.shared.controllers.UpdatableController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Endpoints.BRANDS)
@ConditionalOnExpression("${controller.brand.enabled:false}")
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
