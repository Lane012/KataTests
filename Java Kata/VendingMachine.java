package com.audition;

import static com.audition.VendingMachine.COIN.DIME;
import static com.audition.VendingMachine.COIN.NICKEL;
import static com.audition.VendingMachine.COIN.QUARTER;

public class VendingMachine {
    public Inventory inventory;
    public double changeIn;



    public VendingMachine(){
        this.inventory = new Inventory();
        this.changeIn =  0.0;
    }

    public enum COIN {
        NICKEL(5.0, 0.835), DIME(2.268, 0.705), QUARTER(5.670, 0.955);

        private final double weight;
        private final double diameter;

        COIN(double weight, double diameter) {
            this.weight = weight;
            this.diameter = diameter;
        }

        boolean isEqual(double weight, double diameter) {
            if(weight == this.weight && diameter == this.diameter) {
                return true;
            }
            return false;
        }
    }

    public void insertMoney(Coin coin){
        try {
            double value = this.render(coin);
            this.increaseChangeInMachine(value);
        }
        catch(IllegalArgumentException e){
            System.out.println("Coin is invalid");
        }
    }

    public void increaseChangeInMachine(double change){
        this.changeIn = this.changeIn + change;

    }

    public double render(Coin coin) {
        double weight = coin.getWeight();
        double diameter = coin.getDiameter();
        if (NICKEL.isEqual(weight, diameter)) {
            return .05;
        } else if (DIME.isEqual(weight, diameter)) {
            return .10;
        } else if (QUARTER.isEqual(weight, diameter)) {
            return .25;
        } else {
            throw new IllegalArgumentException("Coin not recognized by system");
        }
    }

    public String buyProduct(char buttonPressed) {
        String outOfStock = "OUT OF STOCK";
        double price = 0;
        switch (buttonPressed) {
            case 'a':
                price = STOCK_VALUES.COLA.price;
                if(inventory.getColaStock() >0) {
                    if(this.changeIn >= price) {
                        this.subtractFromChangeIn(price);
                        this.inventory.substractColaStock();
                        return STOCK_VALUES.COLA.getName();
                    }
                    return "PLEASE INSERT MORE MONEY";
                }

                return outOfStock;

            case 'b':
                price = STOCK_VALUES.CHIP.price;
                if(inventory.getChipStock() > 0) {
                    if(this.changeIn >= price) {
                        this.subtractFromChangeIn(price);
                        this.inventory.subtractChipStock();
                        return STOCK_VALUES.CHIP.getName();
                    }
                    return "PLEASE INSERT MORE MONEY";
                }
                return outOfStock;

            case 'c':
                price = STOCK_VALUES.CANDY.price;
                if(inventory.getCandyStock() > 0) {
                    if(this.changeIn == price) {
                        this.subtractFromChangeIn(price);
                        this.inventory.subtractCandyStock();
                        return STOCK_VALUES.CANDY.getName();
                    }
                    else if(this.changeIn > price){
                        return "EXACT CHANGE ONLY PLEASE";
                    }
                    return "PLEASE INSERT MORE MONEY";
                }
                return outOfStock;
            default:
                return "ERROR";
        }
    }

    private void subtractFromChangeIn(double productAmount){
        this.changeIn = this.changeIn - productAmount;
    }

    public double getRemainingChange(){
        return this.changeIn;
    }
}
