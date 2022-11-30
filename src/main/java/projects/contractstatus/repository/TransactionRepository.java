package projects.contractstatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projects.contractstatus.entity.Code;
import projects.contractstatus.entity.Transaction;
import projects.contractstatus.entity.TransactionID;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, TransactionID> {
    List<Transaction> findByIdCode(Code code);
    Transaction findOneByIdCodeAndIdStatus(Code code, TransactionID.StatusName status);
}
