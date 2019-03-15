package lab07;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class lab07 extends Application {

	private static Color[] pieColours = { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN,
			Color.PLUM };

	public void start(Stage primaryStage) throws Exception {

		double sum = 0, deg = 0;
		int i = 0;
		HBox rootPane = new HBox();
		rootPane.setPadding(new Insets(100, 100, 100, 100));
		Pane pane = new Pane();
		GridPane grid = new GridPane();

		// Reading the file
		File file = new File("weatherwarnings-2015.csv");
		Scanner input = new Scanner(file);
		ArrayList<String> warnings = new ArrayList<String>();
		while (input.hasNext()) {
			warnings.add(input.nextLine().toLowerCase().split(",")[5]);
		}
		input.close();

		// Parsing the data
		Set<String> uniqueWarnings = new HashSet<String>(warnings);
		Map<String, Integer> freqWarnings = new HashMap<String, Integer>();
		for (String obj : uniqueWarnings) {
			int freq = Collections.frequency(warnings, obj);
			freqWarnings.put(obj, freq);
			sum += freq;
			
			Rectangle legend = new Rectangle();
			legend.setWidth(30);
			legend.setHeight(10);
			legend.setFill(pieColours[i]);
			legend.setStroke(Color.BLACK);
			grid.add(legend, 1, i);
			grid.add(new Label(obj.toUpperCase()), 2, i);
			
			i++;
		}
		
		// Building the chart
		i = 0;
		for (Integer value : freqWarnings.values()) {
			double len = (value / sum) * 2 * Math.PI * 58;
			Arc arc = new Arc(600, 100, 100, 100, deg, len);
			arc.setType(ArcType.ROUND);
			arc.setFill(pieColours[i]);
			arc.setStroke(Color.BLACK);
			deg = deg + ((value / sum) * 360);
			pane.getChildren().add(arc);
			i++;
		}
		
		
		rootPane.getChildren().addAll(grid, pane);

		// Create a scene and place it in the stage
		Scene scene = new Scene(rootPane);
		primaryStage.setTitle("lab07"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

	}

	public static void main(String[] args) {
		launch(args);
	}

}
