package com.idexx.demo.util;

import com.idexx.demo.dto.AlbumResultDto;
import com.idexx.demo.dto.BookResultDto;
import com.idexx.demo.dto.SearchProduct;
import com.idexx.demo.dto.SearchResultDto;
import com.idexx.demo.exception.SearchResultException;
import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class Utils {
    public List<SearchResultDto> mergeSearchResultsDto(List<SearchResultDto> first, List<SearchResultDto> second) {
        if (first.size() == 0 && second.size() == 0) {
            throw new SearchResultException();
        }
        Stream<List<SearchResultDto>> res = (first.size() == 0) ? Stream.of(second)
                : ((second.size() == 0) ? Stream.of(first) : Stream.of(first, second));
        return res
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(SearchResultDto::getTitle))
                .collect(Collectors.toList());
    }

    public List<SearchResultDto> fromBookResultDto(BookResultDto bookResultDto) {
        return Optional.ofNullable(bookResultDto.getItems()
                .stream()
                .map(bookDto -> new SearchResultDto(bookDto.getVolumeInfo().getTitle(),
                        Optional.ofNullable(bookDto.getVolumeInfo().getAuthors()).orElseGet(LinkedList::new).toString(),
                        SearchProduct.BOOK.name()))
                .collect(Collectors.toList()))
                .orElseGet(LinkedList::new);
    }

    public List<SearchResultDto> fromAlbumResultDto(AlbumResultDto albumResultDto) {
        return Optional.ofNullable(albumResultDto.getResults().stream()
                .map(albumDto -> new SearchResultDto(Optional.ofNullable(albumDto.getTrackName()).orElseGet(String::new),
                        albumDto.getArtistName(),
                        SearchProduct.ALBUM.name()))
                .collect(Collectors.toList()))
                .orElseGet(LinkedList::new);
//        return albumResultDto.getResults()
//                .stream()
//                .map(albumDto -> new SearchResultDto(Optional.ofNullable(albumDto.getTrackName()).orElseGet(String::new),
//                        albumDto.getArtistName(),
//                        SearchProduct.ALBUM.name()))
//                .collect(Collectors.toList());
    }
}
