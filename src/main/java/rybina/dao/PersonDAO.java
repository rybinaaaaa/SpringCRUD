package rybina.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import rybina.models.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM PERSON", new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE ID = ?", new Object[]{id} ,new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> show(String email) {
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE email = ?", new Object[]{email} ,new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO PERSON(NAME, EMAIL, AGE, ADDRESS) VALUES(?, ?, ?, ?)", person.getName(), person.getEmail(), person.getAge(), person.getAddress());

    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE PERSON SET NAME = ?, AGE = ?, email = ?, ADDRESS = ? WHERE ID = ?", updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), updatedPerson.getAddress(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM PERSON WHERE ID = ?", id);
    }

    ///////////////////////////////// testing

    public void testMultiplyUpdate() {
        List<Person> people = create1000people();

        long before = System.currentTimeMillis();

        for (Person person : people) {
            jdbcTemplate.update("INSERT INTO PERSON(NAME, EMAIL, AGE) VALUES(?, ?, ?)", person.getName(), person.getEmail(), person.getAge());
        }

        long after = System.currentTimeMillis();

        System.out.println(after - before);
    }

    public void testBatchUpdate() {
        List<Person> people = create1000people();

        long before = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO PERSON(NAME, EMAIL, AGE) VALUES(?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, people.get(i).getName());
                preparedStatement.setString(2, people.get(i).getEmail());
                preparedStatement.setInt(3, people.get(i).getAge());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });

        long after = System.currentTimeMillis();

        System.out.println(after - before);
    }

    private List<Person> create1000people() {
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            people.add(new Person(0, "Name" + i, 20,"some.email@gmail.com", "some address"));
        }

        return people;
    }
}
