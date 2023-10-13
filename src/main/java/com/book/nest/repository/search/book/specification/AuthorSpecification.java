package com.book.nest.repository.search.book.specification;

import com.book.nest.model.Book;
import com.book.nest.repository.search.SpecificationProvider;
import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecification implements SpecificationProvider<Book> {
    private static final String FILTER_KEY = "authorIn";
    private static final String FIELD_NAME = "author";

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }

    @Override
    public Specification<Book> getSpecification(String params) {
        return ((root, query, criteriaBuilder) -> root.get(FIELD_NAME)
                .in(Arrays.stream(params.split(",")).toArray()));
    }
}
