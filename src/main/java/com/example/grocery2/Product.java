package com.example.grocery2;

import javafx.scene.image.ImageView;

public class Product {

    // Variables - Properties of the Product
    String productName;
    double productPrice;
    int productQuantity;
   ImageView productImage;
    String productType;

    //Getters and setters


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public ImageView getProductImage() {
        return productImage;
    }

    public void setProductImage(ImageView productImage) {
        this.productImage = productImage;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    // Constructor to set the product details
    public Product(String productName, double productPrice,int productQuantity,ImageView productImage, String productType){
        this.productName=productName;
        this.productPrice=productPrice;
        this.productQuantity=productQuantity;
        this.productImage=productImage;
        this.productType=productType;
    }
}