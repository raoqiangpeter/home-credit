package com.raoqiang.homecredit.entry;

import java.math.BigInteger;
import java.util.Date;

public class HcStream {
    private BigInteger id;
    private String hcId;
    private String hcResult;
    private String hcStatus;
    private Date hcTime;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public HcStream() {
    }

    public String getHcId() {
        return hcId;
    }

    public void setHcId(String hcId) {
        this.hcId = hcId;
    }

    public String getHcResult() {
        return hcResult;
    }

    public void setHcResult(String hcResult) {
        this.hcResult = hcResult;
    }

    public String getHcStatus() {
        return hcStatus;
    }

    public void setHcStatus(String hcStatus) {
        this.hcStatus = hcStatus;
    }

    public Date getHcTime() {
        return hcTime;
    }

    public void setHcTime(Date hcTime) {
        this.hcTime = hcTime;
    }
}
