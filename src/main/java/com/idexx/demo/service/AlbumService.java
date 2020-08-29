package com.idexx.demo.service;

import com.idexx.demo.client.AlbumClient;
import com.idexx.demo.config.ConfigProperties;
import com.idexx.demo.dto.AlbumResultDto;
import com.idexx.demo.dto.SearchResultDto;
import com.idexx.demo.exception.SearchResultException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AlbumService {
    private static final String ALBUM = "Album";
    private final AlbumClient albumClient;
    private final ConfigProperties configProperties;

    public List<SearchResultDto> getAlbums(String term) {
        AlbumResultDto albums = albumClient.getAlbums(term, configProperties.getLimit());
        if (Objects.isNull(albums.getResultCount()) || Objects.isNull(albums.getResults())) {
            throw new SearchResultException(term);
        }
        return albums.getResults()
                .stream()
                .map(albumDto -> new SearchResultDto(albumDto.getTrackName(), albumDto.getArtistName(), ALBUM))
                .collect(Collectors.toList());
    }
}
