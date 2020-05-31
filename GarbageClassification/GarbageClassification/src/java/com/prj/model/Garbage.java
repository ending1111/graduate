package com.prj.model;

public class Garbage {
    private Integer id;

    private String gName;

    private Integer gType;

    private String function;

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName == null ? null : gName.trim();
    }

    public Integer getgType() {
        return gType;
    }

    public void setgType(Integer gType) {
        this.gType = gType;
    }
}