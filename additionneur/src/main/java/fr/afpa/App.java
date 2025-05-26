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
    private int currentSum = 0;
    private int currentInput = 0;
    private boolean isOperator = false;

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

        EventHandler<ActionEvent> numberButtonHandler = event -> {
            Button source = (Button) event.getSource();
            String numberString = source.getText();
            textArea.appendText(numberString);
            if (isOperator) {
                currentInput = 0;
                isOperator = false;
            }
            currentInput = currentInput * 10 + Integer.parseInt(numberString);
        };

        GridPane gridNumbers = new GridPane();
        Button[] buttons = new Button[10];
        for (int i = 0; i <= 9; i++) {
            buttons[i] = new Button(String.valueOf(i));
            int row = i / 5;
            int col = i % 5;
            gridNumbers.add(buttons[i], col, row);
            buttons[i].setOnAction(numberButtonHandler);
        }

        gridNumbers.setHgap(20);
        gridNumbers.setVgap(10);
        gridNumbers.setAlignment(Pos.CENTER);

        Button clearButton = new Button("Vider");
        Button calculateButton = new Button("Calculer");
        clearButton.setPrefWidth(130);
        calculateButton.setPrefWidth(130);

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                textArea.clear();
                currentSum = 0;
                currentInput = 0;
                isOperator = false;
            }
        });

        calculateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    currentSum += currentInput;
                    textArea.appendText(" = " + currentSum);
                    currentInput = 0;
                    currentSum = 0;
                    isOperator = false;
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
                    currentSum += currentInput;
                    currentInput = 0;
                    isOperator = true;
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