package com.idexx.demo.service;

import com.idexx.demo.client.BookClient;
import com.idexx.demo.dto.SearchResultDto;
import com.idexx.demo.util.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookService {
    private final BookClient bookClient;

    public List<SearchResultDto> getBooks(String q, int maxResults) {
        return Utils.fromBookResultDto(bookClient.getBooks(q, maxResults));
    }
}
