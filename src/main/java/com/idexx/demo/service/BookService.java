package com.idexx.demo.service;

import com.idexx.demo.config.ConfigProperties;
import com.idexx.demo.dto.BookResultDto;
import com.idexx.demo.dto.SearchResultDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookService {
    private static final String BOOK = "Book"; // todo may be move to ENUM
    private final BookClient bookClient;
    private final ConfigProperties configProperties;

    public List<SearchResultDto> getBooks(String q) {
        BookResultDto books = bookClient.getBooks(q, configProperties.getLimit());
        return books.getItems()
                .stream()
                .map(bookDto -> new SearchResultDto(bookDto.getVolumeInfo().getTitle(),
                        Optional.ofNullable(bookDto.getVolumeInfo().getAuthors()).orElseGet(LinkedList::new).toString(), BOOK)) // todo remove [] from list
                .collect(Collectors.toList());
    }
}
