package hu.progmasters.domain;

public class Block {

    private int id;
    private Shop shop;
//    private Date date;
    private String date;
//    private Product product;
//   private ProductList productList;



    public Block(Shop shop, String date) {
        this.shop = shop;
        this.date = date;
    }

    public Block(int id, Shop shop, String date) {
        this.id = id;
        this.shop = shop;
        this.date = date;
//        this.product = product;
//        this.productList = pl;
    }

    public Block(int block_id, int shop_id, Shop shop, String date) {

    }


    public int getId() {
        return id;
    }

    public Shop getShop() {
        return shop;
    }


    public String getDate() {
        return date;
    }

//    public ProductList getProductList() {
//        return productList;
//    }

    @Override
    public String toString() {
        return "b.id[" + id +
                "] | " + shop +
                ", date=" + date
   //             ", product=" + productList +
                ;
    }
}
