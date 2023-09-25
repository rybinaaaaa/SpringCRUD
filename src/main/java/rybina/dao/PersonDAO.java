package rybina.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import rybina.models.Person;

import java.util.List;

@Component
public class PersonDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO PERSON(name, surname, year) VALUES(?, ?, ?)", person.getName(), person.getSurname(), person.getYear());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE PERSON SET name = ?, surname = ?, year = ? WHERE ID = ?", updatedPerson.getName(), updatedPerson.getYear(), updatedPerson.getYear(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from person where id = ?", id);
    }
}
