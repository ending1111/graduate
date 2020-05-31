package com.system.garbageclassification.model;

/**
 * 搜索历史
 */
public class History {

    private int id;
    private int type;  //垃圾分类
    private String name;  //垃圾名
    private String function;  //处理方法

    public History(int id,int type, String name,String function) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.function = function;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
