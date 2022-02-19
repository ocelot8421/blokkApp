package hu.progmasters.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
//    private int id;
//    private List<Product> productList = new ArrayList<>();
//    private Product product;
//    private int blokkId;
    private int idList;
    private int idProduct;
    private int idBlokk;
    private double price;
    private double amount;

    public ProductList() {
    }



    public ProductList(int idList, int idProduct, int idBlokk) {

    }

    public ProductList(int idProduct, int idBlokk, double price, double amount) {
        this.idProduct = idProduct;
        this.idBlokk = idBlokk;
        this.price = price;
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public double getAmount() {
        return amount;
    }

    public int getIdList() {
        return idList;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdBlokk() {
        return idBlokk;
    }

    public void setIdBlokk(int idBlokk) {
        this.idBlokk = idBlokk;
    }
}
