package com.raoqiang.homecredit.entry;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 结果类 -> mysql数据库表t_result
 */
public class HcResult {

    private String hcId;

    private String hcSkId;

    private BigDecimal hcScore;

    private Date hcTime;

    public HcResult() {
    }

    public String getHcId() {
        return hcId;
    }

    public void setHcId(String hcId) {
        this.hcId = hcId;
    }

    public String getHcSkId() {
        return hcSkId;
    }

    public void setHcSkId(String hcSkId) {
        this.hcSkId = hcSkId;
    }

    public BigDecimal getHcScore() {
        return hcScore;
    }

    public void setHcScore(BigDecimal hcScore) {
        this.hcScore = hcScore;
    }

    public Date getHcTime() {
        return hcTime;
    }

    public void setHc_time(Date hcTime) {
        this.hcTime = hcTime;
    }
}
