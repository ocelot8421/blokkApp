package hu.progmasters.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductList {
    private int id;
    List<Product> productList = new ArrayList<>();

    public ProductList() {
    }

    public ProductList(int id, List<Product> productList) {
        this.id = id;
        this.productList = productList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
