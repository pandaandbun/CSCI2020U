package lab10;

import lab10.Client;
import javafx.application.Application;
import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientGUI extends Application {

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		
		Client cli = new Client ();
		
		TextField tfName = new TextField();
		TextField tfMsg = new TextField();
		
		HBox hbUser = new HBox (new Label("Username: "), tfName);
		HBox hbMsg = new HBox (new Label("Message: "), tfMsg);
		
		Button sendBt = new Button("Send");
		Button exitBt = new Button("Exit");


		VBox vb = new VBox();
		vb.getChildren().addAll(hbUser, hbMsg, sendBt, exitBt);
		vb.setPadding(new Insets(20, 20, 50, 20));
		vb.setSpacing(10);
		
		sendBt.setOnAction(e -> {
			String message = tfName.getText() + "#" + tfMsg.getText();
			cli.sendMessage(message);
		});

		exitBt.setOnAction(e -> {
			cli.closeConnection();
			System.exit(0);
		});
		
		// Create a scene and place it in the stage
		Scene scene = new Scene(vb);
		primaryStage.setTitle("Lab 10"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}
	
	public void sendMessage () {
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
