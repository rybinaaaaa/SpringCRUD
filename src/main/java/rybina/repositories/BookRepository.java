package rybina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rybina.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    public Book findFirstByNameStartingWith(String name);
}
