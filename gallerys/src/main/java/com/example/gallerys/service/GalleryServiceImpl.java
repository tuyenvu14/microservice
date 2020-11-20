package com.example.gallerys.service;

import com.example.gallerys.entity.Gallery;
import com.example.gallerys.model.GalleryDto;
import com.example.gallerys.repository.GalleryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GalleryServiceImpl implements GalleryService {
    private Logger logger = LoggerFactory.getLogger(GalleryServiceImpl.class);

    @Autowired
    GalleryRepository repository;

    @Override
    public GalleryDto createGallery(GalleryDto dto) {
            logger.info("request to create gallery");
            Gallery entity = new Gallery();
            entity = entity.builder()
                    .name(dto.getName())
                    .build();
            return convertToDto(repository.save(entity));
    }

    @Override
    public GalleryDto updateGallery(GalleryDto dto) {
        Gallery updateGallery = new Gallery();
        Gallery isUpdatedGallery = null;
        isUpdatedGallery = repository.findById(dto.getId()).orElse(null);

        logger.info("car are queried : {}", updateGallery);
        if (isUpdatedGallery != null) {
            updateGallery.setId(isUpdatedGallery.getId());
            updateGallery.setName(dto.getName());
            return convertToDto(repository.saveAndFlush(updateGallery));
        }
        return null;
    }

    @Override
    public GalleryDto deleteGallery(int id) {
        Gallery deletedGallery = null;
        deletedGallery = repository.findById(id).orElse(null);

        if (deletedGallery != null) {
            repository.deleteById(id);
            return convertToDto(deletedGallery);
        }
        return null;
    }

    @Override
    public GalleryDto findGallery(int id) {
        Gallery galleryEntity = null;
        galleryEntity = repository.findById(id).orElse(null);

        if (galleryEntity != null) {
            return convertToDto(galleryEntity);
        }
        return null;
    }

    @Override
    public List<GalleryDto> findAllGallery() {
        List<Gallery> galleryEntityList = repository.findAll();
        return convertToDtoList(galleryEntityList);
    }

    public GalleryDto convertToDto(Gallery entity) {
        GalleryDto dto = new GalleryDto();
        if (entity != null) {
            dto.setId(entity.getId());
            dto.setName(entity.getName());
        }
        return dto;
    }

    private List<GalleryDto> convertToDtoList(List<Gallery> driverList) {
        if (driverList != null) {
            return driverList.stream().map(this::convertToDto).collect(Collectors.toList());
        }
        return null;
    }
}
