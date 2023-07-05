package Programa;

public class RegisterProduct {
    private static int productsCount = 1;

    private int id;
    private String product;
    private Double price;

    public RegisterProduct(String product, Double price) {
        this.id = productsCount;
        this.product = product;
        this.price = price;
        productsCount += 1;
    }

    public int getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public Double getPrice() {
        return price;
    }

    public String toString() {
        return "\nId: " + this.getId() +
                "\nProduct name: " + this.getProduct() +
                "\nPrice: " + this.getPrice();
    }
}