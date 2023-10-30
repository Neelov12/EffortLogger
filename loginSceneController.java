package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button; 
import javafx.scene.control.Label; 
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javafx.event.ActionEvent;

public class loginSceneController {
	
	// Login username and password objects
	
	@FXML
	private TextField username;
	
	@FXML
	private TextField password; 
	
	@FXML
	private Label wrongLogin; 
	
	private mainMenuController obj = new mainMenuController(); 
	
	private HashMap<String, String> map;
	
	private int criticalError = 0;
    private boolean validUser = false;

	// Event Listener on Button.onAction
	@FXML
	public void SubmitButtonClicked(ActionEvent event) {
		// TODO Autogenerated
		
		authenticate(); 		
	}
	
	private void authenticate() {
        
        map = new HashMap<String, String>();
        
        /*
         *  Reads file 
         */
        
        try(FileReader reader = new FileReader("EmployeeData.txt")){

            BufferedReader br = new BufferedReader(reader);
            String s;

            while((s = br.readLine()) != null){
                map.put(s, br.readLine());
                System.out.println(map.get(s));
            }
            reader.close();

        }catch(IOException e){
            e.printStackTrace();
        }
        
        /*
         *  Tests username and password 
         */
        
        String userName = "";
        String Password = "";
        
        if (validUser == false && criticalError < 3){

            userName = username.getText().toString();
            Password = password.getText().toString();
            
            // Use for debugging if any issues arise 
            //System.out.println(userName);

            if(map.containsKey(userName) && map.get(userName).equals(Password)){
            	Main m = new Main(); 
            	
                validUser = true;
                System.out.println("Welcome to effortlogger v2");
                m.changeScene("/application/mainMenu.fxml");
                //obj.changeName(userName); 
                // Change Scene
            }
            else {
                criticalError++;
                wrongLogin.setText("Invalid username or password");
            }
        }
        else {
        	wrongLogin.setText("Account Locked");
        }
	}
}