package com.raoqiang.homecredit.entry;

/**
 * 身份验证类
 */
public class AnthInfo {

    private String username;

    private String password;

    public AnthInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AnthInfo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AnthInfo{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
