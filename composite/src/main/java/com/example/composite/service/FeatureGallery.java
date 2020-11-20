package com.example.composite.service;


import com.example.gallerys.model.GalleryDto;
import com.example.image.model.ImageDto;

import java.util.List;

public interface FeatureGallery {
    GalleryDto getGallery(int id);

    List<ImageDto> getImage(int galleryId);
}
