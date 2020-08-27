package com.idexx.demo.controller;

import com.idexx.demo.dto.AlbumResultDto;
import com.idexx.demo.dto.BookResultDto;
import com.idexx.demo.service.AlbumService;
import com.idexx.demo.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@AllArgsConstructor
public class SearchController {
    private final AlbumService albumService;
    private final BookService bookService;

    @GetMapping("/")
    public ModelAndView welcomePage() {
        ModelMap model = new ModelMap();
        model.addAttribute("message", "Welcome page");
        return new ModelAndView("welcomePage", model);
//        return "welcomePage";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String term, ModelMap model) {
        AlbumResultDto albums = albumService.getAlbums(term);
        BookResultDto books = bookService.getBooks(term);
        model.addAttribute("attribute", "123445");
//        return new ModelAndView("result", model);
        return new ModelAndView("searchResult", model);
    }
}
