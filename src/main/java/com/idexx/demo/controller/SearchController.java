package com.idexx.demo.controller;

import com.idexx.demo.config.ConfigProperties;
import com.idexx.demo.dto.SearchResultDto;
import com.idexx.demo.service.AlbumService;
import com.idexx.demo.service.BookService;
import com.idexx.demo.util.Utils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
public class SearchController {
    public static final String SEARCH_URI = "/search";
    public static final String WELCOME_PAGE_URI = "/";
    public static final String SEARCH_RESULT_MODEL_NAME = "searchResult";
    public static final String WELCOME_PAGE_MODEL_NAME = "welcomePage";

    private final AlbumService albumService;
    private final BookService bookService;
    private final ConfigProperties configProperties;

    @GetMapping(WELCOME_PAGE_URI)
    public ModelAndView welcomePage() {
        return new ModelAndView(WELCOME_PAGE_MODEL_NAME);
    }

    @ApiOperation(value = "search API", notes = "Search books and albums by search criteria")
    @GetMapping(SEARCH_URI)
    public ModelAndView search(@ApiParam(
            name = "term",
            type = "String",
            value = "search input",
            example = "Java",
            required = true) @RequestParam String term) {
        List<SearchResultDto> albums = albumService.getAlbums(term, configProperties.getLimit());
        List<SearchResultDto> books = bookService.getBooks(term, configProperties.getLimit());
        ModelMap model = new ModelMap();
        model.addAttribute(SEARCH_RESULT_MODEL_NAME, Utils.mergeSearchResultsDto(albums, books));
        return new ModelAndView(SEARCH_RESULT_MODEL_NAME, model);
    }
}
