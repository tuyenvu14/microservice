package com.example.gallerys.controller;

import com.example.gallerys.model.GalleryDto;
import com.example.gallerys.service.GalleryService;
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
public class GalleryController {
    private static final Logger logger = LoggerFactory.getLogger(GalleryController.class);

    @Autowired
    private GalleryService imageService;

    @PostMapping(value = "gallerys", produces = "application/json")
    public ResponseEntity<?> createImage(@RequestBody GalleryDto galleryDto) {
        logger.info("created new gallery : {}", galleryDto);

        try {
            GalleryDto createdGalleryDto = imageService.createGallery(galleryDto);
            logger.info("create successfully");

            return new ResponseEntity<>(createdGalleryDto, HttpStatus.OK);
        } catch (NullPointerException e) {
            logger.error("error : {}", e);
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(), HttpStatus.SEE_OTHER);
        }
    }

    @PutMapping(value = "gallerys", produces = "application/json")
    public ResponseEntity<?> updateImage(@RequestBody GalleryDto dto) {
        logger.info("update gallery = {}", dto);

        GalleryDto updatedGalleryDto = imageService.updateGallery(dto);

        if (updatedGalleryDto != null) {
            logger.info("update successfully");
            return new ResponseEntity<>(updatedGalleryDto, HttpStatus.OK);
        } else {
            logger.error("update error");
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @DeleteMapping(value = "/gallerys/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable int id) {
        logger.info("delete gallery with id = {}", id);

        GalleryDto deletedGalleryDto = imageService.deleteGallery(id);

        if (deletedGalleryDto != null) {
            logger.info("delete successfully");
            return new ResponseEntity<>(deletedGalleryDto, HttpStatus.OK);
        } else {
            logger.error("delete failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/gallerys/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable int id) {
        logger.info("get gallery by id : {}", id);

        GalleryDto galleryDtoDto = imageService.findGallery(id);

        if (galleryDtoDto != null) {
            logger.info("get gallery by id successfully");
            return new ResponseEntity<>(galleryDtoDto, HttpStatus.OK);
        } else {
            logger.error("get gallery by id failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/gallerys", produces = "application/json")
    public ResponseEntity<?> findAll() {
        logger.info("get all images");

        List<GalleryDto> listGalleryDto = imageService.findAllGallery();

        if (listGalleryDto.size() != 0) {
            logger.info("get all image successfully");
            return new ResponseEntity<>(listGalleryDto, HttpStatus.OK);
        } else {
            logger.error("get car failed");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
    }
}
