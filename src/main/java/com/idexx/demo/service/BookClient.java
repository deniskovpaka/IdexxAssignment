package com.idexx.demo.service;

import com.idexx.demo.config.FeignClientConfig;
import com.idexx.demo.dto.BookResultDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${google.books.service.name}", url = "${google.books.service.url}",
        configuration = FeignClientConfig.class, fallback = BookClient.BookClientFallback.class)
public interface BookClient {
    @GetMapping(value = "${google.books.service.endpoint}")
    BookResultDto getBooks(@RequestParam String q, @RequestParam int maxResults);

    @Slf4j
    @Component
    class BookClientFallback implements BookClient {
        @Override
        public BookResultDto getBooks(String q, int maxResults) {
            log.warn("BookClientFallback.getBooks," +
                    "message=\"Smth went wrong with request params\", q={} and maxResults={}", q, maxResults);
            return new BookResultDto();
        }
    }
}
