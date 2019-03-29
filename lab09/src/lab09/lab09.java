package lab09;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class lab09 extends Application {

	private ArrayList<Double> open = new ArrayList<Double>();
	private ArrayList<Double> close = new ArrayList<Double>();
	private int color = 0;
	private Pane rootPane;

	public void start(Stage stage) {
		open = new ArrayList<Double>();
		close = new ArrayList<Double>();
		rootPane = new Pane();
		rootPane.setPadding(new Insets(50, 50, 50, 50));

		downloadStockPrices();
		drawLinePlot();

		// Create a scene and place it in the stage
		Scene scene = new Scene(rootPane);
		stage.setTitle("lab09"); // Set the stage title
		stage.setScene(scene); // Place the scene in the stage
		stage.show(); // Display the stage
	}

	private void downloadStockPrices() {
		try {
			String out = new Scanner(new URL(
					"https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=MSFT&interval=5min&apikey=demo")
							.openStream(),
					"UTF-8").useDelimiter("\\A").next();

			JSONObject time = new JSONObject(out).getJSONObject("Time Series (5min)");
			Iterator<String> keys = time.keys();

			while (keys.hasNext()) {
				String key = keys.next();
				// System.out.println(time.getJSONObject(key).get("1. open"));
				open.add(Double.parseDouble(time.getJSONObject(key).get("1. open").toString()));
				close.add(Double.parseDouble(time.getJSONObject(key).get("4. close").toString()));
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void drawLinePlot() {
		Line xAxis = new Line(50, 600, 600, 600);
		Line yAxis = new Line(50, 50, 50, 600);

		rootPane.getChildren().addAll(xAxis, yAxis);
		
		plotLine(open);
		color = 1;
		plotLine(close);
	}

	private void plotLine(ArrayList<Double> data) {
		int xGap = 50;
		
		for (int i = 1; i < data.size(); i++) {
			Line plot = new Line(xGap , data.get(i - 1) * 100 - 11500, xGap + 5, data.get(i) * 100 - 11500);
			if (color == 0) {
				plot.setStroke(Color.RED);
			} 
			
			if (color == 1) {
				plot.setStroke(Color.BLUE);
			}
			
			rootPane.getChildren().addAll(plot);
			
			xGap += 5;
		}
		
	}

	public static void main(String[] args) {
		launch(args);
		// downloadStockPrices();

	}

}
