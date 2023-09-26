package rybina.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rybina.dao.BookDAO;
import rybina.dao.PersonDAO;
import rybina.models.Book;
import rybina.models.Person;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    BookDAO bookDAO;
    PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        List<Book> books = bookDAO.index();
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = bookDAO.show(id);
        Person person = null;
        List<Person> people = null;
        if (book.getAuthor() != null) {
            person = personDAO.show(book.getPerson_id());
        } else {
            people = personDAO.index();
        }
        model.addAttribute("book", book);
        model.addAttribute("person", person);
        model.addAttribute("people", people);
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String save(@PathVariable("id") int id, @Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newItem(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/setOwner/{id}")
    public String setOwner(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }
}
