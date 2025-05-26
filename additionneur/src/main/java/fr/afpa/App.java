package fr.afpa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Additionneur");

        TextArea textArea = new TextArea();
        textArea.setPrefWidth(300);
        textArea.setPrefHeight(150);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(textArea);
        scrollPane.setPrefWidth(300);
        scrollPane.setPrefHeight(150);

        // TODO serait-il possible d'imaginer une solution avec une boucle ?
        GridPane gridNumbers = new GridPane();
        Button button0 = new Button("0");
        Button button1 = new Button("1");
        Button button2 = new Button("2");
        Button button3 = new Button("3");
        Button button4 = new Button("4");
        Button button5 = new Button("5");
        Button button6 = new Button("6");
        Button button7 = new Button("7");
        Button button8 = new Button("8");
        Button button9 = new Button("9");

        // TODO idem remarque 36
        gridNumbers.add(button0, 0, 0);
        gridNumbers.add(button1, 1, 0);
        gridNumbers.add(button2, 2, 0);
        gridNumbers.add(button3, 3, 0);
        gridNumbers.add(button4, 4, 0);
        gridNumbers.add(button5, 0, 1);
        gridNumbers.add(button6, 1, 1);
        gridNumbers.add(button7, 2, 1);
        gridNumbers.add(button8, 3, 1);
        gridNumbers.add(button9, 4, 1);

        gridNumbers.setHgap(20);
        gridNumbers.setVgap(10);
        gridNumbers.setAlignment(Pos.CENTER);

        EventHandler<ActionEvent> numberButtonHandler = event -> {
            Button source = (Button) event.getSource();
            textArea.appendText(source.getText());
        };

        // TODO idem boucle
        button0.setOnAction(numberButtonHandler);
        button1.setOnAction(numberButtonHandler);
        button2.setOnAction(numberButtonHandler);
        button3.setOnAction(numberButtonHandler);
        button4.setOnAction(numberButtonHandler);
        button5.setOnAction(numberButtonHandler);
        button6.setOnAction(numberButtonHandler);
        button7.setOnAction(numberButtonHandler);
        button8.setOnAction(numberButtonHandler);
        button9.setOnAction(numberButtonHandler);

        Button clearButton = new Button("Vider");
        Button calculateButton = new Button("Calculer");
        clearButton.setPrefWidth(130);
        calculateButton.setPrefWidth(130);

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.clear();
            }
        });

        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String calculString = textArea.getText();
                    // TODO pour optimisation, serait-il possible d'imaginer une solution basée sur un attribut qui serait mis-à jour au fur et à mesure des saisies utilisateur plutôt qu'une analyse de la chaîne de caractère?
                    String[] numberParts = calculString.split("\\+");
                    int sum = 0;
                    for (String numberPart : numberParts) {
                        sum += Integer.parseInt(numberPart.trim());
                    }
                    textArea.appendText(" = " + sum);
                } catch (NumberFormatException e) {
                    textArea.appendText("Erreur dans le calcul.");
                }
            }
        });

        HBox hbox = new HBox(clearButton, calculateButton);
        hbox.setSpacing(30);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(scrollPane, gridNumbers, hbox);
        vbox.setSpacing(15);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));

        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ADD || event.getCode() == KeyCode.PLUS) {
                    textArea.appendText("+");
                }
            }
        });

        stage.setScene(scene);
        stage.show();
        textArea.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }

}