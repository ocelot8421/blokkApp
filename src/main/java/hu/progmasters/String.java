package hu.progmasters;

public class String {
    private java.lang.String city;
    private java.lang.String street;

    public String(java.lang.String city, java.lang.String street) {
        this.city = city;
        this.street = street;
    }

    @Override
    public java.lang.String toString() {
        return city + ", " + street + " utca";
    }
}
