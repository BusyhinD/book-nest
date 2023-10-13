package com.book.nest.service.impl;

import com.book.nest.dto.BookDto;
import com.book.nest.dto.CreateBookRequestDto;
import com.book.nest.exception.EntityNotFoundException;
import com.book.nest.mapper.BookMapper;
import com.book.nest.model.Book;
import com.book.nest.repository.BookRepository;
import com.book.nest.repository.search.SpecificationBuilder;
import com.book.nest.service.BookService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final SpecificationBuilder<Book> specBuilder;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(CreateBookRequestDto bookDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(bookDto)));
    }

    @Override
    public BookDto findById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not found book with id " + id)));
    }

    @Override
    public List<BookDto> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDto> findAll(Map<String, String> searchParameters) {
        return bookRepository.findAll(specBuilder.build(searchParameters))
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found book with id " + id));
        Optional.ofNullable(bookDto.getPrice()).ifPresent(book::setPrice);
        Optional.ofNullable(bookDto.getAuthor()).ifPresent(book::setAuthor);
        Optional.ofNullable(bookDto.getTitle()).ifPresent(book::setTitle);
        Optional.ofNullable(bookDto.getIsbn()).ifPresent(book::setIsbn);
        Optional.ofNullable(bookDto.getDescription()).ifPresent(book::setDescription);
        Optional.ofNullable(bookDto.getCoverImage()).ifPresent(book::setCoverImage);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }
}
