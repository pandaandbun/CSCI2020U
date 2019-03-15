package lab06;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class lab06 extends Application {

	private static double[] avgHousingPricesByYear = { 247381.0, 264171.4, 287715.3, 294736.1, 308431.4, 322635.9,
			340253.0, 363153.7 };
	private static double[] avgCommercialPricesByYear = { 1121585.3, 1219479.5, 1246354.2, 1295364.8, 1335932.6,
			1472362.0, 1583521.9, 1613246.3 };

	private static String[] ageGroups = { "18-25", "26-35", "36-45", "46-55", "56-65", "65+" };
	private static int[] purchasesByAgeGroup = { 648, 1021, 2453, 3173, 1868, 2247 };
	private static Color[] pieColours = { Color.AQUA, Color.GOLD, Color.DARKORANGE, Color.DARKSALMON, Color.LAWNGREEN,
			Color.PLUM };

	@Override
	public void start(Stage primaryStage) {
		double deg = 0, len = 0, add = 0;
		
		HBox rootPane = new HBox();
		rootPane.setPadding(new Insets(100, 100, 100, 100));
		//rootPane.setAlignment(Pos.CENTER);
		//rootPane.setHgap(5);
		//rootPane.setVgap(5);
		
		Pane pane = new Pane();
		Pane pane1 = new Pane();

		//Bar Chart
		for (int i = 0; i < avgHousingPricesByYear.length; i++) {
			Rectangle bar = new Rectangle();
			bar.setX(i * 100 + 10);
			bar.setY(avgCommercialPricesByYear[avgHousingPricesByYear.length - 1] / 5000
					- avgHousingPricesByYear[i] / 5000);
			bar.setWidth(20);
			bar.setHeight(avgHousingPricesByYear[i] / 5000);
			bar.setFill(Color.RED);

			Rectangle bar2 = new Rectangle();
			bar2.setX(i * 100 + 30);
			bar2.setY(avgCommercialPricesByYear[avgCommercialPricesByYear.length - 1] / 5000
					- avgCommercialPricesByYear[i] / 5000);
			bar2.setWidth(20);
			bar2.setHeight(avgCommercialPricesByYear[i] / 5000);
			bar2.setFill(Color.BLUE);

			pane.getChildren().addAll(bar, bar2);
		}
		
		//Pie Chart
		for (int i = 0; i < ageGroups.length; i++) {
			add += purchasesByAgeGroup[i];
		}
		
		for (int i = 0; i < ageGroups.length; i++) {
			
			len = (purchasesByAgeGroup[i]/add) * 2 * Math.PI * 57;			
			Arc arc = new Arc(600, 100, 100, 100, deg, len);
			arc.setType(ArcType.ROUND);
			arc.setFill(pieColours[i]);
			arc.setStroke(Color.BLACK);
			
			deg = deg + ((purchasesByAgeGroup[i]/add) * 360);
			
			pane1.getChildren().add(arc);
		}	
		
		rootPane.getChildren().addAll(pane, pane1);
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(rootPane);
		primaryStage.setTitle("lab06"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}

}
