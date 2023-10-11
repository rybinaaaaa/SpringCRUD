package rybina.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @NotEmpty(message = "Name can not be empty")
    @Size(min = 2, max = 30, message = "Name should be longer then 2 symbols & shorter then 30 symbols")
    private String name;
    @Column
    @Min(value = 0, message = "male should not to be empty")
    private int age;

    @Column
    @NotEmpty()
    @Email()
    private String email;

    @Column
    private String address;
    // Страна, город, индекс (6 цифр)

    public Person(String name, int age, String email, String address) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.address = address;
    }

    public Person() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
