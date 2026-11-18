
package LibraryManagementAPI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "books")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @NotBlank(message = "Book title is required")
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank(message = "Author name is required")
    @Column(name = "author", nullable = false)
    private String author;

    @NotBlank(message = "ISBN is required")
    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;

    @NotBlank(message = "Category is required")
    @Column(name = "category", nullable = false)
    private String category;

    @Min(value = 1, message = "Total copies must be at least 1")
    @Column(name = "total_copies", nullable = false)
    private int totalCopies;

    @Min(value = 0, message = "Available copies cannot be negative")
    @Column(name = "available_copies", nullable = false)
    private int availableCopies;

    public Book() {
        this.bookId = 0;
        this.title = null;
        this.author = null;
        this.isbn = null;
        this.category = null;
        this.totalCopies = 0;
        this.availableCopies = 0;
    }

    public Book(String title, String author, String isbn,
                String category, int totalCopies, int availableCopies) {
        super();
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title
                + ", author=" + author + ", isbn=" + isbn
                + ", category=" + category + ", totalCopies="
                + totalCopies + ", availableCopies=" + availableCopies + "]";
    }
}
