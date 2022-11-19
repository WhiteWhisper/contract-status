package projects.contractstatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projects.contractstatus.entity.Code;
import projects.contractstatus.entity.Transaction;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByCode(Code code);
    Transaction findOneByCodeAndStatus(Code code, String status);
}
