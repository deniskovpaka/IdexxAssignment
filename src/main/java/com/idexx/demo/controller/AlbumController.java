//package com.idexx.demo.controller;
//
//import com.idexx.demo.dto.AlbumResultDto;
//import com.idexx.demo.service.AlbumService;
//import lombok.AllArgsConstructor;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//@RestController
//@AllArgsConstructor
//public class AlbumController {
//    private final AlbumService albumService;
//
//    @GetMapping("/albums")
//    public ModelAndView getAlbums(ModelMap model) {
//        AlbumResultDto albums = albumService.getAlbums("jack+johnson"); // todo add RequestParam
//        model.addAttribute("attribute", albums.getResults());
//        return new ModelAndView("albumView", model);
//    }
//}
