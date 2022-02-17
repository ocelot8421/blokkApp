package hu.progmasters.domain;

import java.time.LocalDate;
import java.util.List;

public class Block {

    private int id;
    private Shop shop;
    private double amount;
    private LocalDate date;
    private Product product;


    public Block(int id, Shop shop, Product product, double amount, LocalDate date) {
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

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", shop=" + shop +
                ", amount=" + amount +
                ", date=" + date +
                ", product=" + product +
                '}';
    }
}
