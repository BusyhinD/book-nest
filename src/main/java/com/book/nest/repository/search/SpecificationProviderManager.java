package com.book.nest.repository.search;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationProviderManager<T> {
    Specification<T> get(String filter, String params);
}
