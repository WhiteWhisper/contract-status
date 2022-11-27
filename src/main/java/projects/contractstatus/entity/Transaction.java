package projects.contractstatus.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import projects.contractstatus.entity.Code;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Transactions")
public class Transaction implements Serializable {
    public enum StatusName {NEW, PENDING, ACTIVE, STAND_BY, FINAL};
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@Column(name = "code")
    private Code code;
    @Column(name = "contract_number")
    private int contractNumber;
    @Column(name = "time_i")
    private Date time;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusName status;

    Transaction() { // jpa only
    }

    public Transaction(Code code, int contract_number, Date time, StatusName status) {
        this.code = code;
        this.contractNumber = contract_number;
        this.time = time;
        this.status = status;
    }

    @PrePersist
    public void time(){
        this.time = new Date();
    }
    public Code getCode() {
        return code;
    }


    public Date getTime() {
        return time;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public StatusName getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setStatus(StatusName status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", code=" + code.getCode() +
                ", contractNumber=" + contractNumber +
                ", time=" + time +
                ", status='" + status + '\'' +
                '}';
    }
}
