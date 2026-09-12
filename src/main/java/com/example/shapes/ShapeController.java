package com.example.shapes;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;


enum Color {
    RED,
    GREEN,
    BLUE,
    UNINITIALIZED,
}

enum Shape {
    SQUARE,
    CIRCLE,
    TRIANGLE,
    UNINITIALIZED,
}


public class ShapeController {
    @FXML
    private Label bgcolorlabel;
    @FXML
    private ImageView shapeImage;
    @FXML
    private ListView<String> shapes;


    Color color = Color.UNINITIALIZED;
    Shape shape = Shape.UNINITIALIZED;
    String[] content = new String[0];


    @FXML
    protected void onRedButtonClick() {
        bgcolorlabel.setStyle("-fx-background-color: #ff0000; -fx-border-color: black;");
        color = Color.RED;
    }

    @FXML
    protected void onGreenButtonClick() {
        bgcolorlabel.setStyle("-fx-background-color: #00ff00; -fx-border-color: black;");
        color = Color.GREEN;

    }

    @FXML
    protected void onBlueButtonClick() {
        bgcolorlabel.setStyle("-fx-background-color: #0000ff; -fx-border-color: black;");
        color = Color.BLUE;

    }

    @FXML
    protected void onSquareButtonClick() {
        shapeImage.setImage(new Image(getClass().getResourceAsStream("negyzet.png")));
        shape = Shape.SQUARE;
    }

    @FXML
    protected void onCircleButtonClick() {
        shapeImage.setImage(new Image(getClass().getResourceAsStream("kor.png")));
        shape = Shape.CIRCLE;

    }

    @FXML
    protected void onTriangleButtonClick() {
        shapeImage.setImage(new Image(getClass().getResourceAsStream("haromszog.png")));
        shape = Shape.TRIANGLE;

    }

    @FXML
    protected void onAddButtonClick() {
        String new_content = switch (color) {
            case RED -> "Piros";
            case GREEN -> "Zöld";
            case BLUE -> "Kék";
            case UNINITIALIZED -> "semmilyen";
        } + ", " + switch (shape) {
            case SQUARE -> "Négyzet";
            case CIRCLE -> "Kör";
            case TRIANGLE -> "Háromszög";
            case UNINITIALIZED -> "semmilyen";
        };

        if (new_content.contains("semmilyen")) return;

        add_to_content(new_content);
        shapes.setItems(FXCollections.observableArrayList(content));
        shapes.getSelectionModel().select(content.length - 1);
    }

    @FXML
    protected void onRemoveButtonClick() {
        remove_from_content(shapes.getSelectionModel().getSelectedIndex());
        shapes.setItems(FXCollections.observableArrayList(content));
    }

    @FXML
    protected void onSaveButtonClick() {
        try {
            FileWriter writer = new FileWriter("alakzat.dat");
            writer.write("");

            for (String line : content) {
                writer.append(line);
                writer.append(";");
            }

            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void add_to_content(String new_line) {
        ArrayList<String> temp = new ArrayList<>(Arrays.asList(content));
        temp.add(new_line);
        content = temp.toArray(content);
    }


    private void remove_from_content(int index) {
        ArrayList<String> temp = new ArrayList<>(Arrays.asList(content));
        temp.remove(index);
        content = new String[0];
        content = temp.toArray(content);
    }
}