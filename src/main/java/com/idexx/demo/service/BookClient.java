package com.idexx.demo.service;

import com.idexx.demo.config.FeignClientConfig;
import com.idexx.demo.dto.AlbumResultDto;
import com.idexx.demo.dto.BookResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${google.books.service.name}",
        url = "${google.books.service.url}", configuration = FeignClientConfig.class) // todo Fallback
public interface BookClient {
    @GetMapping(value = "${google.books.service.endpoint}")
    BookResultDto getAlbums(@RequestParam String q, @RequestParam int maxResults);
}
