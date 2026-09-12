package com.example.shapes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ShapeController {
    @FXML
    private Label bgcolorlabel;

    @FXML
    protected void onRedButtonClick() {
        bgcolorlabel.setStyle("-fx-background-color: #ff0000; -fx-border-color: black;");;
    }

    @FXML
    protected void onGreenButtonClick() {
        bgcolorlabel.setStyle("-fx-background-color: #00ff00; -fx-border-color: black;");;
    }

    @FXML
    protected void onBlueButtonClick() {
        bgcolorlabel.setStyle("-fx-background-color: #0000ff; -fx-border-color: black;");;
    }
}