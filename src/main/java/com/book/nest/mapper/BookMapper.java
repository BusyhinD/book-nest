package com.book.nest.mapper;

import com.book.nest.config.MapperConfig;
import com.book.nest.dto.BookDto;
import com.book.nest.dto.CreateBookRequestDto;
import com.book.nest.model.Book;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto bookDto);
}
