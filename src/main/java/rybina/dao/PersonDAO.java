package rybina.dao;

import org.springframework.stereotype.Component;
import rybina.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    {people = new ArrayList<Person>();
    people.add(new Person(PEOPLE_COUNT++, "Mike"));
    people.add(new Person(PEOPLE_COUNT++, "Jack"));
    people.add(new Person(PEOPLE_COUNT++, "Nick"));
    people.add(new Person(PEOPLE_COUNT++, "Bob"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(PEOPLE_COUNT);
        people.add(person);
    }
}
