package com.foodorder;

import java.util.ArrayList;

public class Order {
    private int orderId;
    private Customer customer;
    private ArrayList<OrderItem> items;

    public Order(int orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getTotal();
        }
        return total;
    }

    public void displayOrder() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());

        for (OrderItem item : items) {
            System.out.println(item.getFoodItem().getName()
                    + " x " + item.getQuantity()
                    + " = ₹" + item.getTotal());
        }

        System.out.println("Total: ₹" + calculateTotal());
    }
}
