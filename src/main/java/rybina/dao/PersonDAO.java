package rybina.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rybina.models.Person;

import java.util.List;

@Component
public class PersonDAO {
//    private final JdbcTemplate jdbcTemplate;
    private SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

    @Transactional(readOnly = true) // обозначаем, что эта функция - транзакция
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select  p from Person p", Person.class).getResultList();
//        return jdbcTemplate.query("SELECT * FROM PERSON", new PersonMapper());
    }

    @Transactional(readOnly = true)
    public Person show(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Person.class, id);
//        return jdbcTemplate.query("SELECT * FROM PERSON WHERE ID = ?", new Object[]{id} ,new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    @Transactional
    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
//        jdbcTemplate.update("INSERT INTO PERSON(NAME, EMAIL, AGE, ADDRESS) VALUES(?, ?, ?, ?)", person.getName(), person.getEmail(), person.getAge(), person.getAddress());
    }


    @Transactional
    public void update(int id, Person updatedPerson) {
        Session session = sessionFactory.getCurrentSession();

        Person personToBeUpdated = session.get(Person.class, id);

        personToBeUpdated.setName(updatedPerson.getName());
        personToBeUpdated.setAge(updatedPerson.getAge());
        personToBeUpdated.setEmail(updatedPerson.getEmail());
//        jdbcTemplate.update("UPDATE PERSON SET NAME = ?, AGE = ?, email = ?, ADDRESS = ? WHERE ID = ?", updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), updatedPerson.getAddress(), id);
    }


    @Transactional
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Person.class, id));
//        jdbcTemplate.update("DELETE FROM PERSON WHERE ID = ?", id);
    }
}
