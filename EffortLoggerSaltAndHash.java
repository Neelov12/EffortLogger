package application;
import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EffortLoggerSaltAndHash {
	/*
	 * Created by Benjamin Wise
	 * 
	 * This is a very basic salt creator
	 * that is only outfitted with three prebuilt salts,
	 * since this project is not intended to be incredibly secure;
	 * having a salt, let alone three salts, should be more than secure enough.
	 * 
	 * This function returns one of three salts to ensure malicious actors cannot use even the final 
	 * product of salting-and-hashing to determine sensitive info, in this case user id, user password,
	 * and other personal information, depending on the exact formatting and storage of said information.
	 * The salt returned is chosen randomly to ensure duplicate inputs do not give duplicate hashes.
	 */
	public static String salt(String input) {
		if(input.length() <= 0) {//if the input is nonexistent
			return "error: bad input";//tell the user
		}
		int rand = (int)(Math.random() * 3);//get a random value
		if(rand == 1) {
			return input+"*A@!";//and return one of three salts
		} else if(rand == 2){
			return input+"(JG+";//depending on the random value
		} else {
			return input+"~(&[.";
		}
	}
	
	
	/*
	 * Created by Benjamin Wise
	 * 
	 * This is a basic salting and hashing function, and uses SHA-256 to do the actual hashing, 
	 * since nothing I can create would be better than that,
	 * as noted in the design specifications for this prototype in phase 3.
	 * 
	 * This function calls effortSalt to salt the input before hashing said input
	 * and is independent of formatting and what information is stored.
	 * 
	 */
	public static String hash(String input) {
		try {
			MessageDigest hashy = MessageDigest.getInstance("SHA-256");//get a new hash, Hashy the SHA-256 hash
			String saltedInput = salt(input);//get the salted input
			hashy.update(saltedInput.getBytes());//hash the bytes of the salted input
			String hashed = new String(Base64.getEncoder().encode(hashy.digest()));//get and convert the hashed data to a compact and semi-readable format
			
			//debug
			//System.out.println("Original input: " + input + "\nSalted input: " + saltedInput + "\nSalted and Hashed input: " + hashed + "\n");
		
			return hashed;//return the final, salted-and-hashed data
		} catch (NoSuchAlgorithmException exc) {
			System.out.println("Error in hash function: No Such Algorithm Exception");//technically this should never happen, but I'd rather be prepared and correct than unprepared and wrong
			return input;
		}
	}
}
