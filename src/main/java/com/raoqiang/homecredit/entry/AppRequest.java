package com.raoqiang.homecredit.entry;

import java.util.Map;

public class AppRequest {
    private AnthInfo anthInfo;

//    private Map

    private Map params;

    public AnthInfo getAnthInfo() {
        return anthInfo;
    }

    @Override
    public String toString() {
        return "AppRequest{" +
                "anthInfo=" + anthInfo +
                ", params=" + params +
                '}';
    }

    public void setAnthInfo(AnthInfo anthInfo) {
        this.anthInfo = anthInfo;
    }

    public AppRequest() {
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }
}
