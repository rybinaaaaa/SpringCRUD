package rybina.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import rybina.models.Book;
import rybina.models.Person;
import rybina.services.BookService;
import rybina.services.PersonService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private PersonService personService;
    private BookService bookService;

    @Autowired
    public BookController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping()
    public String index(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "books/index";
    }
    @GetMapping("/paged")
    public String index(Model model, @RequestParam("p") int page,
                        @RequestParam("c") int bookPerPage) {
        List<Book> books = bookService.findAll(page, bookPerPage);
        model.addAttribute("books", books);
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = bookService.findOne(id);
        Person person = null;
        List<Person> people = null;
        if (book.getPerson_id() != null) {
            person = personService.findOne(book.getPerson_id());
        } else {
            people = personService.findAll();
        }
        model.addAttribute("book", book);
        model.addAttribute("person", person);
        model.addAttribute("people", people);
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String save(@PathVariable("id") int id, @Valid @ModelAttribute("book") Book book, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
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
        bookService.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/setOwner/{id}")
    public String edit(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookService.update(id, book);
        return "redirect:/books";
    }

    @PatchMapping("/deleteOwner/{id}")
    public String edit(@PathVariable("id") int id) {
        Book book = bookService.findOne(id);
        book.setPerson_id(null);
        bookService.update(id, book);
        return "redirect:/books";
    }

    @GetMapping("/search")
    public String search() {
        return "books/search";
    }

    @GetMapping("/get")
    public String findLike(Model model, @RequestParam("name") String name) {
        Book book = bookService.findLike(name);
        if (book != null) {
            model.addAttribute("book", book);
            return "books/show";
        }

        model.addAttribute("message", "No book by this query was found");
        return "not_found";
    }
}
