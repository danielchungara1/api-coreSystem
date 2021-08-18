package com.tplate.coresystem.catalog.product.access;

import com.tplate.coresystem.catalog.product.business.ProductService;
import com.tplate.coresystem.catalog.product.persistence.ProductImageModel;
import com.tplate.coresystem.catalog.product.persistence.ProductModel;
import com.tplate.coresystem.catalog.product.persistence.ProductRepository;
import com.tplate.coresystem.shared.Endpoints;
import com.tplate.coresystem.shared.ResponseMessages;
import com.tplate.coresystem.shared.controllers.CreatableController;
import com.tplate.coresystem.shared.controllers.DeletableController;
import com.tplate.coresystem.shared.controllers.SearchableController;
import com.tplate.coresystem.shared.controllers.UpdatableController;
import com.tplate.coresystem.shared.dtos.ResponseDto;
import com.tplate.coresystem.shared.dtos.ResponseSimpleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


    @PostMapping(value = "/{id}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadImage(
            @RequestPart(value = "file", required = true) MultipartFile file,
            @PathVariable Long id
    ) throws IOException {

        ProductImageModel model = this.service.saveImage(file, id);

        return ResponseDto.builder()
                .message(ResponseMessages.UPLOADED)
                .data(model, ProductImageOutDto.class)
                .build();
    }


    @GetMapping("/{idProd}/images/{idImg}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long idProd, @PathVariable Long idImg) {

        ProductImageModel model = this.service.findImageByProductIdAndImageId(idProd, idImg);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "atachment; filename=\"" + model.getName() + "\"")
                .body(model.getData());

    }

    @GetMapping("/{id}/images")
    public ResponseDto getImages(@PathVariable Long id) {

        List<ProductImageModel> images = this.service.findImagesByProductId(id);

        return ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(images, ProductImageOutDto[].class)
                .build();

    }

    @DeleteMapping("/{idProd}/images/{idImg}")
    public ResponseSimpleDto deleteImage(@PathVariable Long idProd, @PathVariable Long idImg) {
        this.service.deleteImageByProductIdAndImageId(idProd, idImg);
        return ResponseSimpleDto.builder()
                .message(ResponseMessages.DELETED)
                .build();
    }

}
