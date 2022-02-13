package hu.progmasters;

import java.util.Scanner;

public class Shop {
    private Franchise franchise;
    private Address address;

    public Shop(Franchise franchise, Address address) {
        this.franchise = franchise;
        this.address = address;
    }

    public static Shop shopGenerator() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Which franchise?");
            String franchise = scanner.nextLine();
            System.out.println("Which city?");
            String city = scanner.nextLine();
            System.out.println("Which street?");
            String street = scanner.nextLine();
            return new Shop(new Franchise(franchise), new Address(city, street));
    }

    @Override
    public String toString() {
        return franchise + ", " + address;
    }
}
