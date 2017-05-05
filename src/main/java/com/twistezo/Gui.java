package com.twistezo;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author twistezo (13.03.2017)
 */
public class Gui extends Application {
    private double xOffset = 0;
    private double yOffset = 0;
    private Boolean resizeBottom = false;
    private double dx;
    private double dy;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/scene.fxml"));
        Scene scene = new Scene(root);
//        stage.setAlwaysOnTop(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setTitle("learning-java");
        stage.show();

        /*
        Moving undecorated scene
         */
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

        /*
        Resizing undecorated scene by bottom right corner
         */
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                if (event.getX() > stage.getWidth() - 10
                        && event.getX() < stage.getWidth() + 10
                        && event.getY() > stage.getHeight() - 10
                        && event.getY() < stage.getHeight() + 10) {
                    resizeBottom = true;
//                    scene.setCursor(Cursor.HAND);
                    dx = stage.getWidth() - event.getX();
                    dy = stage.getHeight() - event.getY();
                } else {
                    resizeBottom = false;
                    xOffset = event.getSceneX();
                    yOffset = event.getSceneY();
                }
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (!resizeBottom) {
                    stage.setX(event.getScreenX() - xOffset);
                    stage.setY(event.getScreenY() - yOffset);
                } else {
                    stage.setWidth(event.getX() + dx);
                    stage.setHeight(event.getY() + dy);
                }
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
