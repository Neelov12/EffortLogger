package application;
import java.util.Scanner;
public class Main1 {

	public static void main(String[] args) {
		//testing the salt and hash prototype
//		Scanner input = new Scanner(System.in);
//		System.out.println("Beginning testing of salting and hashing prototype. \nPlease specify how many tests you would like to perform:");
//		int testsRemaining = Integer.decode(input.next()).intValue();
//		while(testsRemaining > 0) {
//			System.out.println("Enter some text to salt and hash:");
//			System.out.println("Output: " + EffortLoggerSaltAndHash.hash(input.next()));
//			testsRemaining--;
//		}
//		input.close();
		
		
		//testing the planning poker preparation features
		
		Scanner input = new Scanner(System.in);
		System.out.println("Beginning testing planning poker basic functionality. \nPlease enter the name of the file to be saved.");
		String fileName = input.next();
		System.out.println("How many projects to create?");
		int numProjects = Integer.decode(input.next()).intValue();
		input.nextLine();//to get rid of a null line
		String[] projects = new String[numProjects];
		int line = 0;
		while(line < numProjects) {
			System.out.println("Now create some previous projects with this formatting: \nProject Time = num, Project Name = \"name\", Project Keywords = \"asd\",\"hfdhg\", Project Description = \"stuff\".");
			projects[line] = input.nextLine();
			line++;
		}
		input.close();
		System.out.println("Saving to file.");
		EffortLoggerPlanningPoker.saveProjects(projects, fileName);//note that this file is currently within the source folder (/users/user/eclipse-workspace/EffortLoggerStuff in this case)
		String[] shouldBeProjects = EffortLoggerPlanningPoker.getProjectFile(fileName);
		System.out.println("Contents of file: ");
		for(int i = 0; i < shouldBeProjects.length; i++) {
			System.out.println(shouldBeProjects[i]);
		}
		int[] projectTimes = EffortLoggerPlanningPoker.computeTimes(shouldBeProjects);
		int avg = EffortLoggerPlanningPoker.getEstimate(projectTimes);
		System.out.println("Average time of projects: " + avg);
	}

}
