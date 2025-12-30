package mizangroup.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mizangroup.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
	boolean existsByIsbn(String isbn);
}