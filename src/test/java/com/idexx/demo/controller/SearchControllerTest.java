package com.idexx.demo.controller;

import com.idexx.demo.dto.SearchResultDto;
import com.idexx.demo.service.AlbumService;
import com.idexx.demo.service.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.idexx.demo.controller.SearchController.SEARCH_RESULT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(SearchController.class)
class SearchControllerTest {
    public static final String SEARCH_TITLE = "title";
    public static final String SEARCH_CREATOR = "creator";
    public static final String SEARCH_PRODUCT = "product";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;
    @MockBean
    private BookService bookService;

    @Test
    public void getSearchResultList() throws Exception {
        List<SearchResultDto> searchResultList = List.of(createSearchResultDto(1));

        Mockito.when(albumService.getAlbums(anyString())).thenReturn(searchResultList);
        Mockito.when(bookService.getBooks(anyString())).thenReturn(searchResultList);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/search?term=Java"));
        ModelAndView modelAndViewResult = resultActions.andReturn().getModelAndView();
        assertEquals(SEARCH_RESULT, modelAndViewResult.getViewName());
        List<SearchResultDto> resultList = (List<SearchResultDto>) modelAndViewResult.getModelMap().getAttribute(SEARCH_RESULT);
        assertEquals(2, resultList.size());
        for (SearchResultDto resultDto : resultList) {
            assertEquals(SEARCH_TITLE + 1, resultDto.getTitle());
            assertEquals(SEARCH_CREATOR + 1, resultDto.getCreator());
            assertEquals(SEARCH_PRODUCT + 1, resultDto.getProduct());
        }
    }

    private SearchResultDto createSearchResultDto(int number) {
        return SearchResultDto.builder()
                .title(SEARCH_TITLE + number)
                .creator(SEARCH_CREATOR + number)
                .product(SEARCH_PRODUCT + number)
                .build();
    }
}