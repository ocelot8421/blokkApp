package hu.progmasters.domain;

public class Shop {

    private int id;
    private String franchise;
    private Address address;
    //private KeyWord keyWord;


    public Shop(String franchise, Address address) {
        this.franchise = franchise;
        this.address = address;
    }

    public Shop(int id, String franchise, Address address) {
        this.id = id;
        this.franchise = franchise;
        this.address = address;
    }

    public Shop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "s.id[" + id + "] " + franchise + " | " +
                address;
    }
}
