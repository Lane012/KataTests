package com.audition;

public class Inventory {
    private int colaStock = 10;
    private int chipStock = 10;
    private int candyStock = 10;

    public void substractColaStock(){
        this.colaStock--;
    }

    public void subtractChipStock(){
        this.chipStock--;
    }

    public void subtractCandyStock(){
        this.candyStock--;
    }

    public int getColaStock() {
        return colaStock;
    }

    public int getChipStock() {
        return chipStock;
    }

    public int getCandyStock() {
        return candyStock;
    }

}
