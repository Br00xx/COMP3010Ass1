package ass1_comp3010;
import java.util.Scanner;


public class Ass1_comp3010 {
	
	public static void main(String[] args) {
		int groupCount = 0;
		
		//Open scanner
		Scanner scan = new Scanner(System.in);
		
		//Ask how many groups
		System.out.println("Enter the number of groups from which you must find representatives:");
		String input = scan.nextLine();
		
		groupCount = Integer.parseInt(input);
		
		//Ask the members belonging to each group
		System.out.println("Enter the list of members of each group");
		input = scan.nextLine();		
		
		for(int i=1; i<groupCount; i++) {
			input = input + scan.nextLine();
		}
		
		//Close Scanner
		scan.close();
		
		String[] strMembers = input.split("0");
		for(String n : strMembers)
			System.out.println(n);
		
		//Put list into array
		
		
		//Make second array with associated group
		/*
		 * Members
		 * 1 2 3 0 2 3 4 0 3 4 5 0
		 * Group
		 * 1 1 1 0 2 2 2 0 3 3 3 0 
		 */
		
		// Sort top list while moving bottom
		
		
	}	

}
