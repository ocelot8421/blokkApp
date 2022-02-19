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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "a.id[" + id + "] " + city + ", " + street + " út/utca";
    }
}
