package com.tplate.coresystem.catalog.product.image;

import com.tplate.coresystem.catalog.product.ProductOutDto;
import com.tplate.coresystem.core.Endpoints;
import com.tplate.coresystem.core.ResponseMessages;
import com.tplate.coresystem.core.dtos.ResponseDto;
import com.tplate.coresystem.core.dtos.ResponseSimpleDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(Endpoints.PRODUCTS)
@Slf4j
@Tag(name = "product-image-controller")
public class ImageController {

    @Autowired
    private ImageService service;

    private final Class CLAZZ_OUT_DTO = ProductOutDto.class;


    @PostMapping(value = "/{id}/main-image", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseDto addMainImage(
            @PathVariable Long id,
            @RequestPart(value = "file", required = true) MultipartFile file
    ) throws IOException {

        ImageModel model = this.service.addMainImage(file, id);

        return ResponseDto.builder()
                .message(ResponseMessages.UPLOADED)
                .data(model, ImageOutDto.class)
                .build();
    }

    @PostMapping(value = "/{id}/alternative-image", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseDto addAlternativeImage(
            @PathVariable Long id,
            @RequestPart(value = "file", required = true) MultipartFile file
    ) throws IOException {

        ImageModel model = this.service.addAlternativeImage(file, id);

        return ResponseDto.builder()
                .message(ResponseMessages.UPLOADED)
                .data(model, ImageOutDto.class)
                .build();
    }

    @GetMapping("/{id}/images/main-image")
    public ResponseEntity<byte[]> findMainImage(@PathVariable Long id) {

        ImageModel model = this.service.findMainImage(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "atachment; filename=\"" + model.getName() + "\"")
                .body(model.getData());

    }

    @GetMapping("/{id}/images/alternative-images")
    public ResponseDto findAlternativeImages(@PathVariable Long id) {

        List<ImageModel> models = this.service.findAlternativeImages(id);

        return ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(models, ImageOutDto[].class)
                .build();
    }

    @GetMapping("/{id}/images/{imageId}")
    public ResponseEntity<byte[]> findImage(@PathVariable Long id, @PathVariable Long imageId) {

        ImageModel model = this.service.findImage(id, imageId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "atachment; filename=\"" + model.getName() + "\"")
                .body(model.getData());

    }

    @GetMapping("/{id}/images/{imageId}/thumbnail")
    public ResponseEntity<byte[]> findThumbnail(@PathVariable Long id, @PathVariable Long imageId) throws IOException {

        ImageModel model = this.service.findThumbnail(id, imageId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "atachment; filename=\"" + model.getName() + "\"")
                .body(model.getData());

    }

    @GetMapping("/{id}/images")
    public ResponseDto getImages(@PathVariable Long id) {

        List<ImageModel> images = this.service.findAllByProductId(id);

        return ResponseDto.builder()
                .message(ResponseMessages.FETCHED)
                .data(images, ImageOutDto[].class)
                .build();

    }

    @DeleteMapping("/{id}/images/{imageId}")
    public ResponseSimpleDto deleteImage(@PathVariable Long id, @PathVariable Long imageId) {
        this.service.deleteImageByProductIdAndImageId(id, imageId);
        return ResponseSimpleDto.builder()
                .message(ResponseMessages.DELETED)
                .build();
    }

}
