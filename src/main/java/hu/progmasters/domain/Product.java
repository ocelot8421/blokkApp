package hu.progmasters.domain;

public class Product {

    private int id;
    private String name;

  //  private double price; //TODO az ár függ a helytől és időtől is - blokknak lesz a példányváltozója
  //  private double amount;
    // private Category category;

    public Product(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "\n" + id + " | " + name + " | " + price + " | " + amount;
    }
}
