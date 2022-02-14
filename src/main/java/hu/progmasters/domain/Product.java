package hu.progmasters.domain;

public class Product {

    private int id;
    private String name;
    private double price;
    private double amount;
    // private Category category;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
