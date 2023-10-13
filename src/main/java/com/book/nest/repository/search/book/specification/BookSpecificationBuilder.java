package com.book.nest.repository.search.book.specification;

import com.book.nest.model.Book;
import com.book.nest.repository.search.SpecificationBuilder;
import com.book.nest.repository.search.SpecificationProviderManager;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationManager;

    @Override
    public Specification<Book> build(Map<String, String> params) {
        Specification<Book> specification = null;
        for (Map.Entry<String, String> param : params.entrySet()) {
            Specification<Book> spec = bookSpecificationManager.get(param.getKey(),
                    param.getValue());
            specification = specification == null ? Specification.where(spec) :
                    specification.and(spec);
        }
        return specification;
    }
}
