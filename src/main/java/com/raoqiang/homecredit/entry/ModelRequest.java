package com.raoqiang.homecredit.entry;


import java.util.Map;

public class ModelRequest {

    private AnthInfo anthInfo;

//    private Map

    private Map params;

    public AnthInfo getAnthInfo() {
        return anthInfo;
    }

    public ModelRequest(AnthInfo anthInfo, Map params) {
        this.anthInfo = anthInfo;
        this.params = params;
    }

    public ModelRequest() {
    }

    public void setAnthInfo(AnthInfo anthInfo) {
        this.anthInfo = anthInfo;
    }

    public Map getParams() {
        return params;
    }

    public void setParams(Map params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "ModelRequest{" +
                "anthInfo=" + anthInfo +
                ", params=" + params +
                '}';
    }
}
