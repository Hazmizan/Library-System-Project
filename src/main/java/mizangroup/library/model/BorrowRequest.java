package mizangroup.library.model;

public class BorrowRequest {
	private Long borrowerId;
	private Long bookDetailId;

	// getters & setters
	public Long getBorrowerId() {
		return borrowerId;
	}
	public void setBorrowerId(Long borrowerId) {
		this.borrowerId = borrowerId;
	}
	public Long getBookDetailId() {
		return bookDetailId;
	}
	public void setBookDetailId(Long bookDetailId) {
		this.bookDetailId = bookDetailId;
	}

}