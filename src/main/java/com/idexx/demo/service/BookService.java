package com.idexx.demo.service;

import com.idexx.demo.config.ConfigProperties;
import com.idexx.demo.dto.BookResultDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BookService {
    private final BookClient bookClient;
    private final ConfigProperties configProperties;

    public BookResultDto getBooks(String query) {
        return bookClient.getAlbums(query, configProperties.getLimit());
    }
}
