package com.book.nest.service;

import com.book.nest.dto.BookDto;
import com.book.nest.dto.BookPageResponseDto;
import com.book.nest.dto.CreateBookRequestDto;
import java.util.List;
import java.util.Map;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    BookDto findById(Long id);

    List<BookDto> findAll();

    BookPageResponseDto findAll(Map<String,String> searchParameters);

    BookDto update(Long id, CreateBookRequestDto bookDto);

    void delete(Long id);
}
