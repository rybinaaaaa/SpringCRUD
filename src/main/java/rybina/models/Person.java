package rybina.models;

import javax.validation.constraints.*;

public class Person {

    private int id;

    @NotEmpty(message = "Name can not be empty")
    @Size(min = 2, max = 30, message = "Name should be longer then 2 symbols & shorter then 30 symbols")
    private String name;

    @Min(value = 0, message = "male should not to be empty")
    private int age;

    @NotEmpty()
    @Email()
    private String email;

    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ \\d{3} \\d{2}", message = "incorrect address")
    private String address;
    // Страна, город, индекс (6 цифр)

    public Person(int id, String name, int age, String email, String address) {
        this.id = id;
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
