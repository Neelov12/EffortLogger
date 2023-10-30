package application; 

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class DataLossPrevention {
	
    public static void main(String[] args) {
        displayProgramInfo();
        backupUserActions(); // This is a risk-reduction prototype for data backup
    }
    private static void displayProgramInfo() {
        System.out.println("EffortLogger Prototype");
    }
    private static void backupUserActions() {
        try (FileWriter logFile = new FileWriter("user_actions.txt");
             PrintWriter backupFile = new PrintWriter("backup_actions.txt")) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("EffortLogger is running. Type 'exit' to quit.");
            while (true) {
                System.out.print("Enter an action: ");
                String action = scanner.nextLine();
                logFile.write(action + "\n");
                backupFile.write(action + "\n");
                if (action.equalsIgnoreCase("exit")) {
                    break;
                }
            }
            System.out.println("Logging complete.");
        } catch (IOException e) {
            System.err.println("An error occurred while logging user actions.");
            e.printStackTrace();
        }
    }
}