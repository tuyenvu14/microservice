package com.example.composite.service;


import com.example.composite.model.GalleryAggreDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@Produces(APPLICATION_JSON)
//@Consumes(APPLICATION_JSON)
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(maxAge = 3600)
public class GalleryCompositeService {
    private static final Logger logger = LoggerFactory.getLogger(GalleryCompositeService.class);
    @Autowired
    private GalleryCompositeIntegration integration;

    @Autowired
    private Util utils;

    @GetMapping(value = "/gallery/{id}", produces = "application/json")
    public ResponseEntity<GalleryAggreDto> getGalleryAggregated(@PathVariable int id){
        GalleryAggreDto   galleryGetById = new GalleryAggreDto();

        galleryGetById = getBasicById(id);
        return utils.createOkResponse(galleryGetById);
    }

    private GalleryAggreDto getBasicById(int id){
        ResponseEntity<GalleryAggreDto> responseEntity = (ResponseEntity<GalleryAggreDto>) integration.getGalleryAggregatedDto(id);

        GalleryAggreDto respondedGetGalleryData = null;

        if (!responseEntity.getStatusCode().is2xxSuccessful()){
            logger.error("Call the getPost fail: " + responseEntity.getStatusCode());
        }
        else {
            respondedGetGalleryData = responseEntity.getBody();
            logger.info("get post by uid successfully");
        }

        return respondedGetGalleryData;

    }
}
