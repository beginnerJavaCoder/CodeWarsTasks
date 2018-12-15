/*
Original task - https://www.codewars.com/kata/pizza-payments

Kate and Michael want to buy a pizza and share it. 
Depending on the price of the pizza, they are going to divide the costs:

If the pizza is less than €5,- Michael invites Kate, so Michael pays the full price.
Otherwise Kate will contribute 1/3 of the price, but no more than €10 (she's broke :-) and Michael pays the rest.
How much is Michael going to pay? Calculate the amount with two decimals, if necessary.
 */
 
public class PizzaPayments {
    public static double michaelPays(double cost) {
        if (cost <= 5)            return (double)Math.round(cost * 100) / 100;
        if (cost / 3 <= 10)       return (double)Math.round(cost * 200 / 3) / 100;
        return (double)Math.round((cost - 10) * 100) / 100;
    }
}