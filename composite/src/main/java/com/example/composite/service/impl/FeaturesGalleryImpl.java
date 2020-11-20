package com.example.composite.service.impl;


import com.example.composite.service.FeatureGallery;
import com.example.composite.service.Util;
import com.example.gallerys.model.GalleryDto;
import com.example.image.model.ImageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;


import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@Service
public class FeaturesGalleryImpl implements FeatureGallery {

    private Util util;
    private final RestOperations restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(FeaturesGalleryImpl.class);
    private static final int SIZE = 20;
    private static final String IMAGE_DOMAIN = "http://localhost:8200";
    private static final String GALLERY_DOMAIN = "http://localhost:8800";

    @Inject
    public FeaturesGalleryImpl(Util util, RestOperations restTemplate) {
        this.util = util;
        this.restTemplate = restTemplate;
    }


    @Override
    public GalleryDto getGallery(int id) {
        String getGalleryUrl = GALLERY_DOMAIN + "api/v1/gallerys/" + id;
        logger.debug("get gallery from url:" + getGalleryUrl);
        try {
            ResponseEntity<GalleryDto> result = restTemplate.getForEntity(getGalleryUrl, GalleryDto.class);
            if (result.getStatusCode().is2xxSuccessful()) {
                logger.info("get Gallery http-status: " + result.getStatusCode());
                logger.debug("post Gallery body: " + result.getBody());

                GalleryDto responseGalleryDtoData = result.getBody();
                return responseGalleryDtoData;
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ImageDto> getImage(int galleryId) {
        String getImageUrl = IMAGE_DOMAIN + "api/v1/images/" + galleryId + "/gallery_id";
        logger.debug("get gallery from url:" + getImageUrl);
        try {
            ResponseEntity<ImageDto[]> result = restTemplate.getForEntity(getImageUrl, ImageDto[].class);
            if (result.getStatusCode().is2xxSuccessful()) {
                logger.info("get Image http-status: " + result.getStatusCode());
                logger.debug("post Image body: " + result.getBody());

                List<ImageDto> responseImageDtoData = Arrays.asList(result.getBody());
                return responseImageDtoData;
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
