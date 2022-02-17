package hu.progmasters;

import java.time.LocalDate;
import java.util.List;

import hu.progmasters.domain.Address;
import hu.progmasters.domain.Shop;
import hu.progmasters.domain.Block;
import hu.progmasters.domain.Product;
import hu.progmasters.repository.ShopRepository;
import hu.progmasters.repository.BlockRepository;
import hu.progmasters.repository.ProductRepository;
import hu.progmasters.ui.Ui;

public class Menu {
    ProductRepository productRepository = new ProductRepository();
    ShopRepository shopRepository = new ShopRepository();
    BlockRepository blockRepository = new BlockRepository();
    Ui ui = new Ui();

    public void printMenu() {
        System.out.println("Press number for choose between options");
        System.out.println("1: Create new Product and save to database");
        System.out.println("2: Search Product by id");
        System.out.println("3: Print all Product data");
        System.out.println("4: Create new Shop and save to database");
        System.out.println("5: Search Shop by id");
        System.out.println("6: Print all Shop data");
        System.out.println("7: Create new block");
        System.out.println("8: Search Block by id");
        System.out.println("9: Quit");
    }

    public void startApplication() {

        boolean flag = true;
        productRepository.createProductTable();
        shopRepository.createShopTable();
        blockRepository.createBlockTable(); //TODO createBlockTable
        printMenu();
        while (flag) {
            int userInput = ui.askIntFromUser();
            switch (userInput) {
                case 1:
//                    case 3;
                    List<Product> products = productRepository.printOutAllProductDetails(); //TODO hogy hívom meg a saját case-ét?
                    System.out.println(products);
                    System.out.println("Id of product: ");
                    int productId = ui.askIntFromUser(); //TODO AUTO_INCREMENT-ET BEÁLLÍTANI (minden id-ra)
                    System.out.println("Name of product: ");
                    String productName = ui.askTextFromUser();
                    System.out.println("Price of product: ");
                    double productPrice = ui.askDoubleFromUser();
                    System.out.println("Amount of product:");
                    double productAmount = ui.askDoubleFromUser();
                    String newProductInfo = productRepository.createNewProduct(new Product(productId, productName, productPrice, productAmount));
                    System.out.println(newProductInfo);
//                case 3;
                    List<Product> products1 = productRepository.printOutAllProductDetails(); //TODO hogy hívom meg a saját case-ét?
                    System.out.println(products1);
                    printMenu();
                    break;
                case 2:
                    Product product = productRepository.searchProductById(ui.askIntFromUser());
                    System.out.println(product);
                    printMenu();
                    break;
                case 3:
                    List<Product> products3 = productRepository.printOutAllProductDetails();
                    System.out.println(products3);
                    printMenu();
                    break;
                case 4:
                    List<Shop> shops = shopRepository.printOutAllShopDetails();
                    System.out.println(shops);
                    System.out.println("Name of the new shop:");
                    String name = ui.askTextFromUser();
                    System.out.println("ID of the new shop:");
                    int shopID = ui.askIntFromUser();
                    System.out.println("City of the new shop's address:");
                    String city = ui.askTextFromUser();
                    System.out.println("Street of the new shop's address:");
                    String street = ui.askTextFromUser();
                    System.out.println("ID of the new shop's address:");
                    int addressID = ui.askIntFromUser(); //TODO kinyomtatni ezt is
                    Address address = new Address(addressID, city, street);
                    String newShopInfo = shopRepository.createNewShop(new Shop(shopID, name, address));
                    System.out.println(newShopInfo);
                    printMenu();
                    List<Shop> shops4 = shopRepository.printOutAllShopDetails();
                    System.out.println(shops4);
                    break;
                case 5:
                    System.out.println("ID of the shop which you want to find:");
                    Shop shop = shopRepository.searchShopById(ui.askIntFromUser());
                    System.out.println(shop);
                    printMenu();
                    break;
                case 6:
                    List<Shop> shops11 = shopRepository.printOutAllShopDetails(); //TODO ne legyen ennyi változó (shopsXY)
                    System.out.println(shops11);
                    printMenu();
                    break;
                case 7:
                    System.out.println("Id of block");
                    int blockId = ui.askIntFromUser();
                    System.out.println("Id of shop");
                    int shopId = ui.askIntFromUser();
                    System.out.println("Id of product");
                    int productId1 = ui.askIntFromUser();
                    System.out.println("Final amount: "); //TODO számold össze!!
                    double finalAmount = ui.askDoubleFromUser();
                    System.out.println("Block year: ");
                    int blockYear = ui.askIntFromUser();
                    System.out.println("Block month: ");
                    int blockMonth = ui.askIntFromUser();
                    System.out.println("Block day: ");
                    int blockDay = ui.askIntFromUser();
                    Product product1 = productRepository.searchProductById(productId1);
                    Shop shop1 = shopRepository.searchShopById(shopId);
                    String result = blockRepository.createNewBlock(new Block(blockId, shop1, product1, finalAmount, LocalDate.of(blockYear,blockMonth,blockDay)));
                    System.out.println("Id of block");
                    int blockId = ui.askIntFromUser();
                    System.out.println("Id of shop");
                    int shopId = ui.askIntFromUser();
                    System.out.println("Id of product");
                    int productId1 = ui.askIntFromUser();
                    System.out.println("Final amount: "); //TODO számold össze!!
                    double finalAmount = ui.askDoubleFromUser();
                    System.out.println("Block year: ");
                    int blockYear = ui.askIntFromUser();
                    System.out.println("Block month: ");
                    int blockMonth = ui.askIntFromUser();
                    System.out.println("Block day: ");
                    int blockDay = ui.askIntFromUser();
                    Product product1 = productRepository.searchProductById(productId1);
                    Shop shop1 = shopRepository.searchShopById(shopId);
                    String result = blockRepository.createNewBlock(new Block(blockId, shop1, product1, finalAmount, LocalDate.of(blockYear, blockMonth, blockDay)));
                    System.out.println(result);
                    printMenu();
                    break;
                case 8:
                    Block block = blockRepository.searchBlockById(ui.askIntFromUser()); //TODO BLOKKLEKÉRÉST MEGCSINÁLNI....
                    System.out.println(block);
                    printMenu();
                    break;
                case 9:
                    flag = false;
            }
        }
    }
}

