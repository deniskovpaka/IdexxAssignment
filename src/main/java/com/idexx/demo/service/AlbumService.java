package com.idexx.demo.service;

import com.idexx.demo.config.ConfigProperties;
import com.idexx.demo.dto.AlbumResultDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AlbumService {
    private final AlbumClient albumClient;
    private final ConfigProperties configProperties;

    public AlbumResultDto getAlbums(String term) {
        return albumClient.getAlbums(term, configProperties.getLimit());
    }
}
