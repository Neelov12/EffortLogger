package application;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class EffortLoggerPlanningPoker {
	
	
	/*
	 * Created by Benjamin Wise
	 * 
	 * This accesses the file of previous projects
	 * and gets the descriptions of each as an array of Strings
	 * 
	 * the function takes a String, the name of the file,
	 * and returns an array of Strings, the project descriptions
	 */
	public static String[] getProjectFile(String projectFileName){
		try {
			File projectFile = new File(projectFileName);
			if(projectFile.exists() && projectFile.canRead()) {//try to check if the file exists and then check that its readable
				int lines = 0;//the number of lines in the file
				Scanner readFile = new Scanner(projectFile);//start reading the file
				while(readFile.hasNextLine()) {//while theres a line remaining
					lines++;//increase the number of lines
					readFile.nextLine();//then move down one
				}
				if(lines == 0) {//if the file contains nothing
					readFile.close();//make sure to close the reader
					System.out.println("An error occured while trying to read the file " + projectFileName);//tell the user
					return new String[1];//then return a null string array
				}
				readFile.close();//close
				readFile = new Scanner(projectFile);//and repeat
				String[] projects = new String[lines];//this time filling up an array
				int i = 0;//number of the current line
				while(readFile.hasNextLine()) {
					projects[i] = readFile.nextLine();//with each line as a string in the array
					i++;
				}
				readFile.close();//close when done
				return projects;//return the completed string array
			} else {
				System.out.println("An error occured while trying to read the file " + projectFileName);//tell the user
				return new String[1];//return a null string array if the file is not readable
			}
		} catch (Exception e) {//tell the user if something went wrong
			System.out.println("An error occured while trying to read the file " + projectFileName);
			e.printStackTrace();
			return new String[1];//return a null string array if the file is not readable
		}
	}
	
	/*
	 * Created by Benjamin Wise
	 * 
	 * this saves the projects to a file
	 * each as a line in said file
	 * 
	 * the function takes a String array,
	 * the project descriptions, and a String,
	 * the name of the file, and returns nothing
	 * 
	 */
	public static void saveProjects(String[] previousProjects, String projectFileName) {
		try {
			File projectFile = new File(projectFileName);
			if(projectFile.createNewFile()) {//either its a new file
				projectFile.setWritable(true);//just in case
				projectFile.setReadable(true);//just in case
				FileWriter writeFile = new FileWriter(projectFileName);//start writing
				for(int i = 0; i < previousProjects.length; i++) {
					writeFile.write(previousProjects[i] + "\n");//and write each project as a line
				}
				writeFile.close();//close it after its done writing
			} else if(projectFile.canWrite()) {//or it can be written
				FileWriter writeFile = new FileWriter(projectFileName,true);//start appending
				for(int i = 0; i < previousProjects.length; i++) {
					writeFile.write(previousProjects[i] + "\n");//and append each project as a line
				}
				writeFile.close();//close it after its done writing
			} else {//or something went wrong
				System.out.println("File " + projectFileName + " had been created but was not writable.");//so tell the user
			}
		} catch (Exception e) {//tell the user if something went wrong
			System.out.println("An error occured while trying to write the file " + projectFileName);
			e.printStackTrace();
		}
		
	}
	
	
	/*
	 * Created by Benjamin Wise
	 * 
	 * this gets the time each previous relevant project has taken
	 * given each project as a String, in an array of Strings
	 * 
	 * the function takes an array of Strings,
	 * the project times, descriptions, and names,
	 * and returns an array of ints, the times for each project
	 */
	public static int[] computeTimes(String[] previousProjects) {
		
		final int startingIndex = 15;//the index the project time starts at in a given string description of the projects
		//this depends heavily on the formatting of the project strings
		
		int[] projectTimes = new int[previousProjects.length];//the array of how long every project has taken
		String project = "";//a particular project saved in the file
		Integer projectTime;//the time a particular project takes
		int lastDigit;//the index of the last digit in the project description
		for(int i = 0; i < previousProjects.length; i ++) {//go through each project description
			project = previousProjects[i];
			lastDigit = startingIndex;//start it at the first digit for every project, which depends on the format each project is saved in the file as
			
			/* format for now
			 * Project Time = 1, Project Name = "asdsad", Project keywords = "asd","asd","Asdas", Project Description = "yourmom"
			 */
			
			while(Character.isDigit(project.charAt(lastDigit))) {//while the character at a given index is part of the project time
				lastDigit++;//keep going
			}
			project = project.substring(startingIndex,lastDigit);//narrow the string we have to only the project time number
			projectTime = Integer.decode(project);//and convert that to a useable int value
			projectTimes[i] = projectTime.intValue();//and then put it back in the array
		}
		return projectTimes;//return the array of project times
	}
	
	/*
	 * Created by Benjamin Wise
	 * 
	 * this computes the estimated time of the next project,
	 * as the average of the previous relevant projects' times
	 * 
	 * the function takes an array of ints,
	 * the times of the previous relevant projects,
	 * and returns an int, the time estimated for the next project
	 */
	public static int getEstimate(int[] previousProjectTimes) {
		
		int sum = 0;
		for(int i = 0; i < previousProjectTimes.length; i++) {
			sum += previousProjectTimes[i];
		}
		int avg = (int)(sum/previousProjectTimes.length);
		
		return avg;
	}
}
