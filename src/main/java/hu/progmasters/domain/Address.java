package hu.progmasters.domain;

public class Address {
    private int id;
    private String city;
    private String street;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public Address(int id, String city, String street) {
        this.id = id;
        this.city = city;
        this.street = street;
    }

    @Override
    public String toString() {
        return city + ", " + street + " út/utca";
    }
}
