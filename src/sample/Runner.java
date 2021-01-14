package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Runner extends Application implements EventHandler<ActionEvent> {

    int SIZE = 800;

    @Override
    public void start(Stage window) throws Exception {
        window.setTitle("Othello by Nicholas Lee");


        Scene scene1 = new Scene(OthelloGUI.createStartScreen(window),SIZE,SIZE);

        window.setScene(scene1);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void handle(ActionEvent actionEvent) {

    }
}