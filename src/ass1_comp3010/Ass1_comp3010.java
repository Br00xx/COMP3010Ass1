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
		
		//Ask the members belonging to each group
		System.out.println("Enter the list of members of each group");		
		for(int i=0; i<groupCount; i++) {
			input = scan.nextLine();
			members.add(removeChar(input.toCharArray(), ' '));
		}
		
		//Close Scanner
		scan.close();
		
		// Displaying groups and members in readable manner
		for(char[] mem : members) {
			System.out.println(mem);
		}
		
		while(members.size()>0) {
			char pickedMember = findMembers(members);
			
			for(int i=0; i<members.size(); i++) {
				for(char c : members.get(i)) {
					if(c == pickedMember) {
						members.remove(i);
					}
				}
			}
		}
		
		for(char[] mem : members) {
			System.out.println(mem);
		}
		
	}
	
	/*
	 * O(n) char removal
	 * 	put members in char array and remove spacings
	 */
	public static char[] removeChar(char[] list, char remove) {
		int next = 0; 
		
		for(int cur=0; cur<list.length; cur++) {
			if(list[cur] != remove) {
				list[next++] = list[cur];
			}
		}
		String memberString = new String(list, 0, next);
		return memberString.toCharArray();
	}
	
	public static char findMembers(ArrayList<char[]> members) {
		int longestStreak = 1;
		int currentStreak;
		char memberID = '\0';
		
		for(int i=0; i<members.size()-1; i++) {
			for(int j=0; j<members.get(i).length; j++) {
				if(members.get(i)[j] !=0) {
					currentStreak = 1;
					for(int k=i+1; k<members.size(); k++) {
						for(int l=0; l<members.get(k).length; l++) {
							if(members.get(k)[l]!=0 && members.get(i)[j] == members.get(k)[l]) {
								currentStreak++;
								if(currentStreak>longestStreak) {
									longestStreak = currentStreak;
									memberID = members.get(i)[j];
								}
							}
						}
					}
				}
				
			}
		}
		
		System.out.println("Chosen");
		System.out.println(longestStreak + " " + memberID);
		
		return memberID;
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
