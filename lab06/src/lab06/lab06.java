package lab06;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class lab06 extends Application {

	private static double[] avgHousingPricesByYear = { 247381.0, 264171.4, 287715.3, 294736.1, 308431.4, 322635.9,
			340253.0, 363153.7 };
	private static double[] avgCommercialPricesByYear = {1121585.3,1219479.5,1246354.2,1295364.8,1335932.6,1472362.0,
			1583521.9,1613246.3};

	@Override
	public void start(Stage primaryStage) {

		Pane pane = new Pane();
		//pane.setAlignment(Pos.CENTER);

		Arc arc = new Arc(10, 600, 50, 50, 0, 200);
		arc.setFill(Color.WHITE);
		arc.setStroke(Color.BLACK);
		
		for (int i = 0; i < avgHousingPricesByYear.length; i++) {
			Rectangle bar = new Rectangle();
			bar.setX(i * 100 + 10);
			bar.setY(avgCommercialPricesByYear[avgHousingPricesByYear.length - 1]/5000 - avgHousingPricesByYear[i]/5000);
			bar.setWidth(20);
			bar.setHeight(avgHousingPricesByYear[i]/5000);
			bar.setFill(Color.RED);
			
			
			Rectangle bar2 = new Rectangle();
			bar2.setX(i * 100 + 30);
			bar2.setY(avgCommercialPricesByYear[avgCommercialPricesByYear.length - 1]/5000 - avgCommercialPricesByYear[i]/5000);
			bar2.setWidth(20);
			bar2.setHeight(avgCommercialPricesByYear[i]/5000);
			bar2.setFill(Color.BLUE);


			pane.getChildren().addAll(bar, bar2);
		}
		

		//pane.getChildren().addAll(cir, line1, line2, line3, line4, line5, line6, line7, line8, arc);

		// Create a scene and place it in the stage
		Scene scene = new Scene(pane);
		primaryStage.setTitle("lab06"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		launch(args);
	}

}
