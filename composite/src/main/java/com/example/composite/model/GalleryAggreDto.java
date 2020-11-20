package com.example.composite.model;

import com.example.gallerys.model.GalleryDto;
import com.example.image.model.ImageDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonIgnoreProperties({"id"})
@Data
public class GalleryAggreDto extends GalleryDto {
    @JsonProperty
    @NonNull
    private List<ImageDto> images;
}
