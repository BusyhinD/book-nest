package com.book.nest.repository.search;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PageManager {
    private static final int DEFAULT_PAGE_SIZE = 21;
    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final String DEFAULT_SORT_BY = "id";
    private static final int DIRECTION = 1;
    private static final int FIELD = 0;

    public static Pageable getPageable(String sizeParam, String pageNumberParam, String sortBy) {
        final int size = sizeParam == null ? DEFAULT_PAGE_SIZE : Integer.parseInt(sizeParam);
        final int pageNumber =
                pageNumberParam == null ? DEFAULT_PAGE_NUMBER : Integer.parseInt(pageNumberParam);
        if (sortBy == null) {
            sortBy = DEFAULT_SORT_BY;
        }
        List<Sort.Order> orders = new ArrayList<>();
        String[] fields = sortBy.split(",");
        for (String field : fields) {
            Sort.Order order;
            if (field.contains(":")) {
                String[] fieldAndDirection = field.split(":");
                order = new Sort.Order(Sort.Direction.valueOf(fieldAndDirection[DIRECTION]
                        .toUpperCase()), fieldAndDirection[FIELD]);
            } else {
                order = new Sort.Order(Sort.Direction.ASC, field);
            }
            orders.add(order);
        }
        return PageRequest.of(pageNumber, size, Sort.by(orders));
    }
}
