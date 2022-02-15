package hu.progmasters.domain;

public class Product {

    private int id;
    private String name;
    private double price;
    private double amount;
    // private Category category;

    public Product(int id, String name, double price, double amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
