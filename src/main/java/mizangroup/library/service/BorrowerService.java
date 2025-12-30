package mizangroup.library.service;

import mizangroup.library.model.*;
import mizangroup.library.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;

import java.time.LocalDate;

@Service
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private final BookDetailRepository bookDetailRepository;
    private final BorrowingRecordRepository borrowingRecordRepository;

    public BorrowerService(BorrowerRepository borrowerRepository,
                           BookDetailRepository bookDetailRepository,
                           BorrowingRecordRepository borrowingRecordRepository) {
        this.borrowerRepository = borrowerRepository;
        this.bookDetailRepository = bookDetailRepository;
        this.borrowingRecordRepository = borrowingRecordRepository;
    }

    @Transactional
    public BorrowedBook borrowBook(Long borrowerId, Long bookDetailId) {
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new IllegalArgumentException("Borrower not found"));

        BookDetail bookDetail = bookDetailRepository.findById(bookDetailId)
                .orElseThrow(() -> new IllegalArgumentException("BookDetail not found"));

        if (bookDetail.getStatus() != BookStatus.AVAILABLE) {
            bookDetail.setStatus(BookStatus.PENDING); // optional: indicate pending borrow
            bookDetailRepository.save(bookDetail);
            throw new IllegalArgumentException("Book is already borrowed or pending");
        }

        bookDetail.setStatus(BookStatus.BORROWED);
        bookDetailRepository.save(bookDetail);

        BorrowedBook bb = new BorrowedBook();
        bb.setBorrower(borrower);
        bb.setBookDetail(bookDetail);
        bb.setBorrowDate(LocalDate.now());
        return borrowingRecordRepository.save(bb);
    }

    @Transactional
    public BorrowedBook returnBook(Long borrowerId, Long bookDetailId) {
        BorrowedBook bb = borrowingRecordRepository
                .findByBookDetailIdAndReturnDateIsNull(bookDetailId)
                .orElseThrow(() -> new IllegalArgumentException("No borrowed book found"));

        bb.setReturnDate(LocalDate.now());
        BookDetail bookDetail = bb.getBookDetail();
        bookDetail.setStatus(BookStatus.AVAILABLE);
        bookDetailRepository.save(bookDetail);

        return borrowingRecordRepository.save(bb);
    }

	public Borrower create(@Valid Borrower borrower) {
			Borrower savedBorrower = borrowerRepository.save(borrower);
		return savedBorrower;
	}
}
