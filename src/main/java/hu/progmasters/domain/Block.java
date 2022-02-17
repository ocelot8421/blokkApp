package hu.progmasters.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Block {

    private int id;
    private Shop shop;
    private double amount;
    private LocalDate date;
//    private Product product;
    private ProductList productList;


    public Block(int id, Shop shop, ProductList productList, double amount, LocalDate date) {
        this.id = id;
        this.shop = shop;
        this.amount = amount;
        this.date = date;
        this.productList = productList;
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

    public ProductList getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", shop=" + shop +
                ", amount=" + amount +
                ", date=" + date +
                ", product=" + productList +
                '}';
    }
}
