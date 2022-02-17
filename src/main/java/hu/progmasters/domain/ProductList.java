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

    public ProductList() {
    }

//    public ProductList(int id, Product product) {
//        this.id = id;
//        this.product = product;
//        this.productList.add(product);
//    }

    public ProductList(int idList, int idProduct, int idBlokk) {

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
