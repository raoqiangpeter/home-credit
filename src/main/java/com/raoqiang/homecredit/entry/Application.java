package com.raoqiang.homecredit.entry;

import java.util.Map;

public class Application {

    private AnthInfo anthInfo;

    private Map data;


    public Application(AnthInfo anthInfo, Map data) {
        this.anthInfo = anthInfo;
        this.data = data;
    }

    public Application() {
    }

    public AnthInfo getAnthInfo() {
        return anthInfo;
    }

    public void setAnthInfo(AnthInfo anthInfo) {
        this.anthInfo = anthInfo;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Application{" +
                "anthInfo=" + anthInfo +
                ", data=" + data +
                '}';
    }
}
