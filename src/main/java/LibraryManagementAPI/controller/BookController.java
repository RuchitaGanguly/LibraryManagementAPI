
package LibraryManagementAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import LibraryManagementAPI.entity.Book;
import LibraryManagementAPI.service.BookService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Add book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@Valid @RequestBody Book book) {

        return bookService.addBook(book);
    }

    // Get all books
    @GetMapping
    public List<Book> getAllBooks() {

        return bookService.getAllBooks();
    }

    // Get book by ID
    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable int bookId) {

        return bookService.getBookById(bookId);
    }

    // Update book
    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable int bookId,
    		@Valid @RequestBody Book book) {

        return bookService.updateBook(bookId, book);
    }

    // Delete book
    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable int bookId) {

        bookService.deleteBook(bookId);
    }

    // Get books by category
    @GetMapping("/category/{category}")
    public List<Book> getBooksByCategory(@PathVariable String category) {

        return bookService.getBooksByCategory(category);
    }
}
