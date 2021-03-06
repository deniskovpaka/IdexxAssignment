package com.idexx.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class BookResultDto {
    @JsonProperty
    private Integer totalItems;
    @JsonProperty
    private List<BookDto> items;
}
