
package LibraryManagementAPI.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import LibraryManagementAPI.entity.BookIssue;

@Repository
public interface BookIssueRepository extends JpaRepository<BookIssue, Integer> {

    List<BookIssue> findByStudentStudentId(int studentId);

    List<BookIssue> findByDueDateBeforeAndStatus(
            LocalDate date, String status);

    long countByStudentStudentIdAndStatusIn(
            int studentId, List<String> statuses);

}
