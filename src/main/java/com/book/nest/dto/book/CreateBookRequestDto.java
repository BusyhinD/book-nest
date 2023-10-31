package com.book.nest.dto.book;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Set;
import lombok.Data;

@Data
public class CreateBookRequestDto {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotBlank
    private String isbn;
    @DecimalMin("0")
    private BigDecimal price;
    @NotBlank
    private String description;
    @NotBlank
    private String coverImage;
    @NotEmpty
    private Set<Long> categoryIds;
}
