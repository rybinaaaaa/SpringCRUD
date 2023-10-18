package rybina.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Name can not be empty")
    @Size(min = 2, max = 30, message = "Name should be longer then 2 symbols & shorter then 30 symbols")
    private String name;

    @Column
    @NotEmpty(message = "Surname can not be empty")
    @Size(min = 2, max = 30, message = "Surname should be longer then 2 symbols & shorter then 30 symbols")
    private String surname;

    @Column
    @Min(value = 1900, message = "Incorrect date of birth")
    private int year;

    @OneToMany(mappedBy = "person")
    private List<Book> books;


    public Person() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person(String name, String surname, int year) {
        this.name = name;
        this.surname = surname;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return name +
                " " + surname +
                " " + year;
    }
}
