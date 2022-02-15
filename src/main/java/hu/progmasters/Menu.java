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
                    System.out.println("Id of product: ");
                    int productId = ui.askIntFromUser();
                    System.out.println("Name of product: ");
                    String productName = ui.askTextFromUser();
                    System.out.println("Price of product: ");
                    double productPrice = ui.askDoubleFromUser();
                    System.out.println("Amount of prdouct:");
                    double productAmount = ui.askDoubleFromUser();
                    String newProductInfo = productRepository.createNewProduct(new Product(productId, productName, productPrice, productAmount));
                    System.out.println(newProductInfo);
                    break;
                case 2:
                    Product product = productRepository.searchProductById(ui.askIntFromUser());
                    System.out.println(product);
                    break;
                case 3:
                    List<Product> products = productRepository.printOutAllProductDetails();
                    System.out.println(products);
                    break;
                case 4:
                    System.out.println("Name of the new shop:");
                    String name = ui.askTextFromUser();
                    System.out.println("ID of the new shop:");
                    int shopID = ui.askIntFromUser();
                    System.out.println("City of the new shop's address:");
                    String city = ui.askTextFromUser();
                    System.out.println("Street of the new shop's address:");
                    String street = ui.askTextFromUser();
                    System.out.println("ID of the new shop's address:");
                    int addressID = ui.askIntFromUser();
                    Address address = new Address(addressID, city, street);
                    String newShopInfo = shopRepository.createNewShop(new Shop(shopID, name, address));
                    System.out.println(newShopInfo);
                    printMenu();
                    break;
                case 5:
                    System.out.println("ID of the shop which you want to find:");
                    Shop shop = shopRepository.searchShopById(ui.askIntFromUser());
                    System.out.println(shop);
                    printMenu();
                    break;
                case 6:
                    List<Shop> shops = shopRepository.printOutAllShopDetails();
                    System.out.println(shops);
                    break;
                case 7:
                    System.out.println("");
                    Product product1 = productRepository.searchProductById(ui.askIntFromUser());
                    Shop shop1 = shopRepository.searchShopById(ui.askIntFromUser());
                    String result = blockRepository.createNewBlock(new Block(ui.askIntFromUser(), shop1, product1, ui.askDoubleFromUser(), LocalDate.of(2022,02,01)));
                    System.out.println(result);
                    break;
                case 8:
                    Block block = blockRepository.searchBlockById(ui.askIntFromUser());
                    System.out.println(block);
                    break;
                case 9:
                    flag = false;
            }
        }
    }
}

