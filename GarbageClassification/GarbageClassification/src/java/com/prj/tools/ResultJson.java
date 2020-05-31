package com.prj.tools;

public class ResultJson<T> {
    private Integer code;
    private String message;
    private T result;

    public ResultJson(Integer code, String msg) {
        this.code = code;
        this.message = msg;
    }



    public ResultJson(Integer code) {
        this.code = code;
    }



    public ResultJson(Integer code,String message, T result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return (new StringBuilder("ResultJson{"))
                .append(" code=").append(this.getCode())
                .append(", message=").append(this.getMessage())
                .append(", result=").append(this.getResult() != null ? this.getResult() : "")
                .toString();
    }
}
