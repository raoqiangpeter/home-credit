package com.raoqiang.homecredit.entry;

public class Request {

    private AnthInfo anthInfo;

//    private Map

    private Params params;

    public AnthInfo getAnthInfo() {
        return anthInfo;
    }

    public Request(AnthInfo anthInfo, Params params) {
        this.anthInfo = anthInfo;
        this.params = params;
    }

    public Request() {
    }

    public void setAnthInfo(AnthInfo anthInfo) {
        this.anthInfo = anthInfo;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Request{" +
                "anthInfo=" + anthInfo +
                ", params=" + params +
                '}';
    }
}
