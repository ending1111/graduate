package com.prj.model;

public class Recycle {
    private Integer id;

    private Integer garbageType;

    private String function;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGarbageType() {
        return garbageType;
    }

    public void setGarbageType(Integer garbageType) {
        this.garbageType = garbageType;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function == null ? null : function.trim();
    }
}