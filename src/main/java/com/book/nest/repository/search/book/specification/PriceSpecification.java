package com.book.nest.repository.search.book.specification;

import com.book.nest.model.Book;
import com.book.nest.repository.search.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceSpecification implements SpecificationProvider<Book> {
    private static final String FILTER_KEY = "priceIn";
    private static final String FIELD_NAME = "price";
    private static final Integer MIN = 0;
    private static final Integer MAX = 1;

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }

    @Override
    public Specification<Book> getSpecification(String params) {
        String[] minAndMax = params.split(",");
        if (minAndMax.length != 2) {
            throw new RuntimeException("Price filter must include min and max values");
        }
        return (root, query, cb) -> cb.between(root.get(FIELD_NAME), minAndMax[MIN],
                minAndMax[MAX]);
    }
}
