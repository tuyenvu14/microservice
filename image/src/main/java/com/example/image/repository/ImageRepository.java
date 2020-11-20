package com.example.image.repository;

import com.example.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
   List <Image> findAllByGalleryId(int galleryId);
}
