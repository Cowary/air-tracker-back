package org.cowary.arttrackerback.repo.book;

import org.cowary.arttrackerback.entity.book.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends CrudRepository<Book, Long> {

    List<Book> findAll();
    List<Book> findAllByUsrId(Long usrId);
    Optional<Book> findById(Long id);
    List<Book> findByStatus(String status);
}
