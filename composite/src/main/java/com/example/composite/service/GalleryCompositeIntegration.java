package com.example.composite.service;

import com.example.composite.model.GalleryAggreDto;
import com.example.gallerys.model.GalleryDto;
import com.example.image.model.ImageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Component
public class GalleryCompositeIntegration {

    @Autowired
    private Util util;
    private RestOperations restTemplate;
    private static final Logger LOG = LoggerFactory.getLogger(GalleryCompositeIntegration.class);

    @Autowired
    private FeatureGallery featureGallery;

    @Inject
    public GalleryCompositeIntegration(Util util, RestOperations restTemplate) {
        this.util = util;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<?> getGalleryAggregatedDto(int id) {
        LOG.debug("will call get gallery by {}", id);
        GalleryDto galleryDto = featureGallery.getGallery(id);
        if (galleryDto == null) {
            LOG.error("get galleryAggregated fail by id = {}", id);
            return new ResponseEntity<>(HttpStatus.SEE_OTHER.getReasonPhrase(), HttpStatus.SEE_OTHER);
        }
        GalleryAggreDto resultGetById = setDataGalleryAggregated(galleryDto);
        resultGetById.setImages(new ArrayList<>());
        List<ImageDto> listImageDto = featureGallery.getImage(id);
        resultGetById.setImages(listImageDto);

        return util.createOkResponse(resultGetById);
    }

    private GalleryAggreDto setDataGalleryAggregated(GalleryDto galleryDto) {
        GalleryAggreDto galleryAggregatedDto = new GalleryAggreDto();

        if (galleryDto != null) {
            galleryAggregatedDto.setId(galleryDto.getId());
            galleryAggregatedDto.setName(galleryDto.getName());
        }
        return galleryAggregatedDto;
    }
}
