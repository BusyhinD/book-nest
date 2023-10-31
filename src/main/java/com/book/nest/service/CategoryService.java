package com.book.nest.service;

import com.book.nest.dto.CategoryDto;
import java.util.List;

public interface CategoryService {
    CategoryDto save(CategoryDto categoryDto);

    CategoryDto findById(Long id);

    List<CategoryDto> findAll();

    CategoryDto update(Long id, CategoryDto categoryDto);

    void deleteById(Long id);
}
