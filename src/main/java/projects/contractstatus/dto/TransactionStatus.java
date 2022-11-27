package projects.contractstatus.dto;

import projects.contractstatus.entity.Transaction;

import java.util.Date;

public class TransactionStatus {
    private int code;
    private Transaction.StatusName status;

    private Date time;

    public int getCode() { return code; }

    public void setCode(int code)
    {
        this.code = code;
    }

    public Transaction.StatusName getStatus()
    {
        return status;
    }

    public void setStatus(Transaction.StatusName status)
    {
        this.status = status;
    }

    public Date getTime() { return time; }

    public void setTime(Date time) { this.time = time; }

    @Override
    public String toString()
    {
        return "TransactionStatus{" +
                "code=" + code +
                "time=" + time +
                ", status='" + status + '\'' +
                '}';
    }
}
