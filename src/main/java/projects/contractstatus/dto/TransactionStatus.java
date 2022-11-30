package projects.contractstatus.dto;

import projects.contractstatus.entity.Transaction;
import projects.contractstatus.entity.TransactionID;

import java.util.Date;

public class TransactionStatus {
    private int code;
    private TransactionID.StatusName status;

    private Date time;

    public int getCode() { return code; }

    public void setCode(int code)
    {
        this.code = code;
    }

    public TransactionID.StatusName getStatus()
    {
        return status;
    }

    public void setStatus(TransactionID.StatusName status)
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
