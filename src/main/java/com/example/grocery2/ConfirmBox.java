package com.example.grocery2;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {

    //Create variable
    static String answer;

    public static String display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);

        //Create two buttons
        Button nameBtn = new Button("Product Name");
        Button typeBtn = new Button("Product Type");
        Button pictureBtn = new Button("Product Price");

        //Clicking will set answer and close window
        nameBtn.setOnAction(e -> {
            answer = "Name";
            window.close();
        });
        typeBtn.setOnAction(e -> {
            answer = "Type";
            window.close();
        });
        pictureBtn.setOnAction(e -> {
            answer = "Price";
            window.close();
        });
        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(label, nameBtn, typeBtn,pictureBtn);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return answer;
    }

}
