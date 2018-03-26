
describe("Test VendingMachine indentifies coins by weight and diameter", function() {
    var coin;
    var vendingMachine;
    beforeEach(function () {
       coin = COIN;
       vendingMachine = new VendingMachine();
   });


  it("test vending machine recognizes nickel", function() {
      coin.Weight = 5.000;
      coin.Diameter = 0.835;
     let value = vendingMachine.render(coin);
     expect(value).toBe(5);
  });

  it("test vending machine recognizes dime", function(){
      coin.Weight = 2.268;
      coin.Diameter = 0.705;
      let value = vendingMachine.render(coin);
      expect(value).toBe(10);

  });

  it("test vending machine recognises quarter", function(){
      coin.Weight =  5.670;
      coin.Diameter = 0.955;
      let value = vendingMachine.render(coin);
      expect(value).toBe(25);
  });


});

describe("Test vending machine gets right product when button is pressed", function(){
    var coin;
    var vendingMachine;
    beforeEach(function () {
        coin = COIN;
        vendingMachine = new VendingMachine();
        vendingMachine.changeIn = 1; // add this so we can make purchases
    });

    it("Test vending machine gets COLA when button a is pressed", function(){
        let buttonPressed = 'a';
        let value = vendingMachine.buyProduct(buttonPressed);
        expect(value).toBe("COLA");
    });

    it("Test vending machine gets CHIP when button b is pressed", function(){
        let buttonPressed = 'b';
        let value = vendingMachine.buyProduct(buttonPressed);
        expect(value).toBe("CHIP");
    });

    it("Test vending machine gets CANDY when button c is pressed", function(){
        let buttonPressed = 'c';
        let value = vendingMachine.buyProduct(buttonPressed);
        expect(value).toBe("CANDY");
    });
});

describe("Test inventory count and subtraction when product is bought", function () {
    var coin;
    var vendingMachine;
    beforeEach(function () {
        coin = COIN;
        vendingMachine = new VendingMachine();
        vendingMachine.changeIn = 1;
    });

    it("test COLA inventory is subtracted when cola is bought", function () {
        let buttonPressed = 'a';
        vendingMachine.buyProduct(buttonPressed);
        let value = vendingMachine.colaCount();
        expect(value).toBe(9);
    });

    it("test CHIP inventory is subtracted when chip is bought", function () {
        let buttonPressed = 'b';
        vendingMachine.buyProduct(buttonPressed);
        let value = vendingMachine.chipCount();
        expect(value).toBe(9);
    });

    it("test CANDY inventory is subtracted when candy is bought", function(){
        let buttonPressed = 'c';
        vendingMachine.buyProduct(buttonPressed);
        let value = vendingMachine.candyCount();
        expect(value).toBe(9);
    });

    it("test vending machine notifies when inventory is out", function () {
        let buttonPressed = 'a';
        for(let i=0; i<10; i++){ // run 10 times to exhaust COLA inventory
            vendingMachine.changeIn = 1;
            vendingMachine.buyProduct(buttonPressed);
        }
        let value = vendingMachine.colaCount();
        expect(value).toBe(0);
    });

});


describe("Test inventory keeps track of change, checks correct change for product and returns proper change", function () {
    var coin;
    var vendingMachine;
    beforeEach(function () {
        coin = COIN;
        vendingMachine = new VendingMachine();
    });

    it("test vendingMachine collects change when inserted", function () {
        coin.weight = 5.670;
        coin.Diameter = 0.955;
        vendingMachine.insertCoin(coin);
        let value = vendingMachine.getChangeIn();
        expect(value).toBe(25);
    });

    it("test vending Machine rejects purchase if not enough changeIn ", function () {
        vendingMachine.changeIn = .25; // cutting corners so I don't have to do inserts (we already know the insertCoin works just fine)
        let buttonPressed = 'a';
        let value = vendingMachine.buyProduct(buttonPressed);
        expect(value).toBe("PLEASE INSERT MORE CHANGE");
    });

    it("test vending machine rejects if change is not exact for cola", function () {
        vendingMachine.changeIn = 1.05;
        let buttonPressed = 'a';
        let value = vendingMachine.buyProduct(buttonPressed);
        expect(value).toBe("EXACT CHANGE ONLY");
    });



});


