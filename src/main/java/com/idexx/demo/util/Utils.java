package com.idexx.demo.util;

import com.idexx.demo.dto.*;
import com.idexx.demo.exception.SearchResultException;
import lombok.experimental.UtilityClass;
import org.apache.logging.log4j.util.Strings;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@UtilityClass
public class Utils {
    public final String NOT_AVAILABLE = "N/A";

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
        List<BookDto> bookDtos = Optional.ofNullable(bookResultDto.getItems()).orElseGet(LinkedList::new);
        return bookDtos.stream()
                .map(bookDto -> new SearchResultDto(Optional.ofNullable(bookDto.getVolumeInfo().getTitle()).orElse(NOT_AVAILABLE),
                        Optional.ofNullable(bookDto.getVolumeInfo().getAuthors()).orElse(List.of(NOT_AVAILABLE))
                                .toString().replace("[", Strings.EMPTY).replace("]", Strings.EMPTY),
                        SearchProduct.BOOK.name()))
                .collect(Collectors.toList());
    }

    public List<SearchResultDto> fromAlbumResultDto(AlbumResultDto albumResultDto) {
        List<AlbumDto> albumDtos = Optional.ofNullable(albumResultDto.getResults()).orElseGet(LinkedList::new);
        return albumDtos.stream()
                .map(albumDto -> new SearchResultDto(Optional.ofNullable(albumDto.getTrackName()).orElse(NOT_AVAILABLE),
                        Optional.ofNullable(albumDto.getArtistName()).orElse(NOT_AVAILABLE),
                        SearchProduct.ALBUM.name()))
                .collect(Collectors.toList());
    }
}
