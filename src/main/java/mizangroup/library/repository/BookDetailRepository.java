package mizangroup.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import jakarta.persistence.LockModeType;
import mizangroup.library.model.BookDetail;


public interface BookDetailRepository extends JpaRepository<BookDetail, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Optional<BookDetail> findById(Long id);
}
