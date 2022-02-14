package hu.progmasters;

import java.util.Scanner;

public class Shop {
    private String name;
    private Address address;

    public Shop(String name, Address address) {
        this.name = name;
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
            return new Shop(franchise, new Address(city, street));
    }

    @Override
    public String toString() {
        return  name + " " + address;
    }
}
