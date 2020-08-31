package com.idexx.demo.util;

import com.idexx.demo.dto.*;
import com.idexx.demo.exception.SearchResultException;
import org.apache.logging.log4j.util.Strings;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {
    public static final String SEARCH_TITLE = "title";
    public static final String SEARCH_CREATOR = "creator";
    public static final String SEARCH_PRODUCT = "product";

    @Test
    void mergeSearchResultsDtoWhenTwoListProvided() {
        List<SearchResultDto> first = List.of(createSearchResultDto(1));
        List<SearchResultDto> second = List.of(createSearchResultDto(1));

        List<SearchResultDto> resultDtos = Utils.mergeSearchResultsDto(first, second);

        assertEquals(2, resultDtos.size());
        for (SearchResultDto resultDto : resultDtos) {
            assertEquals(SEARCH_TITLE + 1, resultDto.getTitle());
            assertEquals(SEARCH_CREATOR + 1, resultDto.getCreator());
            assertEquals(SEARCH_PRODUCT + 1, resultDto.getProduct());
        }
    }

    @Test
    void returnFirstListWhenSecondIsEmpty() {
        List<SearchResultDto> first = List.of(createSearchResultDto(0),
                createSearchResultDto(1), createSearchResultDto(2));
        List<SearchResultDto> second = Lists.emptyList();

        List<SearchResultDto> resultDtos = Utils.mergeSearchResultsDto(first, second);

        assertEquals(3, resultDtos.size());
        for (int i = 0; i < resultDtos.size(); ++i) {
            SearchResultDto dto = resultDtos.get(i);
            assertEquals(SEARCH_TITLE + i, dto.getTitle());
            assertEquals(SEARCH_CREATOR + i, dto.getCreator());
            assertEquals(SEARCH_PRODUCT + i, dto.getProduct());
        }
    }

    @Test
    void returnSecondListWhenFirstIsEmpty() {
        List<SearchResultDto> first = Lists.emptyList();
        List<SearchResultDto> second = List.of(createSearchResultDto(8));

        List<SearchResultDto> resultDtos = Utils.mergeSearchResultsDto(first, second);

        assertEquals(1, resultDtos.size());
        assertEquals(SEARCH_TITLE + 8, resultDtos.get(0).getTitle());
        assertEquals(SEARCH_CREATOR + 8, resultDtos.get(0).getCreator());
        assertEquals(SEARCH_PRODUCT + 8, resultDtos.get(0).getProduct());
    }

    @Test
    void shouldThrowExceptionIfTwoListAreEmpty() {
        Throwable thrown = assertThrows(SearchResultException.class,
                () -> Utils.mergeSearchResultsDto(Lists.emptyList(), Lists.emptyList()));
        assertNotNull(thrown.getMessage());
    }

    @Test
    void returnSearchResultDtoListFromBookResultDto() {
        BookInfoDto bookInfoDto = new BookInfoDto();
        List<String> authors = List.of("Author1", "Author2");
        bookInfoDto.setAuthors(authors);
        String title = SEARCH_TITLE;
        bookInfoDto.setTitle(title);
        BookDto bookDto = new BookDto();
        bookDto.setVolumeInfo(bookInfoDto);
        BookResultDto bookResultDto = new BookResultDto();
        bookResultDto.setItems(List.of(bookDto));
        bookResultDto.setTotalItems(1);

        List<SearchResultDto> resultDtos = Utils.fromBookResultDto(bookResultDto);

        assertEquals(1, resultDtos.size());
        assertEquals(title, resultDtos.get(0).getTitle());
        assertEquals(authors.toString().replace("[", Strings.EMPTY).replace("]", Strings.EMPTY),
                resultDtos.get(0).getCreator());
        assertEquals(SearchProduct.BOOK.name(), resultDtos.get(0).getProduct());
    }

    @Test
    void returnEmptyListIfBookResultDtoIsEmpty() {
        List<SearchResultDto> resultDtos = Utils.fromBookResultDto(new BookResultDto());
        assertTrue(resultDtos.isEmpty());
    }

    @Test
    void returnSearchResultDtoListWithNotAvailableFieldIfBookInfoDtoNotContainsAuthors() {
        BookInfoDto bookInfoDto = new BookInfoDto();
        String title = SEARCH_TITLE;
        bookInfoDto.setTitle(title);
        BookDto bookDto = new BookDto();
        bookDto.setVolumeInfo(bookInfoDto);
        BookResultDto bookResultDto = new BookResultDto();
        bookResultDto.setItems(List.of(bookDto));
        bookResultDto.setTotalItems(1);

        List<SearchResultDto> resultDtos = Utils.fromBookResultDto(bookResultDto);

        assertEquals(1, resultDtos.size());
        assertEquals(title, resultDtos.get(0).getTitle());
        assertEquals(Utils.NOT_AVAILABLE, resultDtos.get(0).getCreator());
        assertEquals(SearchProduct.BOOK.name(), resultDtos.get(0).getProduct());
    }

    @Test
    void returnSearchResultDtoListFromAlbumResultDto() {
        AlbumDto albumDto = new AlbumDto();
        String artistName = "artistName";
        albumDto.setArtistName(artistName);
        String trackName = "trackName";
        albumDto.setTrackName(trackName);
        AlbumResultDto albumResultDto = new AlbumResultDto();
        albumResultDto.setResults(List.of(albumDto));

        List<SearchResultDto> resultDtos = Utils.fromAlbumResultDto(albumResultDto);

        assertEquals(1, resultDtos.size());
        assertEquals(artistName, resultDtos.get(0).getCreator());
        assertEquals(trackName, resultDtos.get(0).getTitle());
        assertEquals(SearchProduct.ALBUM.name(), resultDtos.get(0).getProduct());
    }

    @Test
    void returnEmptyListIfAlbumResultDtoIsEmpty() {
        List<SearchResultDto> resultDtos = Utils.fromAlbumResultDto(new AlbumResultDto());
        assertTrue(resultDtos.isEmpty());
    }

    @Test
    void returnSearchResultDtoListWithNotAvailableFieldIfAlbumResultDtoNotContainsTrackName() {
        AlbumDto albumDto = new AlbumDto();
        String artistName = "artistName";
        albumDto.setArtistName(artistName);
        AlbumResultDto albumResultDto = new AlbumResultDto();
        albumResultDto.setResults(List.of(albumDto));

        List<SearchResultDto> resultDtos = Utils.fromAlbumResultDto(albumResultDto);

        assertEquals(1, resultDtos.size());
        assertEquals(artistName, resultDtos.get(0).getCreator());
        assertEquals(Utils.NOT_AVAILABLE, resultDtos.get(0).getTitle());
        assertEquals(SearchProduct.ALBUM.name(), resultDtos.get(0).getProduct());
    }

    public static SearchResultDto createSearchResultDto(int number) {
        return SearchResultDto.builder()
                .title(SEARCH_TITLE + number)
                .creator(SEARCH_CREATOR + number)
                .product(SEARCH_PRODUCT + number)
                .build();
    }
}