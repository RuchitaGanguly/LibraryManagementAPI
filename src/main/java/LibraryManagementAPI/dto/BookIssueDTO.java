
package LibraryManagementAPI.dto;

import jakarta.validation.constraints.Min;

public class BookIssueDTO {

    @Min(value = 1, message = "Student ID must be greater than 0")
    private int studentId;

    @Min(value = 1, message = "Book ID must be greater than 0")
    private int bookId;

    public BookIssueDTO() {
        this.studentId = 0;
        this.bookId = 0;
    }

    public BookIssueDTO(int studentId, int bookId) {
        super();
        this.studentId = studentId;
        this.bookId = bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "BookIssueDTO [studentId=" + studentId
                + ", bookId=" + bookId + "]";
    }
}
