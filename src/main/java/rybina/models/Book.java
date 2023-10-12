package rybina.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotEmpty(message = "Name can not be empty")
    private String name;

    @Column
    @NotEmpty(message = "Book should have author")
    private String author;

    @Column
    @Min(value = 1900, message = "Incorrect year")
    private Integer year;

    @Column
    private Integer person_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + ", " + year +
                ", author:" + author;
    }


//    @Override
//    public String toString() {
//        return "Book{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", author='" + author + '\'' +
//                ", year=" + year +
//                ", person_id=" + person_id +
//                '}';
//    }

    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(Integer person_id) {
        this.person_id = person_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Book() {
    }

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(int id, String name, String author, int year, Integer person_id) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.person_id = person_id;
    }
}
