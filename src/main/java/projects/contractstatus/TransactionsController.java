package projects.contractstatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projects.contractstatus.dto.TransactionStatus;
import projects.contractstatus.entity.Code;
import projects.contractstatus.entity.Transaction;
import projects.contractstatus.entity.TransactionID;
import projects.contractstatus.repository.CodeRepository;
import projects.contractstatus.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    private TransactionRepository transactionRepo;
    private CodeRepository codeRepo;

    public TransactionsController(TransactionRepository transactionRepo, CodeRepository codeRepo) {
        this.codeRepo = codeRepo;
        this.transactionRepo = transactionRepo;
    }

    @GetMapping(value = "/{code}")
    ResponseEntity<List<Transaction>> getTransaction(@PathVariable int code) {
        Code codeRec = codeRepo.findOneByCode(code);

        return codeRec != null ?  new ResponseEntity<>(transactionRepo.findByIdCode(codeRec), HttpStatus.OK) :
                                  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/status/{code}")
    ResponseEntity<List<TransactionStatus>> getTransactionStatus(@PathVariable int code) {
        List<TransactionStatus> transactionStatuses = new ArrayList<>();

        Code codeRec = codeRepo.findOneByCode(code);

        if (codeRec != null) {
            transactionStatuses = transactionRepo.findByIdCode(codeRec).stream().map(transaction ->
            {
                TransactionStatus transactionStatus = new TransactionStatus();
                transactionStatus.setCode(transaction.getId().getCode().getCode());
                transactionStatus.setStatus(transaction.getId().getStatus());
                transactionStatus.setTime(transaction.getTime());
                return transactionStatus;
            }).collect(Collectors.toList());
        }


        return transactionStatuses.isEmpty() ?  new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                                                new ResponseEntity<>(transactionStatuses, HttpStatus.OK);
    }

    @GetMapping(value = "/")
    ResponseEntity<List<Transaction>> getTransactions() {
        List<Transaction> transactions = transactionRepo.findAll();
        return transactions.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                                        new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    ResponseEntity<?> addTransaction(@RequestBody TransactionID trId) {
        ResponseEntity<?> result;
        //getting Code with code from Transaction
        Code code = codeRepo.findOneByCode(trId.getCode().getCode());
        //if not found, code doesn't exist. Save new one
        if (code == null) {
            code = codeRepo.save(new Code(trId.getCode().getCode()));
            //saving Transactions
            trId.setCode(code);
            Transaction tr = new Transaction(trId);
            transactionRepo.save(tr);
            result = new ResponseEntity<>(HttpStatus.CREATED);
        } else {//code was found, check transaction with the same code and status
            Transaction tr_dub = transactionRepo.findOneByIdCodeAndIdStatus(code, trId.getStatus());
            if (tr_dub != null) {//we find the transaction with the same code and status, update time
                tr_dub.setTime(new Date());
                transactionRepo.save(tr_dub);
                result = new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {//didn't find the transaction with the same code and status, create new one
                trId.setCode(code);
                Transaction tr = new Transaction(trId);
                transactionRepo.save(tr);
                result = new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        return result;
    }

}