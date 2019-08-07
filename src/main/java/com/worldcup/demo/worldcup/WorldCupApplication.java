package com.worldcup.demo.worldcup;

import com.worldcup.demo.worldcup.service.Couples;
import com.worldcup.demo.worldcup.service.Cup;
import java.util.Arrays;
import java.util.List;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WorldCupApplication extends Application {

	private static final String DATA = "C:\\Training\\worldcup\\src\\main\\resources\\data.txt";
	private static final Logger logger = LoggerFactory.getLogger(WorldCupApplication.class);
	private static final String REGEX_SPLIT_COMMA = "\\s*,\\s*";
	private ConfigurableApplicationContext springContext;
	private Cup cup;
	private ObservableList<String> husbandData;
	private ObservableList<String> wifeData;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() {
		springContext = SpringApplication.run(WorldCupApplication.class);
	}

	@Override
	public void start(Stage primaryStage) {

		// Create resources on startup
		primaryStage.setTitle("World Cup");
		Couples couples = new Couples(DATA);
		Pane root = new Pane();
		Scene scene = new Scene(root, 200, 100);

		// TODO: add 3 TableView, 3 static text, 1 Team1:Team2 text and 2 counting text

		// Create couple view
		Label coupleViewLabel = new Label("Couples");
		ObservableList<String> coupleData = FXCollections.observableArrayList(createCoupleView(couples));
		TableView<String> coupleView = new TableView<>();
		TableColumn<String, String> col1 = new TableColumn<>();
		coupleView.getColumns().add(col1);
		col1.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		coupleView.setItems(coupleData);
		StackPane coupleSp = new StackPane(coupleView);
		coupleSp.getChildren().add(coupleViewLabel);
		coupleSp.setAlignment(Pos.TOP_CENTER);
		coupleSp.setPrefSize(200,400);
		coupleSp.setLayoutX(50);
		coupleSp.setLayoutY(100);

		// Create Husband view
		Label husbandLabel = new Label("Husbands");
		husbandData = FXCollections.observableArrayList(createHusbandView(couples));
		TableView<String> husbandView = new TableView<>();
		TableColumn<String, String> husbandColumn = new TableColumn<>();
		husbandColumn.setMinWidth(100);
		husbandView.getColumns().add(husbandColumn);
		husbandColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		husbandView.setItems(husbandData);
		StackPane husbandSp = new StackPane(husbandView);
		husbandSp.getChildren().add(husbandLabel);
		husbandSp.setAlignment(Pos.TOP_CENTER);
		husbandSp.setPrefSize(200, 400);
		husbandSp.setLayoutX(400);
		husbandSp.setLayoutY(100);

		//Create Wife view
		Label wifeLabel = new Label("Wives");
		wifeData = FXCollections.observableArrayList(createWifeView(couples));
		TableView<String> wifeView = new TableView<>();
		TableColumn<String, String> wifeColumn = new TableColumn<>();
		wifeColumn.setMinWidth(100);
		wifeView.getColumns().add(wifeColumn);
		wifeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		wifeView.setItems(wifeData);
		StackPane wifeSp = new StackPane(wifeView);
		wifeSp.getChildren().add(wifeLabel);
		wifeSp.setAlignment(Pos.TOP_CENTER);
		wifeSp.setPrefSize(200,400);
		wifeSp.setLayoutX(750);
		wifeSp.setLayoutY(100);

		//Create the match button
		Button matchButton = new Button("Match!");
		matchButton.setLayoutX(480);
		matchButton.setLayoutY(650);
		matchButton.setOnAction(event -> {
			this.cup = new Cup(DATA);
			watchMatch(couples,cup);
			husbandData.clear();
			husbandData = FXCollections.observableArrayList(createHusbandView(couples));
			updateHusband(husbandView,husbandColumn);
			wifeData.clear();
			wifeData = FXCollections.observableArrayList(createWifeView(couples));
			updateWife(wifeView,wifeColumn);

		});
		
		// Init Desktop view

		root.getChildren().addAll(coupleSp, husbandSp, wifeSp ,matchButton);
		primaryStage.setScene(scene);
		primaryStage.setWidth(1024);
		primaryStage.setHeight(768);
		primaryStage.show();
	}

	private void watchMatch(Couples couples, Cup cup) {
		couples.getCouples().keySet().forEach(husband -> husband.watchCup(cup));
		couples.getCouples().values().forEach(wife -> wife.watchCup(cup));
	}

	private List<String> createCoupleView(Couples couples) {
		StringBuilder stringBuilder = new StringBuilder();
		couples.getCouples().forEach((key, value) -> stringBuilder.append(key.getName())
																  .append("-")
																  .append(value.getName())
																  .append(","));
		return Arrays.asList(stringBuilder.toString().split(REGEX_SPLIT_COMMA));
	}

	private List<String> createHusbandView(Couples couples) {
		StringBuilder stringBuilder = new StringBuilder();
		couples.getCouples().keySet().forEach(k -> stringBuilder.append(k.getName())
																.append(" - ")
																.append(k.getBeers())
																.append(" sort ivott.,"));
		return Arrays.asList(stringBuilder.toString().split(REGEX_SPLIT_COMMA));
	}

	private void updateHusband(TableView<String> husbandView, TableColumn<String, String> husbandColumn) {
		husbandView.getColumns().clear();
		husbandView.getColumns().add(husbandColumn);
		husbandColumn.setMinWidth(100);
		husbandColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		husbandView.setItems(husbandData);
		husbandView.refresh();
	}

	private List<String> createWifeView(Couples couples) {
		StringBuilder stringBuilder = new StringBuilder();
		couples.getCouples().values().forEach(v -> stringBuilder.append(v.getName())
																.append(" - ")
																.append("szabadideje: ")
																.append(v.getFreeTime())
																.append(" perc.,"));
		return Arrays.asList(stringBuilder.toString().split(REGEX_SPLIT_COMMA));
	}

	private void updateWife(TableView<String> wifeView, TableColumn<String, String> wifeColumn) {
		wifeView.getColumns().clear();
		wifeView.getColumns().add(wifeColumn);
		wifeColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
		wifeView.setItems(wifeData);
		wifeView.refresh();
	}

	@Override
	public void stop() {
		springContext.stop();
	}
}
