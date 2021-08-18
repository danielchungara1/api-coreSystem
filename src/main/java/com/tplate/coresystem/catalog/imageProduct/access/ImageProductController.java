package com.tplate.coresystem.catalog.imageProduct.access;

import com.tplate.coresystem.catalog.imageProduct.business.ImageProductService;
import com.tplate.coresystem.catalog.imageProduct.persistence.ImageProductModel;
import com.tplate.coresystem.shared.Endpoints;
import com.tplate.coresystem.shared.ResponseMessages;
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

@RestController
@RequestMapping(Endpoints.IMAGES_PRODUCT)
@ConditionalOnExpression("${controller.image-product.enabled:false}")
public class ImageProductController {

    @Autowired
    private ImageProductService service;

    @PostMapping(value = "/{idProduct}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseDto uploadImage(
            @RequestPart(value = "file", required = true) MultipartFile file,
            @PathVariable Long idProduct
    ) throws IOException {

        ImageProductModel model = this.service.store(file, idProduct);

        return ResponseDto.builder()
                .message(ResponseMessages.UPLOADED)
                .data(model, ImageProductOutDto.class)
                .build();
    }

    @GetMapping("/{idImage}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long idImage) {

        ImageProductModel model = this.service.getFile(idImage);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "atachment; filename=\"" + model.getName() + "\"")
                .body(model.getData());

    }

    @DeleteMapping("/{idImage}")
    public ResponseSimpleDto deleteImage(@PathVariable Long idImage) {
        this.service.deleteImage(idImage);
        return ResponseSimpleDto.builder()
                .message(ResponseMessages.DELETED)
                .build();
    }

}
