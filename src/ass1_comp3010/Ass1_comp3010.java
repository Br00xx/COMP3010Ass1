package ass1_comp3010;
import java.util.ArrayList;
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
		
		ArrayList<char[]> members = new ArrayList<char[]>(groupCount);
		ArrayList<Character> groups = new ArrayList<Character>();
		
		//Ask the members belonging to each group
		System.out.println("Enter the list of members of each group");		
		for(int i=0; i<groupCount; i++) {
			input = scan.nextLine();
			members.add(input.toCharArray());
			groups.add((char)(i+1));
		}
		
		//Close Scanner
		scan.close();
		
		// Displaying groups and members in readable manner
		for(char[] mem : members) {
			System.out.println(mem);
		}
		
		/*
		 * O(n) char removal
		 * 	put members in char array and remove spacings
		 */
		/*
		char[] charsMembers = input.toCharArray();
		char removeCharacter = ' ';
		int next = 0;
		for (int cur = 0; cur < charsMembers.length; ++cur) {
		    if (charsMembers[cur] != removeCharacter) {
		    	charsMembers[next++] = charsMembers[cur];
		    }
		}
		String members = new String(charsMembers, 0, next);
		charsMembers = members.toCharArray();
		*/
		
		//findSolution(charsMembers);
	}
	
	
	
	
	public static void findSolution(char[] members) {
		int longestStreak = 1;
		int currentStreak = 1;
		char memberID = '\0';
		
		
		//While 
		for(int i = 0; i< members.length; i++) {
			currentStreak = 1;
			for(int j = i+1; j< members.length; j++) {
				if(members[i] != '0' || members[j] != '0') {
					if(members[i] == members[j]) {
						System.out.println("Match " + members[i] + " " + currentStreak);
						currentStreak++;
						if(currentStreak>longestStreak) {
							longestStreak = currentStreak;
							memberID = members[i];
						}
					}
				}
			}
		}
		System.out.println();
		System.out.println(longestStreak + " " + memberID);
		
	}

}
