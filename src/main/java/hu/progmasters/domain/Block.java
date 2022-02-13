package hu.progmasters.domain;

import java.time.LocalDate;
import java.util.List;

public class Block {

    private int id;
    private Shop shop;
    private double amount;
    private LocalDate date;
    private Product product;

    public Block(int id, Shop shop, double amount, LocalDate date, Product product) {
        this.id = id;
        this.shop = shop;
        this.amount = amount;
        this.date = date;
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public Shop getShop() {
        return shop;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public Product getProduct() {
        return product;
    }
}
