package main.java.hu.progmasters;

public class Shop {
    private Franchise franchise;
    private Address address;

    public Shop(Franchise franchise, Address address) {
        this.franchise = franchise;
        this.address = address;
    }

    public static Shop shopGenerator(String franchise, String city, String street) {
        return new Shop(new Franchise(franchise), new Address(city, street));
    }

    @Override
    public String toString() {
        return franchise + ", " + address;
    }
}
