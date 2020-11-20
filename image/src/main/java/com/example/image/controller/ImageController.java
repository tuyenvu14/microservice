package com.example.image.controller;

import com.example.image.model.ImageDto;
import com.example.image.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("api/v1/")
public class ImageController {
    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "images", produces = "application/json")
    public ResponseEntity<?> createImage(@RequestBody ImageDto imageDto) {
        logger.info("created new image : {}", imageDto);

        try {
            ImageDto createdImageDto = imageService.createImage(imageDto);
            logger.info("create successfully");

            return new ResponseEntity<>(createdImageDto, HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("error : {}", e);
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(), HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(value = "images", produces = "application/json")
    public ResponseEntity<?> updateImage(@RequestBody ImageDto dto) {
        logger.info("update image = {}", dto);

        ImageDto updatedImageDto = imageService.updateImage(dto);

        if (updatedImageDto != null) {
            logger.info("update successfully");
            return new ResponseEntity<>(updatedImageDto, HttpStatus.OK);
        } else {
            logger.error("update error");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/images/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable int id) {
        logger.info("delete image with id = {}", id);

        ImageDto deletedImageDto = imageService.deleteImage(id);

        if (deletedImageDto != null) {
            logger.info("delete successfully");
            return new ResponseEntity<>(deletedImageDto, HttpStatus.OK);
        } else {
            logger.error("delete failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/images/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable int id) {
        logger.info("get image by id : {}", id);

        ImageDto imageDto = imageService.findImage(id);

        if (imageDto != null) {
            logger.info("get image by id successfully");
            return new ResponseEntity<>(imageDto, HttpStatus.OK);
        } else {
            logger.error("get image by id failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/images", produces = "application/json")
    public ResponseEntity<?> findAll() {
        logger.info("get all images");

        List<ImageDto> listImageDto = imageService.findAllImage();

        if (listImageDto.size() != 0) {
            logger.info("get all image successfully");
            return new ResponseEntity<>(listImageDto, HttpStatus.OK);
        } else {
            logger.error("get car failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/images/{galleryId}/gallery_id", produces = "application/json")
    public ResponseEntity<?> findAllByGalleryId(@PathVariable int galleryId) {
        logger.info("get all images");

        List<ImageDto> listImageDto = imageService.findByGalleryId(galleryId);

        if (listImageDto.size() != 0) {
            logger.info("get all image successfully");
            return new ResponseEntity<>(listImageDto, HttpStatus.OK);
        } else {
            logger.error("get car failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }
}
