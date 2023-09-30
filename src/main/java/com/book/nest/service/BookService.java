package com.book.nest.service;

import com.book.nest.dto.BookDto;
import com.book.nest.dto.CreateBookRequestDto;
import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto bookDto);

    BookDto findById(Long id);

    List<BookDto> findAll();
}
