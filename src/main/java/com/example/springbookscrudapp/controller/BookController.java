package com.example.springbookscrudapp.controller;

import com.example.springbookscrudapp.model.Category;
import com.example.springbookscrudapp.repository.BookRepository;
import com.example.springbookscrudapp.repository.CategoryRepository;
import com.example.springbookscrudapp.service.BookService;
import com.example.springbookscrudapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.springbookscrudapp.model.Book;

import java.util.Set;



@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    private CategoryService categoryService;

    /**
     * Displays a single Book
     * @param id            book Id
     * @param model         book object
     * @return              template for displaying a single book
     */
    @RequestMapping( path = "/book/show/{id}")
    public Book showSingleBook(@PathVariable("id") long id) {
        return bookRepository.findById(id).orElseThrow();
        
        
    }

    /**
     * New Book Form
     * @param model     book object
     * @return          template form for new book
     */
    // @RequestMapping( path = "/book/create")
    // public String newBookForm(Model model) {
    //     model.addAttribute("book", new Book());
    //     Set<Category> categories = categoryService.getAll();
    //     model.addAttribute("categories", categories);
    //     return "books/new";
    // }

    /**
     * saves the details of "book/create" to DB
     * @param book      field values
     * @return          redirection to list view of all books
     */
    @RequestMapping(path = "/book", method = RequestMethod.POST)
    public Book saveNewBook( @RequestBody Book book) {
        return bookRepository.save(book);
        
    }

    /**
     * Edit Form
     * @param id        book Id
     * @param model     book object
     * @return          template for editing a book
     */
    @GetMapping("/book/{id}")
    public Book editBookForm(@PathVariable("id") long id, Book bookDetails) {
        bookRepository.findById(id);
        
        bookDetails.setId(id);
        Book updateBook = bookRepository.save(bookDetails);

        return updateBook;
    }

    /**
     * shows all existing books in DB as list
     * @param model     book objects
     * @return          template for list view
     */
    // @GetMapping({"/books", "/"})
    // public Book showAllBooks(Book book, Category category) {
    //     return bookRepository.getAll() categoryRepository.getAll();
        

    // }

    /**
     * Saves book details from edit template for an existing book in DB
     * @param id        book Id
     * @param book      book details (of field values)
     * @return          redirection to list view of all books
     */
    @RequestMapping(path = "/book/{id}", method = RequestMethod.POST)
    public Book updateBook(@PathVariable("id") long id, Book book) {
        return bookRepository.save(id, book);
}

    /**
     * deletes existing book from DB
     * @param id        book Id
     * @return          redirection to list view of all books
     */
    @RequestMapping(path = "/book/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") long id) {
        return bookRepository.delete(id);
        
    }
}

