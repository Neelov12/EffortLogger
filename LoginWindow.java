/*
Author: Ethan Hodge
*/

package application;
import javafx.application.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.*;

public class LoginWindow extends Application{
	
	public void start(Stage primaryStage) {
		
		// Setting up the login window and its elements
		primaryStage.setTitle("EffortLogger Login");
		
		Label usernameLabel = new Label("Username: ");
		Label passwordLabel = new Label("Password: ");
		
		TextField usernameField = new TextField();
		PasswordField passwordField = new PasswordField();
		
		
		Button loginButton = new Button("Login");
		InputTest tester = new InputTest();
		Text text = new Text();
		
		VBox root = new VBox(10);
		root.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, text);
		root.setAlignment(Pos.CENTER);

		// Clicking the login button starts the validation process using the InputTest class
		loginButton.setOnAction(event -> {
			String errorMessage = "";
			String username = usernameField.getText();
			String password = passwordField.getText();
			
			if (!tester.UsernameTest(username)) errorMessage += "Username is invalid\n";
			if (!tester.PasswordTest(password)) errorMessage += "Password is invalid\n";
			if (tester.UsernameTest(username) && tester.PasswordTest(password)) errorMessage = "Login successful!";
			text.setText(errorMessage);
		});
		
		Scene scene = new Scene(root, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
