
package LibraryManagementAPI.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import LibraryManagementAPI.entity.Book;
import LibraryManagementAPI.entity.BookIssue;
import LibraryManagementAPI.entity.Student;
import LibraryManagementAPI.exception.BadRequestException;
import LibraryManagementAPI.exception.ResourceNotFoundException;
import LibraryManagementAPI.repository.BookIssueRepository;
import LibraryManagementAPI.repository.BookRepository;
import LibraryManagementAPI.repository.StudentRepository;

@Service
public class BookIssueService {

    @Autowired
    private BookIssueRepository bookIssueRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StudentRepository studentRepository;

    // Issue a book
    @Transactional
    public BookIssue issueBook(int studentId, int bookId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Student not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Book not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new BadRequestException("No copies available");
        }

        List<String> activeStatuses = Arrays.asList("ISSUED", "OVERDUE");

        long activeIssues = bookIssueRepository
                .countByStudentStudentIdAndStatusIn(studentId, activeStatuses);

        if (activeIssues >= 3) {
            throw new BadRequestException(
                    "Student cannot issue more than 3 books");
        }

        BookIssue issue = new BookIssue();

        issue.setStudent(student);
        issue.setBook(book);
        issue.setIssueDate(LocalDate.now());
        issue.setDueDate(LocalDate.now().plusDays(14));
        issue.setReturnDate(null);
        issue.setStatus("ISSUED");

        book.setAvailableCopies(book.getAvailableCopies() - 1);

        bookRepository.save(book);

        return bookIssueRepository.save(issue);
    }

    // Get all issues
    public List<BookIssue> getAllIssues() {

        return bookIssueRepository.findAll();
    }

    // Get issues by student
    public List<BookIssue> getIssuesByStudent(int studentId) {

        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found");
        }

        return bookIssueRepository.findByStudentStudentId(studentId);
    }

    // Return book
    @Transactional
    public BookIssue returnBook(int issueId) {

        BookIssue issue = bookIssueRepository.findById(issueId)
                .orElseThrow(() ->
                    new ResourceNotFoundException("Issue record not found"));

        if ("RETURNED".equals(issue.getStatus())) {
            throw new BadRequestException("Book has already been returned");
        }

        Book book = issue.getBook();

        book.setAvailableCopies(book.getAvailableCopies() + 1);

        issue.setReturnDate(LocalDate.now());
        issue.setStatus("RETURNED");

        bookRepository.save(book);

        return bookIssueRepository.save(issue);
    }

    // Get overdue books
    @Transactional
    public List<BookIssue> getOverdueBooks() {

        LocalDate today = LocalDate.now();

        List<BookIssue> overdueIssues =
                bookIssueRepository.findByDueDateBeforeAndStatus(
                        today, "ISSUED");

        for (BookIssue issue : overdueIssues) {
            issue.setStatus("OVERDUE");
        }

        return bookIssueRepository.saveAll(overdueIssues);
    }
}
