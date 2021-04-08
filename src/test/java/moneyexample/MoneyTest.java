package moneyexample;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
@Test
public class MoneyTest {

    @Test
    public void testMultiplication(){
        Money five = Money.dollar(5);
        assertEquals(five.times(2), Money.dollar(10));
        assertEquals(five.times(3), Money.dollar(15));
    }

    @Test
    public void testEquality(){
        assertEquals(Money.dollar(5), Money.dollar(5));
        assertNotEquals(Money.dollar(6), Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.franc(5));
    }

    @Test
    public void testCurrency(){
        assertEquals("USD",Money.dollar(1).currency());
        assertEquals("CHF",Money.franc(1).currency());
    }

    public void testSimpleAddition(){
        var five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(reduced, Money.dollar(10));
    }

    public void testPlusReturnsSum(){
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(sum.augend,five);
        assertEquals(sum.addend,five);
    }

    public void testReduceSum(){
        Expression sum = new Sum(Money.dollar(3),Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum,"USD");
        assertEquals(result, Money.dollar(7));
    }

    public void testReduceMoney(){
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1),"USD");
        assertEquals(result, Money.dollar(1));
    }

    public  void testReduceMoneyDifferentCurrency(){
        Bank bank = new Bank();
        bank.addRate("CHF","USD",2);
        Money result=bank.reduce(Money.franc(2),"USD");
        assertEquals(result,Money.dollar(1));
    }

    public void testIdentityRate(){
        assertEquals(new Bank().rate("USD","USD"),1);
    }

}
