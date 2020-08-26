package com.idexx.demo.service;

import com.idexx.demo.config.FeignClientConfig;
import com.idexx.demo.dto.AlbumResultDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${itunes.album.service.name}",
        url = "${itunes.album.service.url}", configuration = FeignClientConfig.class) // todo Fallback
public interface AlbumClient {
    @GetMapping(value = "${itunes.album.service.endpoint}")
    AlbumResultDto getAlbums(@RequestParam String term, @RequestParam int limit);
}
