package hu.progmasters;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import hu.progmasters.domain.*;
import hu.progmasters.repository.ProductListRepository;
import hu.progmasters.repository.ShopRepository;
import hu.progmasters.repository.BlockRepository;
import hu.progmasters.repository.ProductRepository;
import hu.progmasters.ui.Ui;

public class Menu {
    ProductRepository productRepository = new ProductRepository();
    ShopRepository shopRepository = new ShopRepository();
    BlockRepository blockRepository = new BlockRepository();
    ProductListRepository productListRepository = new ProductListRepository();
    Ui ui = new Ui();

    public void printMenu() {
        System.out.println("Press number for choose between options");
        System.out.print(" 1: Create new  Product and save to database | ");
        System.out.print(" 2: Search  Product by id | ");
        System.out.println(" 3: Print all  Product data");
        System.out.print(" 4: Create new  Shop    and save to database | ");
        System.out.print(" 5: Search  Shop    by id | ");
        System.out.println(" 6: Print all  Shop    data");
        System.out.print(" 7: Create new  Blokk   and save to database | ");
        System.out.print(" 8: Search  Blokk   by id | ");
        System.out.println(" 9: Print all  Blokk   data");
        System.out.print("10: Create new  P.List  and save to database | ");
        System.out.print("11: Search  P.List  by id | ");
        System.out.println("12: Print all product order by price | ");
        System.out.println("13: Print all  P.List  data");
        System.out.println("14: Quit");
    }

    public void startApplication() {

        boolean flag = true;
        productRepository.createProductTable();
        shopRepository.createShopTable();
        blockRepository.createBlockTable(); //TODO createBlockTable
        productListRepository.createProductListTable();
        printMenu();
        while (flag) {
            int userInput = ui.askIntFromUser();
            switch (userInput) {
                case 1:
                    for (Product productDetail : productRepository.printOutAllProductDetails()) {
                        System.out.println(productDetail);
                    }
                    System.out.println("Name of product: ");
                    String productName = ui.askTextFromUser();
                    String newProductInfo = productRepository.createNewProduct(new Product(productName));
                    System.out.println(newProductInfo);
                    for (Product productDetail : productRepository.printOutAllProductDetails()) {
                        System.out.println(productDetail);
                    }
                    printMenu();
                    break;
                case 2:
                    System.out.println("Which product are you looking for? (id)");
                    Product product = productRepository.searchProductById(ui.askIntFromUser());
                    System.out.println(product);
                    printMenu();
                    break;
                case 3:
                    for (Product productDetail : productRepository.printOutAllProductDetails()) {
                        System.out.println(productDetail);
                    }
                    printMenu();
                    break;
                case 4:
                    for (Shop shopDetail : shopRepository.printOutAllShopDetails()) {
                        System.out.println(shopDetail);
                    }
                    System.out.println("Name of the new shop:");
                    String name = ui.askTextFromUser();
                    System.out.println("City:");
                    String city = ui.askTextFromUser();
                    System.out.println("Street:");
                    String street = ui.askTextFromUser();
                    Address address = new Address(city, street);
                    String newShopInfo = shopRepository.createNewShop(new Shop(name, address));
                    System.out.println(newShopInfo);
                    for (Shop shopDetail : shopRepository.printOutAllShopDetails()) {
                        System.out.println(shopDetail);
                    }
                    printMenu();
                    break;
                case 5:
                    System.out.println("ID of the shop which you want to find:");
                    Shop shop = shopRepository.searchShopById(ui.askIntFromUser());
                    System.out.println(shop);
                    printMenu();
                    break;
                case 6:
                    for (Shop shopDetail : shopRepository.printOutAllShopDetails()) {
                        System.out.println(shopDetail);
                    }
                    printMenu();
                    break;
                case 7:
                    for (Shop shopDetail : shopRepository.printOutAllShopDetails()) {
                        System.out.println(shopDetail);
                    }
                    System.out.println("Id of shop");
                    int shopId = ui.askIntFromUser();
                    System.out.println(productRepository.printOutAllProductDetails());
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
//                    Product product1 = productRepository.searchProductById(productId1);
                    ProductList product1 = new ProductList();
                    Shop shop1 = shopRepository.searchShopById(shopId);
                    String result = blockRepository.createNewBlock(new Block(shop1, (blockYear + "-" + blockMonth + "-" + blockDay)));
                    System.out.println(result);
                    printMenu();
                    break;
                case 8:
                    Block block = blockRepository.searchBlockById(ui.askIntFromUser()); //TODO BLOKKLEKÉRÉST MEGCSINÁLNI....
                    System.out.println(block);
                    printMenu();
                    break;
                case 9:
                    for (Block blockDetail : blockRepository.printOutAllBlockDetails()) {
                        System.out.println(blockDetail);
                    }
                    printMenu();
                    break;
                case 10:
                    System.out.println("ProductList DB:");
                    for (ProductList productListDetail : productListRepository.printOutAllProductListDetails()) {
                        System.out.println(productListDetail);
                    }
                    System.out.println("Blokk DB: ");
                    for (Block blockDetail : blockRepository.printOutAllBlockDetails()) {
                        System.out.println(blockDetail);
                    }
                    System.out.println("Blokk ID:");
                    int blokkId = ui.askIntFromUser();
                    for (Product productDetail : productRepository.printOutAllProductDetails()) {
                        System.out.println(productDetail);
                    }
                    System.out.println("Product ID");
                    int productId = ui.askIntFromUser();
                    System.out.println("Price:");
                    double price = ui.askDoubleFromUser();
                    System.out.println("Amount: (example: 0.2)");
                    double amount = ui.askDoubleFromUser();
                    String result2 = productListRepository.createNewProductList(
                            new ProductList(productId, blokkId, price, amount)
                    );
                    System.out.println("ProductList DB:");
                    for (ProductList productListDetail : productListRepository.printOutAllProductListDetails()) {
                        System.out.println(productListDetail);
                    }
                    System.out.println("Blokk DB: ");
                    for (Block blockDetail : blockRepository.printOutAllBlockDetails()) {
                        System.out.println(blockDetail);
                    }
                    break;
                case 11:
                    System.out.println("ProductList ID: ");
                    ProductList productList = productListRepository.searchProductListById(ui.askIntFromUser());
                    System.out.println(productList);
                    printMenu();
                    break;
                case 12:
                    List<ProductList> products = productListRepository.searchAllProductOrderByPrice();
                    System.out.println(products);
                    printMenu();
                    break;

                case 13:
                    for (ProductList productListDetail : productListRepository.printOutAllProductListDetails()) {
                        System.out.println(productListDetail);
                    }
                    printMenu();
                    break;
                case 14:
                    flag = false;
            }
        }
    }

    private void printList(List<Block> list) {
        for (Object record : list) {
            System.out.println(record);
        }
    }
}

