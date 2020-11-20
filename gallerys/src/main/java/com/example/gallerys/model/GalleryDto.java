package com.example.gallerys.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleryDto implements Serializable {
    private int id;

    @JsonProperty("name")
    private String name;
}
