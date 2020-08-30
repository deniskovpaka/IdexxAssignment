package com.idexx.demo.service;

import com.idexx.demo.client.AlbumClient;
import com.idexx.demo.dto.AlbumResultDto;
import com.idexx.demo.dto.SearchProduct;
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
    private final AlbumClient albumClient;

    public List<SearchResultDto> getAlbums(String term, int limit) {
        AlbumResultDto albums = albumClient.getAlbums(term, limit);
        if (Objects.isNull(albums.getResultCount()) || Objects.isNull(albums.getResults())) {
            throw new SearchResultException(term);
        }
        return albums.getResults()
                .stream()
                .map(albumDto -> new SearchResultDto(albumDto.getTrackName(), albumDto.getArtistName(), SearchProduct.ALBUM.name()))
                .collect(Collectors.toList());
    }
}
