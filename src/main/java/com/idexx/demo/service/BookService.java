package com.idexx.demo.service;

import com.idexx.demo.client.BookClient;
import com.idexx.demo.dto.BookResultDto;
import com.idexx.demo.dto.SearchProduct;
import com.idexx.demo.dto.SearchResultDto;
import com.idexx.demo.exception.SearchResultException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookService {
    private final BookClient bookClient;

    public List<SearchResultDto> getBooks(String q, int maxResults) {
        BookResultDto books = bookClient.getBooks(q, maxResults);
        if (Objects.isNull(books.getItems())) {
            throw new SearchResultException(q);
        }
        return books.getItems()
                .stream()
                .map(bookDto -> new SearchResultDto(bookDto.getVolumeInfo().getTitle(),
                        Optional.ofNullable(bookDto.getVolumeInfo().getAuthors()).orElseGet(LinkedList::new).toString(), SearchProduct.BOOK.name())) // todo remove [] from list
                .collect(Collectors.toList());
    }
}
