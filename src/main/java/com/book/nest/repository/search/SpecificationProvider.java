package com.book.nest.repository.search;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProvider<T> {
    String getFilterKey();

    Specification<T> getSpecification(String params);
}
