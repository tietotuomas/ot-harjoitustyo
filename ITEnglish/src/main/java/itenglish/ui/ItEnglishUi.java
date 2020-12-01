/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itenglish.ui;

import itenglish.dao.FileVocabularyDao;
import itenglish.domain.ItEnglishService;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author aaltonet
 */
public class ItEnglishUi extends Application {

    private ItEnglishService itEnglishService;

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));

        HashMap<String, String> files = new HashMap<>();
        files.put("beginner", properties.getProperty("beginner"));
        files.put("average", properties.getProperty("average"));
        files.put("master", properties.getProperty("master"));
        FileVocabularyDao vocabularyDao = new FileVocabularyDao(files);

        itEnglishService = new ItEnglishService(vocabularyDao);

    }

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        login(primaryStage);
    }

    public void login(Stage primaryStage) {
        Label infoLabel = new Label("Tervetuloa oppimaan IT-sanastoa englanniksi!"
                + "\nKirjaudu ensiksi sisään tai luo uusi tunnus.");
        infoLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        Label userLabel = new Label("Käyttäjätunnus");
        Label passwordLabel = new Label("Salasana");
        TextField userInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        Button loginButton = new Button("Kirjaudu sisään");
        Button createButton = new Button("Luo uusi tunnus");
        Button exitButton = new Button("Sulje sovellus");
//       värejä? loginButton.setBackground(new Background(new BackgroundFill(Color.DEEPPINK, null, null)));

        GridPane layout = new GridPane();
        HBox bottomLink = new HBox();
        VBox rootPane = new VBox(30);

        rootPane.setAlignment(Pos.TOP_CENTER);
        rootPane.setPadding(new Insets(10));

        bottomLink.setAlignment(Pos.BOTTOM_RIGHT);
        bottomLink.getChildren().add(exitButton);

        layout.add(userLabel, 0, 0);
        layout.add(passwordLabel, 0, 1);
        layout.add(userInput, 1, 0);
        layout.add(passwordInput, 1, 1);
        layout.add(loginButton, 1, 2);
        layout.add(createButton, 0, 2);

        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        layout.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));

        loginButton.setOnAction(e -> {
            chooseMode(primaryStage);
        });
        createButton.setOnAction(e -> {
            newUser(primaryStage);
        });
        exitButton.setOnAction(e -> {
            primaryStage.close();
        });

        rootPane.getChildren().addAll(infoLabel, layout, bottomLink);
        rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));

        Scene loginScene = new Scene(rootPane, 500, 350);

        primaryStage.setTitle("Kirjaudu");
        primaryStage.setScene(loginScene);
        primaryStage.show();

    }

    public void newUser(Stage primaryStage) {
        Label infoLabel = new Label("Luo uusi käyttäjätunnus."
                + "\nKäyttäjätunnuksen ja salasanan kuuluu olla 3-10 "
                + "\nmerkkiä pitkä.");
        infoLabel.setFont(Font.font("Tahoma", FontWeight.LIGHT, 14));
        Label userLabel = new Label("Käyttäjätunnus");
        Label passwordLabel = new Label("Salasana");
        TextField userInput = new TextField();
        PasswordField passwordInput = new PasswordField();
        Button createButton = new Button("Luo uusi tunnus");
        Button backButton = new Button("Takaisin");

        GridPane layout = new GridPane();
        HBox bottomLink = new HBox();
        VBox rootPane = new VBox(30);

        rootPane.setAlignment(Pos.TOP_CENTER);
        rootPane.setPadding(new Insets(10));

        bottomLink.setAlignment(Pos.BOTTOM_RIGHT);
        bottomLink.getChildren().add(backButton);

        layout.add(userLabel, 0, 0);
        layout.add(passwordLabel, 0, 1);
        layout.add(userInput, 1, 0);
        layout.add(passwordInput, 1, 1);
        layout.add(createButton, 1, 2);

        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        layout.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));

        createButton.setOnAction(e -> {
            login(primaryStage);
        });

        backButton.setOnAction(e -> {
            login(primaryStage);
        });

        rootPane.getChildren().addAll(infoLabel, layout, bottomLink);
        rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));

        Scene loginScene = new Scene(rootPane, 450, 350);

        primaryStage.setTitle("Käyttäjätili");
        primaryStage.setScene(loginScene);

    }

    public void chooseMode(Stage primaryStage) {
        Label infoLabel = new Label("Valitse vaikeustaso:");
        infoLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        Label instructionsLabel = new Label("Aloittelijan vaikeustaso sisältää suomenkielisten sanojen kääntämistä"
                + "\nsuomesta englanniksi tietotekniikan kontekstissa. "
                + "\nEsim. tietokone -> computer."
                + "\nKeskiverto- ja Mestari-vaikeustasot sisältävät myös englanninkielisten "
                + "\nlyhenteiden auki kirjoittamista englanniksi."
                + "\nEsim. IT -> information technology.");
//                + "\nVoit vastata ilman artikkeleita (ohjelma ei tarkista niiden oikeellisuutta). "
//                + "\nMyöskään kirjainkoolla ei ole väliä.");
        instructionsLabel.setFont(Font.font("Times New Roman", FontWeight.LIGHT, 12));

        RadioButton beginnerButton = new RadioButton("Aloittelija");
        RadioButton averageButton = new RadioButton("Keskiverto");
        RadioButton masterButton = new RadioButton("Mestari");
        RadioButton fiveButton = new RadioButton("5 satunnaista");
        RadioButton tenButton = new RadioButton("10 satunnaista");
        RadioButton allButton = new RadioButton("Kaikki");
        Button confirmButton = new Button("Valitse");
        Button logOutButton = new Button("Kirjaudu ulos");

        ToggleGroup setDifficulty = new ToggleGroup();

        beginnerButton.setToggleGroup(setDifficulty);
        averageButton.setToggleGroup(setDifficulty);
        masterButton.setToggleGroup(setDifficulty);
        beginnerButton.setSelected(true);

        beginnerButton.setToggleGroup(setDifficulty);
        averageButton.setToggleGroup(setDifficulty);
        masterButton.setToggleGroup(setDifficulty);
        beginnerButton.setSelected(true);

        ToggleGroup setNumber = new ToggleGroup();

        fiveButton.setToggleGroup(setNumber);
        tenButton.setToggleGroup(setNumber);
        allButton.setToggleGroup(setNumber);
        fiveButton.setSelected(true);

        GridPane layout = new GridPane();
        HBox bottomLink = new HBox();
        VBox rootPane = new VBox(20);

        rootPane.setAlignment(Pos.TOP_CENTER);
        rootPane.setPadding(new Insets(10));

        bottomLink.setAlignment(Pos.BOTTOM_RIGHT);
        bottomLink.getChildren().add(logOutButton);

        layout.add(beginnerButton, 0, 0);
        layout.add(averageButton, 1, 0);
        layout.add(masterButton, 2, 0);
        layout.add(fiveButton, 0, 2);
        layout.add(tenButton, 1, 2);
        layout.add(allButton, 2, 2);
        layout.add(confirmButton, 2, 4);
        

        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        layout.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));

        confirmButton.setOnAction(e -> {
            RadioButton selectedRadioButton = (RadioButton) setDifficulty.getSelectedToggle();
            String difficulty = selectedRadioButton.getText();
            String message = "Valitsit vaikeustason " + difficulty + ".";
            study(primaryStage, difficulty, message);

        });

        logOutButton.setOnAction(e -> {
            login(primaryStage);

        });

        rootPane.getChildren().addAll(infoLabel, instructionsLabel, layout, bottomLink);
        rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));

        Scene chooseModeScene = new Scene(rootPane, 500, 400);

        primaryStage.setTitle("Vaikeustaso");
        primaryStage.setScene(chooseModeScene);

    }

    public void study(Stage primaryStage, String difficulty, String message) {
        Label wordLabel = new Label(itEnglishService.randomWord(difficulty));
        Label infoLabel = new Label(message + "\n" + itEnglishService.infoText(wordLabel.getText()));
        infoLabel.setFont(Font.font("Tahoma", FontWeight.LIGHT, 14));

        wordLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));

        TextField userInput = new TextField();

        Button answerButton = new Button("Lukitse vastaus");
        Button backButton = new Button("Lopeta");

        GridPane layout = new GridPane();
        HBox bottomLink = new HBox();
        VBox rootPane = new VBox(30);

        rootPane.setAlignment(Pos.TOP_CENTER);
        rootPane.setPadding(new Insets(10));

        bottomLink.setAlignment(Pos.BOTTOM_RIGHT);
        bottomLink.getChildren().add(backButton);

        layout.add(wordLabel, 0, 0);
        layout.add(userInput, 1, 0);
        layout.add(answerButton, 1, 1);

        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        layout.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));

        answerButton.setOnAction(e -> {
            String feedback = itEnglishService.checkUserInput(userInput.getText(), wordLabel.getText(), difficulty);
            study(primaryStage, difficulty, feedback);

        });

        backButton.setOnAction(e -> {
            chooseMode(primaryStage);

        });

        rootPane.getChildren().addAll(infoLabel, layout, bottomLink);
        rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));

        Scene studyScene = new Scene(rootPane, 450, 350);

        primaryStage.setTitle("Harjoitus");
        primaryStage.setScene(studyScene);

    }

    public void feedback(Stage primaryStage) {
        //näytä palaute tehdystä harjoituksesta

    }

    @Override
    public void stop() {

    }
}
