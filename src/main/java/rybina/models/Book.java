package rybina.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Book {

    @NotEmpty(message = "Name can not be empty")
    private String name;

    @NotNull(message = "Book should have author")
    private Person author;

    @NotEmpty(message = "Year can not be empty")
    @Min(value = 1900, message = "Incorrect year")
    private int release_year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public int getRelease_year() {
        return release_year;
    }

    public void setRelease_year(int release_year) {
        this.release_year = release_year;
    }

    public Book(String name, Person author, int release_year) {
        this.name = name;
        this.author = author;
        this.release_year = release_year;
    }
}
