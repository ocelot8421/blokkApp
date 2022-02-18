package hu.progmasters.domain;

import java.time.LocalDate;

public class Block {

    private int id;
    private Shop shop;
    private LocalDate date;
//    private Product product;
 //   private ProductList productList;


    public Block(int id, Shop shop, LocalDate date) {
        this.id = id;
        this.shop = shop;
        this.date = date;
//        this.product = product;
//        this.productList = pl;
    }

    public int getId() {
        return id;
    }

    public Shop getShop() {
        return shop;
    }


    public LocalDate getDate() {
        return date;
    }

//    public ProductList getProductList() {
//        return productList;
//    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", shop=" + shop +
                ", date=" + date +
   //             ", product=" + productList +
                '}';
    }
}
