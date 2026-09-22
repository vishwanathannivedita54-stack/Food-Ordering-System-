package com.foodorder;

public class CashPayment extends Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Cash payment selected.");
        System.out.println("Amount to pay: ₹" + amount);
    }
}
