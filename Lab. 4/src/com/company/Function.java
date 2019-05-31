package com.company;

public class Function {
    private String name, sName;

    public Function(String name, String sName) {
        this.name = name;
        this.sName = sName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String toString(){
        return name;
    }
}
