package com.idexx.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AlbumResultDto {
    @JsonProperty
    private Integer resultCount;
    @JsonProperty
    private List<AlbumDto> results;
}
