/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itenglish.ui;

import itenglish.dao.FileUserDao;
import itenglish.dao.FileVocabularyDao;
import itenglish.domain.ItEnglishService;
import itenglish.domain.UserService;
import itenglish.domain.StatsService;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private UserService userService;
    private StatsService statsService;

    @Override
    public void init() throws Exception {
        Properties properties = new Properties();

        properties.load(new FileInputStream("config.properties"));

        HashMap<String, String> vocabularyFiles = new HashMap<>();
        vocabularyFiles.put("beginner", properties.getProperty("beginner"));
        vocabularyFiles.put("average", properties.getProperty("average"));
        vocabularyFiles.put("master", properties.getProperty("master"));
        FileVocabularyDao vocabularyDao = new FileVocabularyDao(vocabularyFiles);

        FileUserDao userdao = new FileUserDao(properties.getProperty("users"));

        statsService = new StatsService(userdao);
        itEnglishService = new ItEnglishService(vocabularyDao, statsService);
        userService = new UserService(userdao);

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
            String message = userService.logIn(userInput.getText(), passwordInput.getText());
            if (message.equals("Success")) {
                statsService.setLoggedUser(userInput.getText());
                chooseMode(primaryStage);
            } else {
                infoLabel.setText(message);
            }

        });
        createButton.setOnAction(e -> {
            newUser(primaryStage, new String(""));
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

    public void newUser(Stage primaryStage, String message) {
        Label infoLabel = new Label(message + "Luo uusi käyttäjätunnus."
                + "\nKäyttäjätunnuksen ja salasanan täytyy olla 3-10 "
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
            String errorMessage = userService.createUser(userInput.getText(), passwordInput.getText());
            if (errorMessage.equals("Success")) {
                login(primaryStage);
            } else {
                newUser(primaryStage, errorMessage);
            }

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
                + "\nenglanniksi (ilman artikkelia) tietotekniikan kontekstissa. "
                + "\nEsim. tietokone -> computer."
                + "\nKeskiverto- ja Mestari-vaikeustasot sisältävät myös englanninkielisten "
                + "\nlyhenteiden auki kirjoittamista englanniksi."
                + "\nEsim. IT -> information technology.");
        instructionsLabel.setFont(Font.font("Times New Roman", FontWeight.LIGHT, 12));

        RadioButton beginnerButton = new RadioButton("Aloittelija");
        RadioButton averageButton = new RadioButton("Keskiverto");
        RadioButton masterButton = new RadioButton("Mestari");
        RadioButton fiveButton = new RadioButton("5 satunnaista");
        RadioButton tenButton = new RadioButton("10 satunnaista");
        RadioButton allButton = new RadioButton("Kaikki");
        Button confirmButton = new Button("Valitse");
        Button logOutButton = new Button("Kirjaudu ulos");
        Button statsButton = new Button("Tarkastele tilastoja");

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
        HBox bottomRightLink = new HBox();
        HBox statsLink = new HBox();
        VBox rootPane = new VBox(20);

        rootPane.setAlignment(Pos.TOP_CENTER);
        rootPane.setPadding(new Insets(10));

        bottomRightLink.setAlignment(Pos.BOTTOM_RIGHT);
        bottomRightLink.getChildren().add(logOutButton);
        statsLink.setAlignment(Pos.BOTTOM_RIGHT);
        statsLink.getChildren().add(statsButton);

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
            RadioButton selectedDifficulty = (RadioButton) setDifficulty.getSelectedToggle();
            RadioButton selectedNumber = (RadioButton) setNumber.getSelectedToggle();
            itEnglishService.createNewSet(selectedDifficulty.getText());
            itEnglishService.setHowManyQuestionsByUser(selectedNumber.getText());
            String difficulty = selectedDifficulty.getText();
            String message = "Valitsit vaikeustason " + difficulty + ".";
            study(primaryStage, difficulty, message);

        });

        logOutButton.setOnAction(e -> {
            login(primaryStage);

        });

        statsButton.setOnAction(e -> {
            stats(primaryStage);

        });

        rootPane.getChildren().addAll(infoLabel, instructionsLabel, layout, statsLink, bottomRightLink);
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
        answerButton.setDefaultButton(true);
        Button backButton = new Button("Lopeta");

        GridPane layout = new GridPane();
        HBox bottomRightLink = new HBox();
        VBox rootPane = new VBox(30);

        rootPane.setAlignment(Pos.TOP_CENTER);
        rootPane.setPadding(new Insets(10));

        bottomRightLink.setAlignment(Pos.BOTTOM_RIGHT);
        bottomRightLink.getChildren().add(backButton);

        layout.add(wordLabel, 0, 0);
        layout.add(userInput, 1, 0);
        layout.add(answerButton, 1, 1);

        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        layout.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));

        answerButton.setOnAction(e -> {
            String feedbackForAnswer = itEnglishService.checkUserInput(userInput.getText(), wordLabel.getText(), difficulty);
            itEnglishService.questionAnswered();
            if (itEnglishService.getHowManyQuestions() == 0) {
                String feedbackForScore = statsService.feedback(difficulty);
                feedback(primaryStage, feedbackForAnswer, feedbackForScore);
            } else {
                study(primaryStage, difficulty, feedbackForAnswer);
            }

        });

        backButton.setOnAction(e -> {
            itEnglishService.setHowManyQuestions(0);
            chooseMode(primaryStage);

        });

        rootPane.getChildren().addAll(infoLabel, layout, bottomRightLink);
        rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));

        Scene studyScene = new Scene(rootPane, 450, 350);

        primaryStage.setTitle("Harjoitus");
        primaryStage.setScene(studyScene);

    }
    
    public void feedback(Stage primaryStage, String message, String feedback) {
        Label wordLabel = new Label(feedback);
        Label infoLabel = new Label(message);
        
        infoLabel.setFont(Font.font("Tahoma", FontWeight.LIGHT, 14));
        wordLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));

        Button backButton = new Button("Takaisin");
        backButton.setDefaultButton(true);
        Button statsButton = new Button("Tilastot");

        GridPane layout = new GridPane();
        HBox bottomRightLink = new HBox();
        VBox rootPane = new VBox(30);

        rootPane.setAlignment(Pos.TOP_CENTER);
        rootPane.setPadding(new Insets(10));

        bottomRightLink.setAlignment(Pos.BOTTOM_RIGHT);
        bottomRightLink.getChildren().add(statsButton);

        layout.add(wordLabel, 0, 0);
        layout.add(backButton, 1, 1);

        layout.setVgap(10);
        layout.setHgap(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        layout.setBackground(new Background(new BackgroundFill(Color.DARKGREY, null, null)));

        backButton.setOnAction(e -> {
            chooseMode(primaryStage);

        });

        statsButton.setOnAction(e -> {
            stats(primaryStage);

        });

        rootPane.getChildren().addAll(infoLabel, layout, bottomRightLink);
        rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));

        Scene studyScene = new Scene(rootPane, 500, 250);

        primaryStage.setTitle("Palaute");
        primaryStage.setScene(studyScene);

   
        
    }

    public void stats(Stage primaryStage) {
        Label infoLabel = new Label("Ennätyksesi");
        infoLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 16));

        Label beginnerLabel = new Label("Aloittelija");
        Label averageLabel = new Label("Keskiverto");
        Label masterLabel = new Label("Mestari");
        Label beginnerRecordLabel = new Label(String.valueOf(statsService.getBeginnerRecord()));
        Label averageRecordLabel = new Label(String.valueOf(statsService.getAverageRecord()));
        Label masterRecordLabel = new Label(String.valueOf(statsService.getMasterRecord()));

        Button chooseButton = new Button("Takaisin");

        HBox bottomLink = new HBox();
        VBox rootPane = new VBox(30);

        rootPane.setAlignment(Pos.CENTER);
        rootPane.setPadding(new Insets(10));

        bottomLink.setAlignment(Pos.BOTTOM_RIGHT);
        bottomLink.getChildren().add(chooseButton);

        CheckBox checkBoxBeginner = new CheckBox("I survived beginner ItEnglish!");
        checkBoxBeginner.setDisable(true);
        if (statsService.getBeginnerRecord() == itEnglishService.totalWords("beginner")) {
            checkBoxBeginner.setSelected(true);
            checkBoxBeginner.setFont((Font.font("Times New Roman", FontWeight.BOLD, 12)));
        }
        checkBoxBeginner.setStyle("-fx-opacity: 1");
        CheckBox checkBoxAverage = new CheckBox("I survived average ItEnglish!");
        checkBoxAverage.setDisable(true);
        if (statsService.getAverageRecord() == itEnglishService.totalWords("average")) {
            checkBoxAverage.setSelected(true);
            checkBoxAverage.setFont((Font.font("Times New Roman", FontWeight.BOLD, 12)));
        }
        checkBoxAverage.setStyle("-fx-opacity: 1");
        CheckBox checkBoxMaster = new CheckBox("I survived master ItEnglish!");
        checkBoxMaster.setDisable(true);
        if (statsService.getMasterRecord() == itEnglishService.totalWords("master")) {
            checkBoxMaster.setSelected(true);
            checkBoxMaster.setFont((Font.font("Times New Roman", FontWeight.BOLD, 12)));
        }
        checkBoxMaster.setStyle("-fx-opacity: 1");

        TableView<String> table = new TableView<String>();

        TableColumn<String, String> beginnerColumn = new TableColumn("Aloittelija");
        beginnerColumn.setStyle("-fx-alignment: CENTER;");
        beginnerColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(statsService.getBeginnerRecord()) + " (" + itEnglishService.totalWords("beginner") + ")"));
        TableColumn<String, String> averageColumn = new TableColumn("Keskiverto");
        averageColumn.setStyle("-fx-alignment: CENTER;");
        averageColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(statsService.getAverageRecord()) + " (" + itEnglishService.totalWords("average") + ")"));
        TableColumn<String, String> masterColumn = new TableColumn("Mestari");
        masterColumn.setStyle("-fx-alignment: CENTER;");
        masterColumn.setCellValueFactory(data -> new ReadOnlyStringWrapper(String.valueOf(statsService.getMasterRecord()) + " (" + itEnglishService.totalWords("master") + ")"));


        ObservableList<String> list = FXCollections.observableArrayList("");
        table.setItems(list);

        table.getColumns().addAll(beginnerColumn, averageColumn, masterColumn);

        table.setMaxSize(244, 52);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(20, 20, 20, 20));
        vbox.getChildren().addAll(table, checkBoxBeginner, checkBoxAverage, checkBoxMaster);
        vbox.setAlignment(Pos.TOP_CENTER);

        chooseButton.setOnAction(e -> {
            chooseMode(primaryStage);
        });

        rootPane.getChildren().addAll(infoLabel, vbox, bottomLink);
        rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, null, null)));

        Scene statsScene = new Scene(rootPane, 400, 350);

        primaryStage.setTitle("Tilastot");
        primaryStage.setScene(statsScene);
        primaryStage.show();

    }

    @Override
    public void stop() {

    }
}
