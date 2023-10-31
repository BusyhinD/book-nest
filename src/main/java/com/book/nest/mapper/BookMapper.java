package com.book.nest.mapper;

import com.book.nest.config.MapperConfig;
import com.book.nest.dto.book.BookDto;
import com.book.nest.dto.book.BookDtoWithoutCategoryIds;
import com.book.nest.dto.book.CreateBookRequestDto;
import com.book.nest.model.Book;
import com.book.nest.model.Category;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto bookDto);

    BookDtoWithoutCategoryIds toDtoWithoutCategoryIds(Book book);

    Book update(@MappingTarget Book book, CreateBookRequestDto bookDto);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        bookDto.setCategoryIds(book.getCategories()
                .stream()
                .map(Category::getId)
                .collect(Collectors.toSet()));
    }

    @AfterMapping
    default void setCategories(@MappingTarget Book book, CreateBookRequestDto bookDto) {
        book.setCategories(bookDto.getCategoryIds()
                .stream()
                .map(Category::new)
                .collect(Collectors.toSet()));
    }
}
