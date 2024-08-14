package org.example;

class Item {
    String nameItem;
    int price;

    public Item (String nameItem, int price) {
        this.nameItem = nameItem;
        this.price = price;

    }
    @Override
    public String toString(){
        return ("Item name " + nameItem + " price " + price);
    }
}
