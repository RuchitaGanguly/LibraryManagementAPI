
package LibraryManagementAPI.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import LibraryManagementAPI.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByCategory(String category);

    boolean existsByIsbn(String isbn);

}
