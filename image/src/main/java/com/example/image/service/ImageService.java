package com.example.image.service;

import com.example.image.model.ImageDto;

import java.util.List;

public interface ImageService {
    ImageDto createImage(ImageDto dto);

    ImageDto updateImage(ImageDto dto);

    ImageDto deleteImage(int id);

    ImageDto findImage(int id);

    List<ImageDto> findAllImage();

    List<ImageDto> findByGalleryId(int galleryId);
}
