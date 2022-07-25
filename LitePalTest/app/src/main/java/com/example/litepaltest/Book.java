package com.example.litepaltest;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class Book extends LitePalSupport {
    private int id;
    private String author;
    private double price;
    private int pags;
    private String names;
    private String press;
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPags() {
        return pags;
    }

    public void setPags(int pags) {
        this.pags = pags;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

}
