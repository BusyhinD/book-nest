package com.book.nest.controller;

import com.book.nest.dto.BookDto;
import com.book.nest.dto.BookPageResponseDto;
import com.book.nest.dto.CreateBookRequestDto;
import com.book.nest.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RestController
@RequestMapping(value = "/api/books", produces = "application/json")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all books")
    public List<BookDto> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/search")
    @Operation(summary = "Get a page of books",
            parameters = {
                    @Parameter(in = ParameterIn.QUERY, name = "page",
                            description = "The page number, default is 0"),
                    @Parameter(in = ParameterIn.QUERY, name = "size",
                            description = "The size of the page, default value is 21"),
                    @Parameter(in = ParameterIn.QUERY, name = "sort",
                            description = "The sort criteria, "
                                    + "default sort by id and default order is ASC, "
                                    + "may be specified by order DESC or ASC. "
                                    + "Example: title:desc,price:asc"),
                    @Parameter(in = ParameterIn.QUERY, name = "priceIn",
                            description = "Filter by price range. Example: 10,100"),
                    @Parameter(in = ParameterIn.QUERY, name = "authorIn",
                            description =
                                    "Filter by authors. Example: Edgar Allan Poe,Stephen King"),
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Page of books found",
                            content = {
                                    @Content(
                                            array = @ArraySchema(schema = @Schema(implementation =
                                                    BookPageResponseDto.class)))
                            }
                    )
            }
    )
    public BookPageResponseDto searchBook(
            @RequestParam @Parameter(hidden = true) Map<String, String> searchParameters) {
        return bookService.findAll(searchParameters);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a book by id")
    public BookDto getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new book")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto bookDto) {
        return bookService.save(bookDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Update a book by its id")
    public BookDto updateBook(@RequestBody CreateBookRequestDto bookDto, @PathVariable Long id) {
        return bookService.update(id, bookDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a book by its id")
    public void deleteBook(@PathVariable Long id) {
        bookService.delete(id);
    }
}
