package hu.progmasters;

public class Franchise {
    private String name;

    public Franchise(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
