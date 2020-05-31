package com.system.garbageclassification.model;

/**
 * 最常见的服务端返回数据格式
 */
public class MResult {

    /**
     * code : -1
     * message : 用户不存在
     * result : null
     */

    private int code;
    private String message;
    private String result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
