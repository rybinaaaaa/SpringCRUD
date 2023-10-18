package rybina.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

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
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @Column(name = "taken_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date takenDate;

    public boolean isDateExpired() {
        boolean expired = false;

        if (takenDate != null && this.person != null) {
            Date expiredDate = new Date(takenDate.getTime() + 60 * 1000 * 60 * 24 * 10);
            expired = expiredDate.before(new Date());
        }
        return expired;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + ", " + year +
                ", author:" + author;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        if (person == null) {
            takenDate = null;
            this.person = null;
            return;
        }
        this.person = person;
        this.takenDate = new Date();
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

    public Date getTakenDate() {
        return takenDate;
    }

    public void setTakenDate(Date takenDate) {
        this.takenDate = takenDate;
    }

    public Book() {
    }

    public Book(String name, String author, int year) {
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public Book(String name, String author, int year, Person person) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.person = person;
    }
}
