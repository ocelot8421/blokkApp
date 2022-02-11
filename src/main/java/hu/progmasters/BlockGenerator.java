package main.java.hu.progmasters;

import java.util.Scanner;

public class BlockGenerator {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Which franchise?");
            String franchise = scanner.nextLine();
            System.out.println("Which city?");
            String city = scanner.nextLine();
            System.out.println("Which street?");
            String street = scanner.nextLine();
            System.out.println(Shop.shopGenerator(franchise,city,street));


        } catch (IllegalArgumentException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            System.out.println("The block generation is ended.");
        }
    }


}
