package com.example.shapes;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


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
    @FXML
    private RadioButton redButton;
    @FXML
    private RadioButton greenButton;
    @FXML
    private RadioButton blueButton;
    @FXML
    private RadioButton squareButton;
    @FXML
    private RadioButton circleButton;
    @FXML
    private RadioButton triangleButton;



    Color color = Color.UNINITIALIZED;
    Shape shape = Shape.UNINITIALIZED;
    String[] content = new String[0];


    public void initialize() {
        shapes.getSelectionModel().selectedItemProperty().addListener((_, _, _) -> select_preview());

        if (!Files.exists(Path.of("alakzat.dat"))) {
            System.out.println("Nincs");
            return;
        }
        try {
            parse_file(Files.readString(Path.of("alakzat.dat")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        shapes.setItems(FXCollections.observableArrayList(content));
        shapes.getSelectionModel().select(0);
        select_preview();
    }


    private void parse_file(String file) {
        for (String part : file.split(";")) {
            add_to_content(part);
        }
    }


    @FXML
    protected void onRedButtonClick() {
        color = Color.RED;
        update_preview();
    }

    @FXML
    protected void onGreenButtonClick() {
        color = Color.GREEN;
        update_preview();
    }

    @FXML
    protected void onBlueButtonClick() {
        color = Color.BLUE;
        update_preview();
    }

    @FXML
    protected void onSquareButtonClick() {
        shape = Shape.SQUARE;
        update_preview();
    }

    @FXML
    protected void onCircleButtonClick() {
        shape = Shape.CIRCLE;
        update_preview();
    }

    @FXML
    protected void onTriangleButtonClick() {
        shape = Shape.TRIANGLE;
        update_preview();
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
        update_preview();
    }

    @FXML
    protected void onRemoveButtonClick() {
        int selected = shapes.getSelectionModel().getSelectedIndex();
        if (selected >= 0) remove_from_content(selected);
        shapes.setItems(FXCollections.observableArrayList(content));
        if (content.length > 0) shapes.getSelectionModel().select(
                Math.min(
                    selected < 0 ? content.length - 1 : selected,
                    content.length - 1
                )
        );
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


    private void select_preview() {
        int selected = shapes.getSelectionModel().getSelectedIndex();

        // Worst code I've ever written
        if (selected < 0) {
            color = Color.UNINITIALIZED;
            shape = Shape.UNINITIALIZED;
            redButton.setSelected(false);
            greenButton.setSelected(false);
            blueButton.setSelected(false);
            squareButton.setSelected(false);
            circleButton.setSelected(false);
            triangleButton.setSelected(false);

        }
        else {
            if (content[selected].contains("Piros")) color = Color.RED;
            else if (content[selected].contains("Zöld")) color = Color.GREEN;
            else if (content[selected].contains("Kék")) color = Color.BLUE;

            if (content[selected].contains("Négyzet")) shape = Shape.SQUARE;
            else if (content[selected].contains("Kör")) shape = Shape.CIRCLE;
            else if (content[selected].contains("Háromszög")) shape = Shape.TRIANGLE;
        }

        update_preview();
    }


    private void update_preview() {
        if (color == Color.UNINITIALIZED || shape == Shape.UNINITIALIZED) {
            bgcolorlabel.setStyle("");
            shapeImage.setImage(null);
            return;
        };

        bgcolorlabel.setStyle("-fx-background-color: #%s; -fx-border-color: black;".formatted(switch (color) {
            case RED -> "ff0000";
            case GREEN -> "00ff00";
            case BLUE -> "0000ff";
            case UNINITIALIZED -> null; // IF YOU CAN CLEARLY SEE THIS IS UNREACHABLE THEN WHY DO YOU GIVE ME A FUCKING ERROR THAT THE SWITCH DOESN'T COVER ALL POSSIBILITIES YOU FUCKER
        }));
        shapeImage.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("%s.png".formatted(switch (shape) {
            case SQUARE -> "negyzet";
            case CIRCLE -> "kor";
            case TRIANGLE -> "haromszog";
            case UNINITIALIZED -> null;
        })))));
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