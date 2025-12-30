package mizangroup.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mizangroup.library.model.Book;
import mizangroup.library.model.BookDetail;
import mizangroup.library.model.BookStatus;
import mizangroup.library.repository.BookDetailRepository;
import mizangroup.library.repository.BookRepository;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final BookDetailRepository bookDetailRepository;

    public BookService(BookRepository bookRepository, BookDetailRepository bookDetailRepository) {
        this.bookRepository = bookRepository;
        this.bookDetailRepository = bookDetailRepository;
    }

    public Book create(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new IllegalArgumentException("ISBN already exists");
        }
        
        // Step 1: Save in Book DB
        Book savedBook = bookRepository.save(book);

        // Step 2: Create BookDetail for this book
        BookDetail detail = new BookDetail();
        detail.setBook(savedBook);
        detail.setStatus(BookStatus.AVAILABLE);

        // Step 3: Save BookDetail DB (status)
        bookDetailRepository.save(detail);

        // Step 4: Return the saved book
        return savedBook;
    }

    public List<Book> getAllBooks() {
    	return bookRepository.findAll();
    }
}
