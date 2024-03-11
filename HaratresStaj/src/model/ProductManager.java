package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ProductManager {
    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void displayProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void sortProducts(String sortBy, String sortOrder) {
        Comparator<Product> comparator = null;
        switch (sortBy) {
            case "name":
                comparator = Comparator.comparing(Product::getName);
                break;
            case "price":
                comparator = Comparator.comparing(Product::getPrice);
                break;
            case "stock":
                comparator = Comparator.comparing(Product::getStock);
                break;
            case "rating":
                comparator = Comparator.comparing(Product::getRating);
                break;
            default:
                System.out.println("Gecersiz siralama kriteri!");
                return;
        }

        if (sortOrder.equalsIgnoreCase("azalan")) {
            comparator = comparator.reversed();
        }

        Collections.sort(products, comparator);
    }

    public void addToCart(String productName, int quantity) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                if (product.getStock() >= quantity) {
                    System.out.println(productName + " sepetinize eklendi.");
                    product.setStock(product.getStock() - quantity);
                    return;
                } else {
                    System.out.println("Stokta yeterli " + productName + " yok.");
                    return;
                }
            }
        }
        System.out.println("Ürün bulunamadi.");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductManager productManager = new ProductManager();
        double totalAmount = productManager.products.stream()
                .mapToDouble(product -> product.getPrice() * product.getStock())
                .sum();

        System.out.print("Kac farkli urun gireceksiniz: ");
        int numProducts = scanner.nextInt();
        scanner.nextLine(); 

        for (int i = 1; i <= numProducts; i++) {
            System.out.println("Urun " + i + ":");
            System.out.print("Urun Adi: ");
            String name = scanner.nextLine();
            System.out.print("Birim Fiyat: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Stok Miktari: ");
            int stock = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Degerlendirme Puani: ");
            double rating = scanner.nextDouble();
            scanner.nextLine(); 

            while (price < 50 || price > 100) {
                System.out.println("Fiyat 50 ve 100 arasinda olmalidir.");
                System.out.print("Birim Fiyat: ");
                price = scanner.nextDouble();
                scanner.nextLine(); 
            }

            while (stock < 1) {
                System.out.println("Stok miktari en az 1 olmalidir.");
                System.out.print("Stok Miktarı: ");
                stock = scanner.nextInt();
                scanner.nextLine(); 
            }

            while (name.length() > 20) {
                System.out.println("Urun adi 20 karakterden fazla olamaz.");
                System.out.print("Urun Adi: ");
                name = scanner.nextLine();
            }

            Product product = new Product(name, price, stock, rating);
            productManager.addProduct(product);
        }

        System.out.print("Urunleri hangi kritere gore siralamak istersiniz? (name/price/stock/rating): ");
        String sortBy = scanner.nextLine();
        System.out.print("Siralama türü artan mi yoksa azalan mi olsun? (artan/azalan): ");
        String sortOrder = scanner.nextLine();
        productManager.sortProducts(sortBy, sortOrder);

        System.out.println("Siralanmis Urunler:");
        productManager.displayProducts();

        while (true) {
            System.out.print("Sepete urun eklemek ister misiniz? (Evet/Hayır): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("Hayir")) {
                break;
            } else if (choice.equalsIgnoreCase("Evet")) {
                System.out.print("Eklemek istediginiz urunun adi: ");
                String productName = scanner.nextLine();
                System.out.print("Eklemek istediginiz adet: ");
                int quantity = scanner.nextInt();
                scanner.nextLine(); 
                productManager.addToCart(productName, quantity);
                
                
                
             } else {
               System.out.println("Gecersiz secenek!");
             }
        }

        System.out.println("Sepetiniz:");
        productManager.displayProducts();
        System.out.println("Sepet Toplami: " + totalAmount);
        

        scanner.close();
    }
}

