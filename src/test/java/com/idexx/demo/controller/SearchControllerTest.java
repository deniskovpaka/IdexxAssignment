package com.idexx.demo.controller;

import com.idexx.demo.config.ConfigProperties;
import com.idexx.demo.dto.SearchResultDto;
import com.idexx.demo.service.AlbumService;
import com.idexx.demo.service.BookService;
import com.idexx.demo.util.UtilsTest;
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

import static com.idexx.demo.controller.SearchController.SEARCH_RESULT_MODEL_NAME;
import static com.idexx.demo.util.UtilsTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

@WebMvcTest(SearchController.class)
class SearchControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;
    @MockBean
    private BookService bookService;
    @MockBean
    private ConfigProperties configProperties;

    @Test
    public void getSearchResultList() throws Exception {
        List<SearchResultDto> searchResultList = List.of(UtilsTest.createSearchResultDto(1));

        Mockito.when(configProperties.getLimit()).thenReturn(1);
        Mockito.when(albumService.getAlbums(anyString(), anyInt())).thenReturn(searchResultList);
        Mockito.when(bookService.getBooks(anyString(), anyInt())).thenReturn(searchResultList);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/search?term=Java"));

        ModelAndView modelAndViewResult = resultActions.andReturn().getModelAndView();
        assertEquals(SEARCH_RESULT_MODEL_NAME, modelAndViewResult.getViewName());
        List<SearchResultDto> resultList = (List<SearchResultDto>) modelAndViewResult.getModelMap().getAttribute(SEARCH_RESULT_MODEL_NAME);
        assertEquals(2, resultList.size());
        for (SearchResultDto resultDto : resultList) {
            assertEquals(SEARCH_TITLE + 1, resultDto.getTitle());
            assertEquals(SEARCH_CREATOR + 1, resultDto.getCreator());
            assertEquals(SEARCH_PRODUCT + 1, resultDto.getProduct());
        }
    }
}