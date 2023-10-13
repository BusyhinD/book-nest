package com.book.nest.repository.search.book.specification;

import com.book.nest.model.Book;
import com.book.nest.repository.search.SpecificationProvider;
import com.book.nest.repository.search.SpecificationProviderManager;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final Map<String, SpecificationProvider<Book>> specProviderMap;

    public BookSpecificationProviderManager(List<SpecificationProvider<Book>> specProviders) {
        specProviderMap = specProviders
                .stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<Book> get(String filter, String params) {
        if (!specProviderMap.containsKey(filter)) {
            throw new RuntimeException("Key " + filter + " is not supported for filtering");
        }
        return specProviderMap.get(filter).getSpecification(params);
    }
}
