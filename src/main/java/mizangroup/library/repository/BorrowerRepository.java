package mizangroup.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mizangroup.library.model.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {}
