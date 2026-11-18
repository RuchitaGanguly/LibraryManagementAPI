
package LibraryManagementAPI.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import LibraryManagementAPI.dto.BookIssueDTO;

import LibraryManagementAPI.entity.BookIssue;
import LibraryManagementAPI.service.BookIssueService;

@RestController
@RequestMapping("/api/issues")
public class BookIssueController {

    @Autowired
    private BookIssueService bookIssueService;

    // Issue a book
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookIssue issueBook(@Valid @RequestBody BookIssueDTO issueDTO) {

        return bookIssueService.issueBook(
                issueDTO.getStudentId(),
                issueDTO.getBookId()
        );
    }
    

    // Get all issue records
    @GetMapping
    public List<BookIssue> getAllIssues() {

        return bookIssueService.getAllIssues();
    }

    // Get issues by student
    @GetMapping("/student/{studentId}")
    public List<BookIssue> getIssuesByStudent(
            @PathVariable int studentId) {

        return bookIssueService.getIssuesByStudent(studentId);
    }

    // Return a book
    @PutMapping("/return/{issueId}")
    public BookIssue returnBook(@PathVariable int issueId) {

        return bookIssueService.returnBook(issueId);
    }

    // Get overdue books
    @GetMapping("/overdue")
    public List<BookIssue> getOverdueBooks() {

        return bookIssueService.getOverdueBooks();
    }
}
