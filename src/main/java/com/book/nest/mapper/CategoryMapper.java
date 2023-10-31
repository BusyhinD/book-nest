package com.book.nest.mapper;

import com.book.nest.config.MapperConfig;
import com.book.nest.dto.CategoryDto;
import com.book.nest.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toModel(CategoryDto categoryDto);

    Category update(@MappingTarget Category category, CategoryDto bookDto);
}
