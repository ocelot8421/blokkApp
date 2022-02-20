package hu.progmasters.domain;

public class Block {

    private int id;
    private Shop shop;
    private String date;


    public Block(Shop shop, String date) {
        this.shop = shop;
        this.date = date;
    }

    public Block(int id, Shop shop, String date) {
        this.id = id;
        this.shop = shop;
        this.date = date;

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


    @Override
    public String toString() {
        return "b.id[" + id +
                "] | " + shop +
                ", date=" + date
                ;
    }
}
