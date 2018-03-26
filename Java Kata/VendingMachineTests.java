package com.audition;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;

public class VendingMachineTests {
        private VendingMachine vendingMachine;

        @Before
        public void setUp(){
            this.vendingMachine = new VendingMachine();
            this.vendingMachine.insertMoney(new Coin(5.670, 0.955));
            this.vendingMachine.insertMoney(new Coin(5.670, 0.955));
            this.vendingMachine.insertMoney(new Coin(5.670, 0.955));
            this.vendingMachine.insertMoney(new Coin(5.670, 0.955));

        }

        @Test
        public void testVendingMachineIdentifiesANickel(){
            Coin nickel = new Coin(5.00, 0.835);
            double value = this.vendingMachine.render(nickel);
            assertEquals(.05, value);
        }

        @Test
        public void testVendingMachineIndentifiesADime(){
            Coin dime = new Coin(2.268, 0.705);
            double value = this.vendingMachine.render(dime);
            assertEquals(.10, value);
        }

        @Test
        public void testVendingMachineIdentifiesAQuarter(){
            Coin quarter = new Coin(5.670, 0.955);
            double value = this.vendingMachine.render(quarter);
            assertEquals(.25, value);
        }

        @Test
        public void testVendingMachineChoosesCola(){
            char buttonPressed  = 'a';
            String value = vendingMachine.buyProduct(buttonPressed);
            assertEquals("COLA", value);
        }


        @Test
        public void testVendingMachineChoosesChip(){
            char buttonPressed = 'b';
            String value = vendingMachine.buyProduct(buttonPressed);
            assertEquals("CHIP", value);
        }

        @Test
        public void testVendingMachineChoosesCandy(){
            char buttonPressed = 'c';
            vendingMachine.changeIn = .65;
            String value = vendingMachine.buyProduct(buttonPressed);
            assertEquals("CANDY", value);
        }

        @Test
        public void testVendingMachineSubtractsFromColaStock(){
            char buttonPressed = 'a';
            vendingMachine.buyProduct(buttonPressed);
            assertEquals(9, vendingMachine.inventory.getColaStock());

        }

        @Test
        public void testVendingMachineStubtractsFromChipStock(){
            char buttonPressed = 'b';
            vendingMachine.buyProduct(buttonPressed);
            assertEquals(9, vendingMachine.inventory.getChipStock());
        }

        @Test
        public void testVendingMachineStubtractsFromCandyStock(){
            char buttonPressed = 'c';
            vendingMachine.changeIn = .65; // needs to be exact change to finish test
            vendingMachine.buyProduct(buttonPressed);
            assertEquals(9, vendingMachine.inventory.getCandyStock());
        }


        @Test
        public void testVendingMachineReturnsCorrectChange(){
            char buttonPressed = 'b';
            vendingMachine.insertMoney(new Coin(5.670, 0.955));
            vendingMachine.insertMoney(new Coin(5.670, 0.955));
            vendingMachine.insertMoney(new Coin(5.670, 0.955));
            vendingMachine.buyProduct(buttonPressed);
            assertEquals(1.25, vendingMachine.getRemainingChange());
        }

        @Test
        public void testVendingMachineNotifiesWhenProductIsOut(){
            char buttonPressed = 'b';
            for(int i=0; i < 10; i++){ // only 10 bags in inventory
                vendingMachine.insertMoney(new Coin(5.670, 0.955));
                vendingMachine.insertMoney(new Coin(5.670, 0.955));
                vendingMachine.buyProduct(buttonPressed);
            }

            String value = vendingMachine.buyProduct(buttonPressed);
            assertEquals("OUT OF STOCK", value);
        }

        @Test
        public void testVendingMachineChecksIfChangeInIsEnoughToMakePurchase(){
            char buttonPressed = 'a';
            vendingMachine.changeIn = 0.0; // set money to zero for this test
            String value = vendingMachine.buyProduct(buttonPressed);
            assertEquals("PLEASE INSERT MORE MONEY", value);
        }


        @Test
        public void testVendingMachineChecksIfChangeIsExactForPurchase(){
            char buttonPressed='c';
            String value = vendingMachine.buyProduct(buttonPressed);
            assertEquals("EXACT CHANGE ONLY PLEASE", value);
        }
}
