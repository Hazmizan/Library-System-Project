package mizangroup.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import mizangroup.library.model.BorrowedBook;

public interface BorrowingRecordRepository extends JpaRepository<BorrowedBook, Long> {
	Optional<BorrowedBook> findByBookDetailIdAndReturnDateIsNull(Long bookDetailId);
}