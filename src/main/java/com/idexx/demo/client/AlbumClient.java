package com.idexx.demo.client;

import com.idexx.demo.config.FeignClientConfig;
import com.idexx.demo.dto.AlbumResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${itunes.album.service.name}", url = "${itunes.album.service.url}",
        configuration = FeignClientConfig.class, fallback = AlbumClient.AlbumClientFallback.class)
public interface AlbumClient {
    @GetMapping(value = "${itunes.album.service.endpoint}")
    AlbumResultDto getAlbums(@RequestParam String term, @RequestParam int limit);

    @Slf4j
    @Component
    class AlbumClientFallback implements AlbumClient {
        @Override
        public AlbumResultDto getAlbums(String term, int limit) {
            log.warn("AlbumClientFallback.getAlbums," +
                    "message=\"Smth went wrong with request params\", term={} and limit={}", term, limit);
            return new AlbumResultDto();
        }
    }
}
