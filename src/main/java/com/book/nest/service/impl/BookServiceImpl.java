package com.book.nest.service.impl;

import com.book.nest.dto.book.BookDto;
import com.book.nest.dto.book.BookDtoWithoutCategoryIds;
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
        Pageable pageable = PageManager.getPageable(
                searchParameters.remove("size"),
                searchParameters.remove("page"),
                searchParameters.remove("sort"));
        return getBookPageResponseDto(
                bookRepository.findAll(
                        specBuilder.build(searchParameters), pageable));
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto bookDto) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Not found book with id " + id));
        return bookMapper.toDto(bookRepository.save(bookMapper.update(book, bookDto)));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDtoWithoutCategoryIds> getAllBooksByCategoryId(Long id) {
        return bookRepository.findAllByCategoryId(id)
                .stream()
                .map(bookMapper::toDtoWithoutCategoryIds)
                .toList();
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
