package projects.contractstatus.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Embeddable
public class TransactionID  implements Serializable {
    public enum StatusName {NEW, PENDING, ACTIVE, STAND_BY, FINAL};
    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "code")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Code code;

    @Column(name = "contract_number")
    private int contractNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusName status;

    public Code getCode() {
        return code;
    }

    public TransactionID() { // jpa only
    }

    public TransactionID(Code code, int contract_number, StatusName status) {
        this.code = code;
        this.contractNumber = contract_number;
        this.status = status;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public int getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(int contractNumber) {
        this.contractNumber = contractNumber;
    }

    public StatusName getStatus() {
        return status;
    }

    public void setStatus(StatusName status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionID that)) return false;
        return getContractNumber() == that.getContractNumber() &&
                                    getCode().getId() == that.getCode().getId() &&
                                    getCode().getCode() == that.getCode().getCode() &&
                                    getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode().getId(), getCode().getCode(), getContractNumber(), getStatus());
    }

    @Override
    public String toString() {
        return "TransactionID{" +
                "code=" + code +
                ", contractNumber=" + contractNumber +
                ", status=" + status +
                '}';
    }
}
