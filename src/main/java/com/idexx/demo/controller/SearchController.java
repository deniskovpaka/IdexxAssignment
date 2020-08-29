package com.idexx.demo.controller;

import com.idexx.demo.dto.SearchResultDto;
import com.idexx.demo.service.AlbumService;
import com.idexx.demo.service.BookService;
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
    public static final String SEARCH_RESULT = "searchResult";
    private final AlbumService albumService;
    private final BookService bookService;

    @GetMapping("/")
    public ModelAndView welcomePage() {
        return new ModelAndView("welcomePage");
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String term, ModelMap model) {
        List<SearchResultDto> albums = albumService.getAlbums(term);
        List<SearchResultDto> books = bookService.getBooks(term);
        List<SearchResultDto> searchResult = Stream.of(albums, books) // todo move to util class
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(SearchResultDto::getTitle))
                .collect(Collectors.toList());
        model.addAttribute(SEARCH_RESULT, searchResult);
        return new ModelAndView(SEARCH_RESULT, model);
    }
}
