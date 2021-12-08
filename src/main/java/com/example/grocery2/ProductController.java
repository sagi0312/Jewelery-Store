package com.example.grocery2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductController extends Application {
    static ArrayList<Product> ALL_PRODUCTS= new ArrayList<>();
    Scene scene1;
    Scene scene2;
    Scene scene3;

    //add products to the arraylist
    public static void addProduct(Product prod) {
        ALL_PRODUCTS.add(prod);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Products");

        final FileChooser fileChooser = new FileChooser();

        GridPane root = new GridPane();
        root.setPadding(new Insets(10,10,10,10));
        root.setVgap(6);
        root.setVgap(8);

        Label pName=new Label("Product Name: ");
        GridPane.setConstraints(pName,0,1);
        TextField nameField=new TextField();
        GridPane.setConstraints(nameField,1,1);
        Label pPrice=new Label("Price: ");
        GridPane.setConstraints(pPrice,0,2);
        TextField priceField=new TextField();
        GridPane.setConstraints(priceField,1,2);
        Label pQuantity=new Label("Quantity: ");
        GridPane.setConstraints(pQuantity,0,3);
        TextField quantityField=new TextField();
        GridPane.setConstraints(quantityField,1,3);
        Label pImage=new Label("Product Image: ");
        GridPane.setConstraints(pImage,0,4);
        Label imageAddress = new Label("no files selected");
        GridPane.setConstraints(imageAddress,1,4);
        final Button openImage = new Button("Open a Picture...");
        GridPane.setConstraints(openImage,2,4);
        //Creating the image view
        ImageView imageView = new ImageView();

        openImage.setOnAction(
                e -> {
                    //File file = fileChooser.showSaveDialog(primaryStage);
                    File file = fileChooser.showOpenDialog(primaryStage);

                    if (file != null) {
                            imageAddress.setText(file.getAbsolutePath()
                                    + "  selected");
                        try {

                            //creating the image object
                            Image image = new Image(new FileInputStream(file.getAbsolutePath()));

                            //Setting image to the image view
                            imageView.setImage(image);
                            //Setting the image view parameters
                            imageView.setX(10);
                            imageView.setY(10);
                            imageView.setFitWidth(200);
                            imageView.setPreserveRatio(true);
                            GridPane.setConstraints(imageView,0,5);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
        Label pType=new Label("Product Type: ");
        GridPane.setConstraints(pType,0,6);
        TextField typeField=new TextField();
        GridPane.setConstraints(typeField,1,6);
        //add button
        Button addBtn = new Button();
        addBtn.setText("Add Product");
        addBtn.setOnAction(event -> {
            System.out.println("Inside the ActionEvent!");

            Product product=new Product(nameField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(quantityField.getText()),
                    imageView,
                    typeField.getText());
            //product.setProductImage(productImage);
            ProductController.addProduct(product);
            AlertBox.display("Addition", "Product added successfully!!");
        });
        GridPane.setConstraints(addBtn,0,7);

        //Display Button
        Button displayBtn = new Button();
        displayBtn.setText("Display All Products");
        GridPane.setConstraints(displayBtn,0,8);
        displayBtn.setOnAction(event -> {
            VBox displayVBox =new VBox();
            //displayVBox.setPadding(new Insets(50,50,50,50));
            TableView<Product> tableView=new TableView<>();
            //Column
            TableColumn<Product,String> nameCol = new TableColumn<>("Name");
            nameCol.setMinWidth(200);
            nameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));

            TableColumn<Product,Double> priceCol = new TableColumn<>("Price");
            priceCol.setMinWidth(100);
            priceCol.setCellValueFactory(new PropertyValueFactory<>("productPrice"));

            TableColumn<Product,String> quantityCol = new TableColumn<>("Quantity");
            quantityCol.setMinWidth(100);
            quantityCol.setCellValueFactory(new PropertyValueFactory<>("productQuantity"));

            TableColumn<Product,ImageView> imageCol = new TableColumn<>("Image");
            imageCol.setMinWidth(200);
            imageCol.setCellValueFactory(new PropertyValueFactory<>("productImage"));

            TableColumn<Product,String> typeCol = new TableColumn<>("Type");
            typeCol.setMinWidth(100);
            typeCol.setCellValueFactory(new PropertyValueFactory<>("productType"));

            ObservableList<Product> oList = FXCollections.observableArrayList(ALL_PRODUCTS);
            tableView.setItems(oList);
            tableView.getColumns().addAll(nameCol,priceCol,quantityCol,imageCol,typeCol);

            Button orig = new Button("Go to main page");
            orig.setOnAction(e -> primaryStage.setScene(scene1));
            displayVBox.getChildren().addAll(tableView,orig);
            scene3 = new Scene(displayVBox, 600, 500);
            primaryStage.setScene(scene3);
                });

        // Delete Button
        Button deleteBtn = new Button();
        deleteBtn.setText("Delete Product");
        GridPane.setConstraints(deleteBtn,1,7);
        deleteBtn.setOnAction(event -> {
            System.out.println("Inside delete button event");
            String result = ConfirmBox.display("Deletion", "Pick a category to delete the product: ");

            if(Objects.equals(result, "Name")) {
                VBox finalVBox=new VBox();
                Label deletePName=new Label("Product Name: ");
                TextField deleteProductName=new TextField();
                Label deletionQuantity = new Label("Quantity to Delete: ");
                TextField quantityProductField=new TextField();
                Button finalDelBtn=new Button("Delete");
                Button closeBtn = new Button("Exit");
                closeBtn.setOnAction(e -> primaryStage.close());

                finalVBox.getChildren().addAll(deletePName,
                        deleteProductName,
                        deletionQuantity,
                        quantityProductField,
                        finalDelBtn,
                        closeBtn,
                        displayBtn);
                scene2=new Scene(finalVBox, 500, 400);
                primaryStage.setScene(scene2);
                finalDelBtn.setOnAction(event2 -> {
                    final List<Product> removalCandidates = new ArrayList<>();
                    int count =0;
                    for(Product p : ALL_PRODUCTS) {

                        if (p.getProductName().equals(deleteProductName.getText()))
                        {
                            System.out.println("Found a match to delete!");
                            if(p.getProductQuantity()-Integer.parseInt(quantityProductField.getText())==0) {
                                System.out.println("Quantity less than or equal to 0!");
                                removalCandidates.add(p);
                            }
                            else{
                                System.out.println("Quantity > 0!");

                                ALL_PRODUCTS.get(count).setProductQuantity(p.getProductQuantity()-
                                        Integer.parseInt(quantityProductField.getText()));
                            }
                        }
                        count++;
                    }
                    ALL_PRODUCTS.removeAll(removalCandidates);
                    System.out.println("Found product to delete");
                    AlertBox.display("Deletion", "Deleted product successfully!!");
                });

            }
        });

        // Exit Button
        Button exitBtn = new Button("Exit");
        exitBtn.setOnAction(e -> primaryStage.close());
        GridPane.setConstraints(exitBtn,1,8);

        root.getChildren().addAll(
                pName,
                nameField,
                pPrice,
                priceField,
                pQuantity,
                quantityField,
                pImage,
                imageView,
                imageAddress,
                openImage,
                pType,
                typeField,
                addBtn,
                deleteBtn,
                displayBtn,
                exitBtn
                );
        scene1=new Scene(root, 800, 500);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }
}
