package com.audition;

public enum STOCK_VALUES {COLA(1.00, "COLA"), CHIP(.50, "CHIP"), CANDY(.65, "CANDY");
    double price;
    String name;
    STOCK_VALUES(double price, String name){
        this.price = price;
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
