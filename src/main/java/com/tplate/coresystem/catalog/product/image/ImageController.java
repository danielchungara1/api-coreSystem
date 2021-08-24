package com.tplate.coresystem.catalog.product.image;

import com.tplate.coresystem.catalog.product.ProductOutDto;
import com.tplate.coresystem.shared.Endpoints;
import com.tplate.coresystem.shared.ResponseMessages;
import com.tplate.coresystem.shared.dtos.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(Endpoints.PRODUCTS)
@Slf4j
public class ImageController {

    @Autowired
    private ImageService service;

    private final Class CLAZZ_OUT_DTO = ProductOutDto.class;

    @PutMapping(value = "/{productId}/upload-image", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseDto uploadImage(
            @PathVariable Long productId,
            @RequestPart(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "main", required = true) Boolean main
    ) throws IOException {

        ImageModel model = this.service.uploadImage(file, productId, new ImageInDto(main));

        return ResponseDto.builder()
                .message(ResponseMessages.UPLOADED)
                .data(model, ImageOutDto.class)
                .build();
    }


    @GetMapping("/{productId}/download-main-image")
    public ResponseEntity<byte[]> downloadImage(@PathVariable Long productId) {

        ImageModel model = this.service.downloadMainImage(productId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "atachment; filename=\"" + model.getName() + "\"")
                .body(model.getData());

    }
//
//    @GetMapping("/{productId}/images")
//    public ResponseDto getImages(@PathVariable Long id) {
//
//        List<ProductImageModel> images = this.service.findImagesByProductId(id);
//
//        return ResponseDto.builder()
//                .message(ResponseMessages.FETCHED)
//                .data(images, ProductImageOutDto[].class)
//                .build();
//
//    }
//
//    @DeleteMapping("/{idProd}/images/{idImg}")
//    public ResponseSimpleDto deleteImage(@PathVariable Long idProd, @PathVariable Long idImg) {
//        this.service.deleteImageByProductIdAndImageId(idProd, idImg);
//        return ResponseSimpleDto.builder()
//                .message(ResponseMessages.DELETED)
//                .build();
//    }

}
