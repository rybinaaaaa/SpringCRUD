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
    people.add(new Person(PEOPLE_COUNT++, "Mike", 29, "some email"));
    people.add(new Person(PEOPLE_COUNT++, "Jack", 29, "some email"));
    people.add(new Person(PEOPLE_COUNT++, "Nick", 29, "some email"));
    people.add(new Person(PEOPLE_COUNT++, "Bob", 29, "some email"));
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

    public void update(int id, Person updatedPerson) {
        Person person = show(id);
        person.setName(updatedPerson.getName());
        person.setEmail(updatedPerson.getEmail());
        person.setAge(updatedPerson.getAge());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
