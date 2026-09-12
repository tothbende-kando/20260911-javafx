package com.example.shapes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ShapeApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShapeApplication.class.getResource("hello-view.fxml"));
        Scene scene = null;
        if (!ApplicationTest.isRunningTest) scene = new Scene(fxmlLoader.load(), 960, 540);
        if (!ApplicationTest.isRunningTest) stage.setTitle("Alakzatok");
        if (!ApplicationTest.isRunningTest) stage.getIcons().add(new Image("file:negyzet.png"));
        if (!ApplicationTest.isRunningTest) stage.setScene(scene);
        if (!ApplicationTest.isRunningTest) stage.show();
    }

    public static void main(String[] args) {
        if (!ApplicationTest.isRunningTest) launch();
    }
}