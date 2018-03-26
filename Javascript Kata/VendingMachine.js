
var COIN = {'Weight': '', 'Diameter': ''};

const COINS = {'NICKEL': {'Weight': 5.000, 'Diameter': 0.835},
             'DIME' : {'Weight': 2.268, 'Diameter': 0.705},
             'QUARTER': {'Weight': 5.670 , 'Diameter': 0.955}};

class VendingMachine{

    constructor(){
        this.changeIn = 0.0;
        this.inventory = {"COLA": {'count': 10, 'price' : 1 },
                          "CHIP": {'count': 10, 'price': .50},
                          "CANDY": {'count': 10, 'price': .65}};
    }

    render(coin){
        if(this.areEqual(coin, COINS['NICKEL'])){
            return 5;
        }
        else if(this.areEqual(coin, COINS['DIME'])){
                return 10;
        }
        else if(this.areEqual(coin, COINS['QUARTER'])){
            return 25;
        }

    }

    insertCoin(coin){
        let value = this.render(coin);
        this.changeIn = this.changeIn + value;
    }

    getChangeIn(){
        return this.changeIn;
    }

    areEqual(coin1, coin2){
       if(coin1['Weight'] === coin2['Weight'] && coin1['Weight'] === coin2['Weight']){
           return true;
       }
       return false;
    }

    buyProduct(buttonPressed){
        if(buttonPressed === 'a' && this.enoughMoney(this.inventory['COLA']['price'])){
            this.inventory['COLA']['count'] = this.inventory['COLA']['count'] -1;
            return "COLA";
        }
        else if (buttonPressed === 'b' && this.enoughMoney(this.inventory['CHIP']['price'])){
            this.inventory['CHIP']['count'] = this.inventory['CHIP']['count'] -1;
            return "CHIP";
        }
        else if (buttonPressed === 'c' && this.enoughMoney(this.inventory['CANDY']['price'])) {
            this.inventory['CANDY']['count'] = this.inventory['CANDY']['count'] -1;
            return "CANDY";
        }

        if(buttonPressed === 'a' && this.changeIn > 1){
            return "EXACT CHANGE ONLY";
        }
        return "PLEASE INSERT MORE CHANGE";
    }

    enoughMoney(productPrice){
        if(productPrice === 1 && this.changeIn > productPrice){
            return false;
        }

        return this.changeIn >= productPrice;
    }

    colaCount(){
        return this.inventory['COLA']['count'];
    }

    chipCount(){
        return this.inventory['CHIP']['count'];
    }

    candyCount(){
        return this.inventory['CANDY']['count'];
    }





}