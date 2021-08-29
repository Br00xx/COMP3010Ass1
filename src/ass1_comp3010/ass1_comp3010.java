package ass1_comp3010;
import java.util.ArrayList;
import java.util.Scanner;


public class Ass1_comp3010 {
	
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		
		int groupCount = 0;
		
		//Open scanner
		Scanner scan = new Scanner(System.in);
		
		//Ask how many groups
		System.out.println("Enter the number of groups from which you must find representatives:");
		String input = scan.nextLine();
		groupCount = Integer.parseInt(input);
		
		ArrayList<String> members = new ArrayList<String>();
		ArrayList<String> memGroup = new ArrayList<String>();
		
		//Ask the members belonging to each group
		System.out.println("Enter the list of members of each group");
		
		//Collecting all the members and splitting into an array
		input = scan.nextLine();
		for(int i=1; i<groupCount; i++) {
			input = input + " " + scan.nextLine();
		}
		String[] membersTemp = input.split(" "); 
		String[] groupTemp = new String[membersTemp.length];
		
		//Making a second array of same length but the same index on second array as the first
		// is the associated group number
		int grp = 0;
		for(int i=0; i<membersTemp.length; i++) {
			if(!membersTemp[i].equals("0")) {
				groupTemp[i] = String.valueOf(grp);
			} 
			else if(membersTemp[i].equals("0")) {
				groupTemp[i] = String.valueOf(grp);
				grp++;
			}
		}
		
		//Merge sort the two list in sync.
		// O(nlogn)
		mergeSort(membersTemp, groupTemp, 0, membersTemp.length-1);
		
		//Close Scanner
		scan.close();
		
		//Adding members numbers are associated group number to ArrayLists
		// O(n)
		for(int i=0; i<membersTemp.length; i++) {
			if(!membersTemp[i].equals("0")) {
				//System.out.println(membersTemp[i] + " " + groupTemp[i]);
				members.add(membersTemp[i]);
				memGroup.add(groupTemp[i]);
			}
		}
		
		//Run pickMembers then remove members until ArrayList is empty
		while(!members.isEmpty()) {
			String picked = pickMembers(members, memGroup);
			
			System.out.println("Picked " + picked);
			
			removeElements(members, memGroup, picked);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Time: " + (end - start));
	}
	
	// Find longest streak
	public static String pickMembers(ArrayList<String> mem, ArrayList<String> groups) {
		int longestStreak = 0;
		int currentStreak = 1;
		String winnerMem = mem.get(0);
		String curMem = mem.get(0);

		
		for(int i=1; i<mem.size(); i++) {
			if(curMem.equals(mem.get(i))) {
				currentStreak++;
			}
			if(currentStreak>longestStreak) {
				longestStreak = currentStreak;
				winnerMem = curMem;
			}
			if(!curMem.equals(mem.get(i))) {
				curMem = mem.get(i);
				currentStreak = 1;
			}
		}
		
		return winnerMem;
	}
	
	// Loop through array list removing items when they equal memNum or groupNum
	public static void removeElements(ArrayList<String> mem, ArrayList<String> groups, String memNum) {
		ArrayList<String> removeGroups = new ArrayList<String>();
		// Checks which groups contain the chosen member
		for(int i=mem.size()-1; i>=0; i--) {
			if(mem.get(i).equals(memNum)) {
				removeGroups.add(groups.get(i));
			}
		}
		
		//Removes all the elements in those groups
		for(int i=mem.size()-1; i>=0; i--) {
			for(String n : removeGroups) {
				if(groups.get(i).equals(n)) {
					mem.remove(i);
					groups.remove(i);
					break;
				}
			}
		}
		
	}
	
	//Old method
	// Not good efficiency
	public static String findMembers(ArrayList<String[]> members) {
		int longestStreak = 0;
		int currentStreak;
		String memberID = "";
		
		if(members.size() == 1) {
			return members.get(0)[0];
		}
		
		for(int i=0; i<members.size()-1; i++) {
			for(int j=0; j<members.get(i).length; j++) {
				if(!members.get(i)[j].equals("0")) {
					currentStreak = 1;
					for(int k=i+1; k<members.size(); k++) {
						for(int l=0; l<members.get(k).length; l++) {
							if(!members.get(k)[l].equals("0") && members.get(i)[j].equals(members.get(k)[l])) {
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
		
		//System.out.println("Chosen");
		//System.out.println(longestStreak + " " + memberID);
		
		return memberID;
	}
	
	/*
	 * Using modified code from this post
	 *  https://stackoverflow.com/questions/23064247/java-mergesort-with-strings
	 *  Merge sort but with Strings
	 *  The order that members gets ordered into is the same that group is sorted into 
	 */
	public static void mergeSort(String[] members, String[] group, int from, int to) {
		if(from == to) {
			return;
		}
		int mid = (from + to)/2;
		// sort the first and the second half
		mergeSort(members, group, from, mid);
		mergeSort(members, group, mid+1, to);
		merge(members, group, from, mid, to);
	}// end mergeSort
	
    public static void merge(String[] members, String[] group, int from, int mid, int to) {
        int n = to - from + 1;       // size of the range to be merged
        String[] b = new String[n];   // merge both halves into a temporary array b
        String[] c = new String[n];
        int i1 = from;               // next element to consider in the first range
        int i2 = mid + 1;            // next element to consider in the second range
        int j = 0;                   // next open position in b

        // as long as neither i1 nor i2 past the end, move the smaller into b
        while (i1 <= mid && i2 <= to) {
            if (members[i1].compareTo(members[i2]) < 0) {
                b[j] = members[i1];
                c[j] = group[i1];
                i1++;
            } else {
                b[j] = members[i2];
                c[j] = group[i2];
                i2++;
            }
            j++;
        }

        // note that only one of the two while loops below is executed
        // copy any remaining entries of the first half
        while (i1 <= mid) {
            b[j] = members[i1];
            c[j] = group[i1];
            i1++;
            j++;
        }

        // copy any remaining entries of the second half
        while (i2 <= to) {
            b[j] = members[i2];
            c[j] = group[i2];
            i2++;
            j++;
        }

        // copy back from the temporary array
        for (j = 0; j < n; j++) {
            members[from + j] = b[j];
            group[from + j] = c[j];
        }
    }//end merge
	
}




































