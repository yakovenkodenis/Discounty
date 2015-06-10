package com.discounty;


public class Item {
    public Barcode Barcode;
    public String Name;
    public String Comment;

    public Item(Barcode b, String n, String c) {
        Barcode = b;
        Name = n;
        Comment = c;
    }

    public Barcode getBarcode() {
        return Barcode;
    }

    public void setBarcode(Barcode barcode) {
        Barcode = barcode;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
