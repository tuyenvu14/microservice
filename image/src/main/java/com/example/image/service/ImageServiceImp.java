package com.example.image.service;


import com.example.image.entity.Image;
import com.example.image.model.ImageDto;
import com.example.image.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageServiceImp implements ImageService {

    private Logger logger = LoggerFactory.getLogger(ImageServiceImp.class);

    @Autowired
    ImageRepository repository;

    @Override
    public ImageDto createImage(ImageDto dto) {
        logger.info("request to create car");
        Image entity = new Image();
        entity = entity.builder()
                .title(dto.getTitle())
                .url(dto.getUrl())
                .galleryId(dto.getGalleryId())
                .build();
        return convertToDto(repository.save(entity));
    }

    @Override
    public ImageDto updateImage(ImageDto dto) {
        Image updateImage = new Image();
        Image isUpdatedImage = null;
        isUpdatedImage = repository.findById(dto.getId()).orElse(null);

        logger.info("car are queried : {}", updateImage);
        if (isUpdatedImage != null) {
            updateImage.setId(isUpdatedImage.getId());
            updateImage.setTitle(dto.getTitle());
            updateImage.setUrl(dto.getUrl());
            updateImage.setGalleryId(dto.getGalleryId());
            return convertToDto(repository.saveAndFlush(updateImage));
        }
        return null;
    }

    @Override
    public ImageDto deleteImage(int id) {
        Image deletedImage = null;
        deletedImage = repository.findById(id).orElse(null);

        if (deletedImage != null) {
            repository.deleteById(id);
            return convertToDto(deletedImage);
        }
        return null;
    }

    @Override
    public ImageDto findImage(int id) {
        Image imageEntity = null;
        imageEntity = repository.findById(id).orElse(null);

        if (imageEntity != null) {
            return convertToDto(imageEntity);
        }
        return null;
    }

    @Override
    public List<ImageDto> findAllImage() {
        List<Image> imageEntityList = repository.findAll();
        return convertToDtoList(imageEntityList);
    }

    @Override
    public List<ImageDto> findByGalleryId(int galleryId) {
        List<Image> imageEntityList = (List<Image>) repository.findAllByGalleryId(galleryId);
        return convertToDtoList(imageEntityList);
    }

    public ImageDto convertToDto(Image entity) {
        ImageDto dto = new ImageDto();
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setTitle(entity.getTitle());
            dto.setUrl(entity.getUrl());
            dto.setGalleryId(entity.getGalleryId());
        }
        return dto;
    }

    private List<ImageDto> convertToDtoList(List<Image> driverList) {
        if (driverList != null) {
            return driverList.stream().map(this::convertToDto).collect(Collectors.toList());
        }
        return null;
    }
}
