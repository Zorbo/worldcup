package com.worldcup.demo.worldcup;

import com.worldcup.demo.worldcup.entiy.Husband;
import com.worldcup.demo.worldcup.entiy.Wife;
import com.worldcup.demo.worldcup.service.Couples;
import com.worldcup.demo.worldcup.service.Cup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author tamas.kiss
 */
@SpringBootApplication
public class WorldCupApplication extends Application {

    private static final String DATA = "C:\\Training\\worldcup\\src\\main\\resources\\data.txt";
    private static final Logger logger = LoggerFactory.getLogger(WorldCupApplication.class);
    private static final String REGEX_SPLIT_COMMA = "\\s*,\\s*";
    private ConfigurableApplicationContext springContext;
    private Cup cup;
    private ObservableList<String> husbandData;
    private ObservableList<String> wifeData;
    private TextField teams = new TextField("");
    private TextField beerSum = new TextField("Atlagosan: " + 0 + " sor.");
    private TextField totalFreeTime = new TextField("Osszesen: " + 0 + " perc.");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        springContext = SpringApplication.run(WorldCupApplication.class);
    }

    /**
     * Start the application and init functions
     *
     * @param primaryStage the primary stage of the application
     */
    @Override
    public void start(Stage primaryStage) {

        // Create resources on startup
        primaryStage.setTitle("World Cup");
        Couples couples = new Couples(DATA);
        Pane root = new Pane();
        Scene scene = new Scene(root, 200, 100);

        // Create couple view
        Label coupleViewLabel = new Label("Hazasparok");
        ObservableList<String> coupleData = FXCollections.observableArrayList(createCoupleView(couples));
        TableView<String> coupleView = new TableView<>();
        TableColumn<String, String> col1 = new TableColumn<>();
        col1.setMinWidth(300);
        coupleView.getColumns().add(col1);
        col1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        coupleView.setItems(coupleData);
        StackPane coupleSp = new StackPane(coupleView);
        coupleSp.getChildren().add(coupleViewLabel);
        coupleSp.setAlignment(Pos.TOP_CENTER);
        coupleSp.setPrefSize(200, 400);
        coupleSp.setLayoutX(50);
        coupleSp.setLayoutY(100);
        logger.info("Couple table created");

        // Create Husband view
        Label husbandLabel = new Label("Ferjek");
        husbandData = FXCollections.observableArrayList(createHusbandView(couples));
        TableView<String> husbandView = new TableView<>();
        TableColumn<String, String> husbandColumn = new TableColumn<>();
        husbandColumn.setMinWidth(300);
        husbandView.getColumns().add(husbandColumn);
        husbandColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        husbandView.setItems(husbandData);
        StackPane husbandSp = new StackPane(husbandView);
        husbandSp.getChildren().add(husbandLabel);
        husbandSp.setAlignment(Pos.TOP_CENTER);
        husbandSp.setPrefSize(200, 400);
        husbandSp.setLayoutX(400);
        husbandSp.setLayoutY(100);
        logger.info("Husband table created");

        //Create Wife view
        Label wifeLabel = new Label("Felesegek");
        wifeData = FXCollections.observableArrayList(createWifeView(couples));
        TableView<String> wifeView = new TableView<>();
        TableColumn<String, String> wifeColumn = new TableColumn<>();
        wifeColumn.setMinWidth(300);
        wifeView.getColumns().add(wifeColumn);
        wifeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        wifeView.setItems(wifeData);
        StackPane wifeSp = new StackPane(wifeView);
        wifeSp.getChildren().add(wifeLabel);
        wifeSp.setAlignment(Pos.TOP_CENTER);
        wifeSp.setPrefSize(200, 400);
        wifeSp.setLayoutX(750);
        wifeSp.setLayoutY(100);
        logger.info("Wife table created");

        //Create the match button and action functions
        Button matchButton = new Button("Meccs!");
        matchButton.setLayoutX(480);
        matchButton.setLayoutY(650);
        matchButton.setOnAction(event -> {
            this.cup = new Cup(DATA);
            setTeamsText(cup);
            watchMatch(couples, cup);
            setAverageBeersText(couples);
            setTotalFreeTimeText(couples);
            husbandData.clear();
            husbandData = FXCollections.observableArrayList(createHusbandView(couples));
            updateHusband(husbandView, husbandColumn);
            wifeData.clear();
            wifeData = FXCollections.observableArrayList(createWifeView(couples));
            updateWife(wifeView, wifeColumn);
            logger.info("Button event triggered");
        });

        // set text views
        teams.setLayoutX(350);
        teams.setLayoutY(10);
        teams.setMinWidth(300);
        teams.setAlignment(Pos.CENTER);
        beerSum.setLayoutX(400);
        beerSum.setLayoutY(510);
        beerSum.setAlignment(Pos.CENTER);
        totalFreeTime.setLayoutX(750);
        totalFreeTime.setLayoutY(510);
        totalFreeTime.setAlignment(Pos.CENTER);
        logger.info("TextViews initialized");

        // Init Desktop view
        root.getChildren().addAll(coupleSp, husbandSp, wifeSp, matchButton, teams, beerSum, totalFreeTime);
        primaryStage.setScene(scene);
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);
        primaryStage.show();
        logger.info("Primary stage shown");
    }

    /**
     * Random select couples and call watchCup() method also check the watched matches
     *
     * @param couples the couples
     * @param cup the specific Cup
     */
    private void watchMatch(Couples couples, Cup cup) {
        for (Map.Entry<Husband, Wife> entry : couples.getCouples().entrySet()) {
            boolean watchMatch = new Random().nextBoolean();
            if (watchMatch) {
                String watchedCup = cup.getTeam1().getName() + cup.getTeam2().getName();
                if (entry.getKey().getWatchedCups().stream().noneMatch(c -> c.equals(watchedCup))) {
                    entry.getKey().watchCup(cup);
                    logger.info(entry.getKey().getName() + " watched: " + cup.getTeam1().getName()
                                    + " : " + cup.getTeam2().getName());
                    entry.getValue().watchCup(cup);
                    logger.info(entry.getValue().getName() + " watched: " + cup.getTeam1().getName()
                                    + " : " + cup.getTeam2().getName());
                } else {
                    logger.info(entry.getKey().getName() + " & " + entry.getValue().getName()
                                    + " already watched: " + cup.getTeam1().getName()
                                    + " : " + cup.getTeam2().getName());
                }
            }
        }
    }

    /**
     * Create couple table view string list
     *
     * @param couples the couples
     * @return Initialized couple view string list
     */
    private List<String> createCoupleView(Couples couples) {
        StringBuilder stringBuilder = new StringBuilder();
        couples.getCouples().forEach((key, value) -> stringBuilder.append(key.getName())
                                                                  .append("-")
                                                                  .append(value.getName())
                                                                  .append(","));
        return Arrays.asList(stringBuilder.toString().split(REGEX_SPLIT_COMMA));
    }

    /**
     * Create husband table view string list
     *
     * @param couples the couples
     * @return Initialized husband view string list
     */
    private List<String> createHusbandView(Couples couples) {
        StringBuilder stringBuilder = new StringBuilder();
        couples.getCouples().keySet().forEach(k -> stringBuilder.append(k.getName())
                                                                .append(" - ")
                                                                .append(k.getBeers())
                                                                .append(" sort ivott.,"));
        return Arrays.asList(stringBuilder.toString().split(REGEX_SPLIT_COMMA));
    }

    /**
     * Update husband Table
     *
     * @param husbandView the husband view
     * @param husbandColumn the husband column
     */
    private void updateHusband(TableView<String> husbandView, TableColumn<String, String> husbandColumn) {
        husbandView.getColumns().clear();
        husbandView.getColumns().add(husbandColumn);
        husbandColumn.setMinWidth(300);
        husbandColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        husbandView.setItems(husbandData);
        husbandView.refresh();
    }

    /**
     * Create wife table view string list
     *
     * @param couples the couples
     * @return Initialized wife view string list
     */
    private List<String> createWifeView(Couples couples) {
        StringBuilder stringBuilder = new StringBuilder();
        couples.getCouples().values().forEach(v -> stringBuilder.append(v.getName())
                                                                .append(" - ")
                                                                .append("szabadideje: ")
                                                                .append(v.getFreeTime())
                                                                .append(" perc.,"));
        return Arrays.asList(stringBuilder.toString().split(REGEX_SPLIT_COMMA));
    }

    /**
     * Update wife Table
     *
     * @param wifeView the wife view
     * @param wifeColumn the wife column
     */
    private void updateWife(TableView<String> wifeView, TableColumn<String, String> wifeColumn) {
        wifeView.getColumns().clear();
        wifeView.getColumns().add(wifeColumn);
        wifeColumn.setMinWidth(300);
        wifeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        wifeView.setItems(wifeData);
        wifeView.refresh();
    }

    /**
     * Set the text of the two opposite team
     *
     * @param cup The cup
     */
    private void setTeamsText(Cup cup) {
        String teamsText = cup.getTeam1().getName() + " : " + cup.getTeam2().getName();
        teams.setText(teamsText);
    }

    /**
     * Set the text of the average beers
     *
     * @param couples the couples
     */
    private void setAverageBeersText(Couples couples) {
        List<Husband> husbandList = new ArrayList<>(couples.getCouples().keySet());
        Double beers = husbandList.stream().collect(Collectors.averagingInt(Husband::getBeers));
        beerSum.setText("Atlagosan: " + beers + " sor.");
    }

    /**
     * Set the text of the total free time
     *
     * @param couples the couples
     */
    private void setTotalFreeTimeText(Couples couples) {
        List<Wife> wifeList = new ArrayList<>(couples.getCouples().values());
        int freeTime = wifeList.stream().mapToInt(Wife::getFreeTime).sum();
        totalFreeTime.setText("Osszesen: " + freeTime + " perc.");
    }
    /**
     * Stop application
     */
    @Override
    public void stop() {
        springContext.stop();
        logger.info("Application is stopped!");
    }
}
