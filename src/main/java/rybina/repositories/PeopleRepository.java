package rybina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rybina.models.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
}
