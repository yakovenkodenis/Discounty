package com.discounty;


public class Barcode {
    public String Type;
    public String Info;

    public Barcode(String type, String info) {
        Type = type;
        Info = info;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }
}
