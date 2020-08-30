package com.idexx.demo.service;

import com.idexx.demo.client.AlbumClient;
import com.idexx.demo.dto.SearchResultDto;
import com.idexx.demo.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AlbumService {
    private final AlbumClient albumClient;

    public List<SearchResultDto> getAlbums(String term, int limit) {
        return Utils.fromAlbumResultDto(albumClient.getAlbums(term, limit));
    }
}
