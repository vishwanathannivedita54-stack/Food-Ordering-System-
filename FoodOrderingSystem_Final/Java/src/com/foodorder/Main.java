package com.foodorder;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       FOOD ORDERING SYSTEM");
        System.out.println("================================");

        // ==============================
        // CUSTOMER NAME VALIDATION
        // ==============================

        String name;

        while (true) {
            System.out.print("Enter customer name: ");
            name = sc.nextLine().trim();

            if (name.matches("[a-zA-Z ]+")) {
                break;
            }

            System.out.println("Invalid name! Enter a valid name using letters only.");
        }

        // ==============================
        // CONTACT NUMBER VALIDATION
        // ==============================

        String phone;

        while (true) {
            System.out.print("Enter contact number: ");
            phone = sc.nextLine().trim();

            if (phone.matches("[0-9]{10}")) {
                break;
            }

            System.out.println("Invalid contact number!");
            System.out.println("Contact number must contain exactly 10 digits.");
        }

        // ==============================
        // ADDRESS VALIDATION
        // ==============================

        String address;

        while (true) {
            System.out.print("Enter address: ");
            address = sc.nextLine().trim();

            if (!address.isEmpty()) {
                break;
            }

            System.out.println("Address cannot be empty. Please enter a valid address.");
        }

        // Create customer
        Customer customer = new Customer(name, phone, address);

        // Create order
        Order order = new Order(1001, customer);

        int choice;

        // ==============================
        // FOOD ORDERING
        // ==============================

        do {

            System.out.println("\n----------- FOOD MENU -----------");
            System.out.println("1. Burger       - ₹120");
            System.out.println("2. Pizza        - ₹180");
            System.out.println("3. Fried Rice   - ₹150");
            System.out.println("4. Noodles      - ₹130");
            System.out.println("5. Sandwich     - ₹100");
            System.out.println("6. French Fries - ₹80");
            System.out.println("7. Coke         - ₹50");
            System.out.println("0. Finish Order");

            System.out.print("\nSelect food: ");

            // Validate menu input
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number from 0 to 7.");
                sc.next();
                System.out.print("Select food: ");
            }

            choice = sc.nextInt();

            if (choice == 0) {
                break;
            }

            FoodItem food;

            switch (choice) {

                case 1:
                    food = new FoodItem(
                            1, "Burger", "Fast Food", 120);
                    break;

                case 2:
                    food = new FoodItem(
                            2, "Pizza", "Fast Food", 180);
                    break;

                case 3:
                    food = new FoodItem(
                            3, "Fried Rice", "Chinese", 150);
                    break;

                case 4:
                    food = new FoodItem(
                            4, "Noodles", "Chinese", 130);
                    break;

                case 5:
                    food = new FoodItem(
                            5, "Sandwich", "Snacks", 100);
                    break;

                case 6:
                    food = new FoodItem(
                            6, "French Fries", "Snacks", 80);
                    break;

                case 7:
                    food = new FoodItem(
                            7, "Coke", "Drinks", 50);
                    break;

                default:
                    System.out.println(
                            "Invalid food choice! Please select 0 to 7.");
                    continue;
            }

            // ==============================
            // QUANTITY VALIDATION
            // ==============================

            int quantity;

            while (true) {

                System.out.print("Enter quantity: ");

                if (sc.hasNextInt()) {

                    quantity = sc.nextInt();

                    if (quantity > 0) {
                        break;
                    }

                    System.out.println(
                            "Quantity must be greater than 0.");

                } else {

                    System.out.println(
                            "Invalid quantity! Enter a valid number.");
                    sc.next();
                }
            }

            // Add food to order
            OrderItem orderItem =
                    new OrderItem(food, quantity);

            order.addItem(orderItem);

            System.out.println(
                    food.getName() +
                            " added to your order!");

        } while (choice != 0);

        // ==============================
        // FINAL ORDER
        // ==============================

        System.out.println("\n================================");
        System.out.println("          FINAL ORDER");
        System.out.println("================================");

        order.displayOrder();

        // ==============================
        // PAYMENT
        // ==============================

        int paymentChoice;

        while (true) {

            System.out.println("\n----------- PAYMENT -----------");
            System.out.println("1. UPI");
            System.out.println("2. Cash");

            System.out.print("Select payment method: ");

            if (sc.hasNextInt()) {

                paymentChoice = sc.nextInt();

                if (paymentChoice == 1 ||
                        paymentChoice == 2) {

                    break;
                }

                System.out.println(
                        "Invalid payment choice! Enter 1 or 2.");

            } else {

                System.out.println(
                        "Invalid input! Enter 1 for UPI or 2 for Cash.");
                sc.next();
            }
        }

        Payment payment;

        if (paymentChoice == 1) {
            payment = new UpiPayment();
        } else {
            payment = new CashPayment();
        }

        // Process payment
        payment.pay(order.calculateTotal());

        // ==============================
        // SUCCESS MESSAGE
        // ==============================

        System.out.println("\n================================");
        System.out.println("     ORDER PLACED SUCCESSFULLY!");
        System.out.println("================================");

        System.out.println("Customer Name : " + customer.getName());
        System.out.println("Contact       : " + customer.getPhone());
        System.out.println("Address       : " + customer.getAddress());
        System.out.println("Total Amount  : ₹" + order.calculateTotal());

        System.out.println("\nThank you for ordering, "
                + customer.getName() + "!");

        sc.close();
    }
}