
package LibraryManagementAPI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LibraryManagementAPI.entity.Book;
import LibraryManagementAPI.exception.BadRequestException;
import LibraryManagementAPI.exception.DuplicateResourceException;
import LibraryManagementAPI.exception.ResourceNotFoundException;
import LibraryManagementAPI.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // Add book
    public Book addBook(Book book) {

        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new DuplicateResourceException("ISBN already exists");
        }

        book.setAvailableCopies(book.getTotalCopies());

        return bookRepository.save(book);
    }

    // Get all books
    public List<Book> getAllBooks() {

        return bookRepository.findAll();
    }

    // Get book by ID
    public Book getBookById(int bookId) {

        return bookRepository.findById(bookId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Book not found"));
    }

    // Update book
    public Book updateBook(int bookId, Book book) {

        Book existingBook = getBookById(bookId);

        if (!existingBook.getIsbn().equals(book.getIsbn())
                && bookRepository.existsByIsbn(book.getIsbn())) {

            throw new DuplicateResourceException("ISBN already exists");
        }

        int issuedCopies = existingBook.getTotalCopies()
                - existingBook.getAvailableCopies();

        if (book.getTotalCopies() < issuedCopies) {
            throw new BadRequestException(
                    "Total copies cannot be less than issued copies");
        }

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setCategory(book.getCategory());
        existingBook.setTotalCopies(book.getTotalCopies());

        existingBook.setAvailableCopies(
                book.getTotalCopies() - issuedCopies);

        return bookRepository.save(existingBook);
    }

    // Delete book
    public void deleteBook(int bookId) {

        Book book = getBookById(bookId);

        bookRepository.delete(book);
    }

    // Get books by category
    public List<Book> getBooksByCategory(String category) {

        return bookRepository.findByCategory(category);
    }
}
