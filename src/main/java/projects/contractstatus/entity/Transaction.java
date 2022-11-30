package projects.contractstatus.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import projects.contractstatus.entity.Code;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Transactions")
public class Transaction {
    @EmbeddedId
    private TransactionID id;
    @Column(name = "time_i")
    private Date time;

    public Transaction() { // jpa only
    }

    public Transaction(TransactionID id, Date time) {
        this.id = id;
        this.time = time;
    }

    public Transaction(TransactionID id) {
        this.id = id;
        this.time = null;
    }

    @PrePersist
    public void time(){
        this.time = new Date();
    }

    public Date getTime() {
        return time;
    }

    public TransactionID getId() {
        return id;
    }

    public void setId(TransactionID id) {
        this.id = id;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", time=" + time +
                '}';
    }
}
