package com.book.nest.repository.search;

import java.util.Map;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(Map<String, String> searchParams);
}
