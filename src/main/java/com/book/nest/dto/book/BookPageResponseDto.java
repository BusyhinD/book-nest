package com.book.nest.dto.book;

import java.util.List;
import lombok.Data;

@Data
public class BookPageResponseDto {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private long numberOfBooks;
    private boolean isLastPage;
    private List<BookDto> bookDtoList;
}
