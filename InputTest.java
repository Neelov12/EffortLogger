/*
 * This class contains the methods that test different types of inputs required for effort logger.
 * Created by: Ethan Hodge
 * Date: 10/27/2023
 */
package application;
import java.util.regex.*;

public class InputTest {
	
	public boolean UsernameTest(String u) {
		String usernameRegex = "[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.]+"; // Regex for a standard email address
		return Pattern.matches(usernameRegex, u);
	}
	
	public boolean PasswordTest(String p) {
		if (p.length() < 8 || p.length() >20) return false;
		String passwordRegex = "[a-zA-Z0-9!()?_'~;:@#$%^&*+=]+[a-zA-Z0-9!()?_'~\\[\\];:@#$%^&*+=-]*"; // Taken from IBM's list of valid characters
		return Pattern.matches(passwordRegex, p);
	}
	
	public boolean planPokerRounds(int i) {
		if (i < 0 || i > 10) return false;
		else return true;
	}
	
	

}
