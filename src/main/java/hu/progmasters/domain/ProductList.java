package hu.progmasters.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private Product product;
    private Block blokk;
    private int idList;
    private int idProduct;
    private int idBlokk;
    private double price;
    private double amount;

    public ProductList() {
    }


    public ProductList(int idList, int idProduct, int idBlokk, double price, double amount) {
        this.idList = idList;
        this.idProduct = idProduct;
        this.idBlokk = idBlokk;
        this.price = price;
        this.amount = amount;
    }

    public ProductList(int idProduct, int idBlokk, double price, double amount) {
        this.idProduct = idProduct;
        this.idBlokk = idBlokk;
        this.price = price;
        this.amount = amount;
    }

    public ProductList(Product product, int idList, int idProduct, int idBlokk, double price, double amount) {
        this.product = product;
        this.idList = idList;
        this.idProduct = idProduct;
        this.idBlokk = idBlokk;
        this.price = price;
        this.amount = amount;
    }

    public ProductList(Product product, Block blokk, int idList, int idBlokk, double price, double amount) {
        this.product = product;
        this.blokk = blokk;
        this.idList = idList;
        this.idBlokk = idBlokk;
        this.price = price;
        this.amount = amount;
    }


//    @Override
//    public String toString() {
//        return "pl.id[" + idList +
//                "] | p.id[" + idProduct +
//                " " + product +
//                "] | b.id[" + idBlokk +
//                " " + blokk +
//                "] | " + price +
//                " Ft | " + amount + " l/kg/db";
//    }


    @Override
    public String toString() {
        return idBlokk + ", " +
                blokk.getShop().getFranchise() + ", " +
                blokk.getShop().getAddress().getCity() + ", " +
                blokk.getShop().getAddress().getStreet() + " u., " +
                blokk.getDate() + " | " +
                product.getName() + " | " +
                amount + " kg/l/db | " +
                price + " Ft";
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
