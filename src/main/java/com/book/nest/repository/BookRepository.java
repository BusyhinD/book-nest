package com.book.nest.repository;

import com.book.nest.model.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @EntityGraph(attributePaths = "{categories}")
    List<Book> findAll();

    @EntityGraph(attributePaths = "{categories}")
    Page<Book> findAll(Specification<Book> specification, Pageable pageable);

    @Query("SELECT b FROM Book b JOIN b.categories c WHERE c.id = :id")
    List<Book> findAllByCategoryId(Long id);

    @EntityGraph(attributePaths = {"categories"})
    Optional<Book> findById(Long id);

}
