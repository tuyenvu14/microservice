package com.example.gallerys.service;


import com.example.gallerys.model.GalleryDto;

import java.util.List;

public interface GalleryService {
    GalleryDto createGallery(GalleryDto dto);

    GalleryDto updateGallery(GalleryDto dto);

    GalleryDto deleteGallery(int id);

    GalleryDto findGallery(int id);

    List<GalleryDto> findAllGallery();
}
