package hu.progmasters;

import java.util.List;
import hu.progmasters.domain.Address;
import hu.progmasters.domain.Shop;
import hu.progmasters.domain.Block;
import hu.progmasters.domain.Product;
import hu.progmasters.repository.ShopRepository;
import hu.progmasters.repository.BlockRepository;
import hu.progmasters.repository.ProductRepository;
import hu.progmasters.ui.Ui;

import java.util.List;

public class Menu {
    ProductRepository productRepository = new ProductRepository();
    ShopRepository shopRepository = new ShopRepository();
    BlockRepository blockRepository = new BlockRepository();
    Ui ui = new Ui();

    public void printAirportSystemMenu() {
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
        printAirportSystemMenu();
        while (flag) {
            int userInput = ui.askIntFromUser();
            switch (userInput) {
                case 1:
                    String newProductInfo = productRepository.createNewProduct(new Product(ui.askIntFromUser(),
                            ui.askTextFromUser(), ui.askIntFromUser()));
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
                    Address address = new Address(ui.askIntFromUser(), ui.askTextFromUser(), ui.askTextFromUser());
                    String newShopInfo = shopRepository.createNewShop(new Shop(ui.askIntFromUser(),
                            ui.askTextFromUser(), address));
                    System.out.println(newShopInfo);
                    break;
                case 5:
                    Shop shop = shopRepository.searchShopById(ui.askIntFromUser());
                    System.out.println(shop);
                    break;
                case 6:
                    List<Shop> shops = shopRepository.printOutAllShopDetails();
                    System.out.println(shops);
                    break;
                case 7:
                    Product productForBlock = productRepository.searchProductById(ui.askIntFromUser());
                    Shop startShopForBlock = shopRepository.searchShopById(ui.askIntFromUser());
                    Shop endShopForBlock = shopRepository.searchShopById(ui.askIntFromUser());
                    String result = blockRepository.createNewBlock(new Block(ui.askIntFromUser(), productForBlock,
                            startShopForBlock, endShopForBlock));
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

