package rybina.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import rybina.models.Book;

import java.util.List;

@Component
public class BookDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Book show(int id) {
       return jdbcTemplate.query("select * from book where book.id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny().orElse(null);
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("update book set name = ?, year = ?, person_id = ?, author  = ? where id = ?", updatedBook.getName(), updatedBook.getYear(), updatedBook.getPerson_id(), updatedBook.getAuthor(), id);
    }

    public void save(Book book) {
        jdbcTemplate.update("insert into book (name, year, author) values  (?, ?, ?)", book.getName(), book.getYear(), book.getAuthor());
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from book where id = ?", id);
    }
}
