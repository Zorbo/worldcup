package com.worldcup.demo.worldcup;

import com.worldcup.demo.worldcup.service.Couples;
import com.worldcup.demo.worldcup.service.Cup;
import java.io.IOException;
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

	private final static String DATA = "C:\\Training\\worldcup\\src\\main\\resources\\data.txt";
	private static final Logger logger = LoggerFactory.getLogger(WorldCupApplication.class);
	private ConfigurableApplicationContext springContext;
	private Cup cup;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void init() {
		springContext = SpringApplication.run(WorldCupApplication.class);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("World Cup");
		// Create resources on startup
		Couples couples = new Couples(DATA);
		Button matchButton = new Button("Match!");
		// Set the button function
		matchButton.setOnAction(event -> {
			 this.cup = new Cup(DATA);
			watchMatch(couples,cup);
		});
		// Set the button element position
		matchButton.setLayoutX(480);
		matchButton.setLayoutY(650);


		// TODO: add 3 TableView, 3 static text, 1 Team1:Team2 text and 2 counting text

		// Create couple view
		Label coupleViewLabel = new Label("Hazasparok");
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

		//Create Wife view



		// Init Desktop view
		Pane root = new Pane();
		Scene scene = new Scene(root, 200, 100);
		root.getChildren().addAll(coupleSp,matchButton);
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
		return Arrays.asList(stringBuilder.toString().split("\\s*,\\s*"));
	}

	@Override
	public void stop() {
		springContext.stop();
	}

}
