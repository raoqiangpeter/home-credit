package com.raoqiang.homecredit.entry;

import java.util.List;

/**
 * 请求响应类。通用类
 */
public class Response {

    // 状态 1表示正常
    private int status;

    // 响应信息/说明
    private String message;

    // 响应是否成功
    private boolean success;

    // data长度
    private int length;

    // 数据体
    private List data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }



    public Response() {
    }

    public Response(int status, String message, boolean success) {
        this.status = status;
        this.message = message;
        this.success = success;
    }

    public Response(int status, String message, boolean success, List data, int length) {
        this.status = status;
        this.message = message;
        this.success = success;
        this.data = data;
        this.length = length;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", success=" + success +
                ", length=" + length +
                ", data=" + data +
                '}';
    }
}
