package mizangroup.library.controller;

import mizangroup.library.model.BorrowRequest;
import mizangroup.library.model.BorrowedBook;
import mizangroup.library.model.Borrower;
import mizangroup.library.service.BorrowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {

    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) { 
    	this.borrowerService = borrowerService; 
    }
    
    @PostMapping
    public ResponseEntity<Borrower> create(@Valid @RequestBody Borrower borrower) {
    	Borrower savedBorrower = borrowerService.create(borrower);
        return ResponseEntity.ok(savedBorrower);
    }

    //test for frontend compatible
    @PostMapping("/borrow")
    public ResponseEntity<BorrowedBook> borrowBook(@RequestBody BorrowRequest request) {
        return ResponseEntity.ok(
            borrowerService.borrowBook(request.getBorrowerId(), request.getBookDetailId())
        );
    }


    @PostMapping("/{borrowerId}/return/{bookDetailId}")
    public ResponseEntity<BorrowedBook> returnBook(
            @PathVariable Long borrowerId,
            @PathVariable Long bookDetailId) {
        return ResponseEntity.ok(borrowerService.returnBook(borrowerId, bookDetailId));
    }
}
