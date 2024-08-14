package org.example;

class Order {
    Customer customer;
    Item item;
    int amount;
    public Order(Customer customer, Item item, int amount) {
        this.customer = customer;
        this.item = item;
        this.amount = amount;
    }
    @Override
    public String toString() {
        return ("Order: customer = " + customer +
                 ", item = " + item + ", amount = " + amount);
    }

}
