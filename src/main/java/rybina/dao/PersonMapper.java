package rybina.dao;


import org.springframework.jdbc.core.RowMapper;
import rybina.models.Person;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {

    @Override
    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
        Person person = new Person();

        person.setAge(resultSet.getInt("age"));
        person.setName(resultSet.getString("name"));
        person.setEmail(resultSet.getString("email"));
        person.setId(resultSet.getInt("id"));

        return person;
    }
}
