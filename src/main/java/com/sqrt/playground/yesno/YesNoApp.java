package com.sqrt.playground.yesno;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class YesNoApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        final Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("error.png"));
        final ImageView view = new ImageView(image);
        view.setFitHeight(50);
        view.setFitWidth(50);

        final Label label = new Label();
        label.setGraphic(view);
        label.setText("An error occurred while starting the application. This is most probably related to you having a small brain." + System.lineSeparator() + System.lineSeparator() + "Do you have a small brain?");
        label.setWrapText(true);
        label.setGraphicTextGap(25);
        label.setMaxWidth(400);

        final Button yes = new Button("Yes");
        yes.setPrefWidth(100);
        yes.setOnAction(event -> {
            final Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle(null);
            alert.setContentText("Thought so, stupid!");
            alert.setOnCloseRequest(dialogEvent -> Platform.exit());
            alert.showAndWait();
        });

        final Button no = new Button("No");
        no.setOnAction(event -> Platform.exit());
        no.setPrefWidth(100);
        no.setFocusTraversable(false);

        final List<Node> buttons = Arrays.asList(yes, no);

        final HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);
        hbox.getChildren().addAll(buttons);

        final VBox vbox = new VBox(label, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);

        no.setOnMouseEntered(mouseEvent -> {
            hbox.getChildren().clear();
            Collections.swap(buttons, 0, 1);
            hbox.getChildren().addAll(buttons);
        });

        final Scene scene = new Scene(vbox, 500, 175);
        stage.setScene(scene);
        stage.setTitle("Error");
        stage.show();
    }
}
