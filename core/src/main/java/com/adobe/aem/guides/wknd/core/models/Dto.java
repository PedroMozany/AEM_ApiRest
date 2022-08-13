package com.adobe.aem.guides.wknd.core.models;

import java.util.List;

public class Dto {
    private int number;
    private String nameCLient;
    private String nameProduct;
    private String category;
    private double price;
    private double value;

    private List<Product> productsBuy;


    public Dto(int number, Client client, Product product){
        this.number = number;
        this.nameCLient = client.getName();
        this.nameProduct = product.getNameProduct();
        this.category = product.getCategory();
        this.price = product.getPrice();
    }


    public Dto(int number, Client client, List<Product> productsBuy) {
        this.number = number;
        this.nameCLient = client.getName();
        this.productsBuy = productsBuy;
        this.value = sumValue(productsBuy);
    }

    public double sumValue(List<Product> productsBuy) {
        return value = productsBuy.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

}
