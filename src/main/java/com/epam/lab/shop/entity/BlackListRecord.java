package com.epam.lab.shop.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class BlackListRecord implements Serializable {

    private int userId;
    private String cause;
    private Timestamp dateOfBlocking;

    public BlackListRecord(int userId, String cause, Timestamp dateOfBlocking) {
        this.userId = userId;
        this.cause = cause;
        this.dateOfBlocking = dateOfBlocking;
    }

    public BlackListRecord() {
        this.userId = 0;
        this.cause = null;
        this.dateOfBlocking = null;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Timestamp getDateOfBlocking() {
        return dateOfBlocking;
    }

    public void setDateOfBlocking(Timestamp dateOfBlocking) {
        this.dateOfBlocking = dateOfBlocking;
    }

    @Override
    public String toString() {
        return "BlackListRecord{" +
                "userId=" + userId +
                ", cause='" + cause + '\'' +
                ", dateOfBlocking=" + dateOfBlocking +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BlackListRecord that = (BlackListRecord) obj;
        return userId == that.userId && Objects.equals(cause, that.cause) && Objects.equals(dateOfBlocking, that.dateOfBlocking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, cause, dateOfBlocking);
    }
}
