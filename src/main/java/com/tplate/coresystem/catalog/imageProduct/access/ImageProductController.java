package com.tplate.coresystem.catalog.imageProduct.access;

import com.tplate.coresystem.catalog.imageProduct.business.ImageProductService;
import com.tplate.coresystem.catalog.imageProduct.persistence.ImageProductModel;
import com.tplate.coresystem.shared.Endpoints;
import com.tplate.coresystem.shared.ResponseMessages;
import com.tplate.coresystem.shared.dtos.ResponseDto;
import com.tplate.coresystem.shared.dtos.ResponseSimpleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(Endpoints.IMAGES_PRODUCT)
public class ImageProductController {

    @Autowired
    private ImageProductService service;

    @PostMapping(value = "/{idProduct}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseSimpleDto uploadImage(
            @RequestPart(value = "file", required = true) MultipartFile file,
            @PathVariable Long idProduct
    ) throws IOException {

        this.service.store(file, idProduct);

        return ResponseSimpleDto.builder()
                .message(ResponseMessages.UPLOADED)
                .build();

    }

    @GetMapping("/{idImage}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long idImage) {

        ImageProductModel model = this.service.getFile(idImage);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "atachment; filename=\"" + model.getName() + "\"")
                .body(model.getData());

    }

}
