package com.book.nest.service.impl;

import com.book.nest.dto.book.BookDto;
import com.book.nest.dto.book.BookPageResponseDto;
import com.book.nest.dto.book.CreateBookRequestDto;
import com.book.nest.exception.EntityNotFoundException;
import com.book.nest.mapper.BookMapper;
import com.book.nest.model.Book;
import com.book.nest.repository.BookRepository;
import com.book.nest.repository.search.PageManager;
import com.book.nest.repository.search.SpecificationBuilder;
import com.book.nest.service.BookService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public BookPageResponseDto findAll(Map<String, String> searchParameters) {
        Pageable pageable = PageManager.getPageable(searchParameters.remove("size"),
                searchParameters.remove("page"), searchParameters.remove("sort"));
        return getBookPageResponseDto(
                bookRepository.findAll(
                        specBuilder.build(searchParameters), pageable));
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

    private BookPageResponseDto getBookPageResponseDto(Page<Book> page) {
        BookPageResponseDto bookPageResponseDto = new BookPageResponseDto();
        bookPageResponseDto.setPageNumber(page.getNumber());
        bookPageResponseDto.setPageSize(page.getSize());
        bookPageResponseDto.setTotalPages(page.getTotalPages());
        bookPageResponseDto.setNumberOfBooks(page.getTotalElements());
        bookPageResponseDto.setLastPage(page.isLast());
        bookPageResponseDto.setBookDtoList(page.getContent()
                .stream()
                .map(bookMapper::toDto)
                .toList());
        return bookPageResponseDto;
    }
}
