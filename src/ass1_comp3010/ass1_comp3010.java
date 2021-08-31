package ass1_comp3010;
import java.util.ArrayList;
import java.util.Scanner;


public class ass1_comp3010 {
	
	public static void main(String[] args) {
		int groupCount = 0;
		
		//Open scanner
		Scanner scan = new Scanner(System.in);
		
		//Ask how many groups
		System.out.println("Enter the number of groups from which you must find representatives:");
		String input = scan.nextLine();
		groupCount = Integer.parseInt(input);
		
		ArrayList<Integer> members = new ArrayList<Integer>();
		ArrayList<Integer> memGroup = new ArrayList<Integer>();
		
		//Ask the members belonging to each group
		System.out.println("Enter the list of members of each group");
		
		//Collecting all the members and splitting into an array
		input = scan.nextLine();
		for(int i=1; i<groupCount; i++) {
			input = input + " " + scan.nextLine();
		}
		
		long start = System.nanoTime();
		
		String[] temp = input.split(" "); 
		int[] membersTemp = new int[temp.length];
		for(int i=0; i<temp.length; i++) {
			membersTemp[i] = Integer.parseInt(temp[i]);
		}
		int[] groupTemp = new int[temp.length];
		
		//Making a second array of same length but the same index on second array as the first
		// is the associated group number
		int grp = 0;
		for(int i=0; i<temp.length; i++) {
			if(!temp[i].equals("0")) {
				groupTemp[i] = grp;
			} 
			else {
				groupTemp[i] = grp;
				grp++;
			}
		}
		
		//Merge sort the two list in sync.
		// O(nlogn)
		sort(membersTemp, groupTemp, 0, membersTemp.length-1);
		
		//Close Scanner
		scan.close();
		
		//Adding members numbers are associated group number to ArrayLists
		// O(n)
		for(int i=0; i<membersTemp.length; i++) {
			if(!(membersTemp[i] == 0)) {
				//System.out.println(membersTemp[i] + " " + groupTemp[i]);
				members.add(membersTemp[i]);
				memGroup.add(groupTemp[i]);
			}
		}
		
		//Run pickMembers then remove members until ArrayList is empty
		ArrayList<Integer> selectedMember = new ArrayList<Integer>();
		while(!members.isEmpty()) {
			int picked = pickMembers(members, memGroup);
			selectedMember.add(picked);
			
			//System.out.println("Picked " + picked);
			
			removeElements(members, memGroup, picked);
		}
		
		long end = System.nanoTime();
		System.out.println("Time: " + (end - start));
		
		System.out.println(selectedMember.size());
		for(int m : selectedMember) {
			System.out.print(m + " ");
		}
	}
	
	// Find longest streak
	public static int pickMembers(ArrayList<Integer> mem, ArrayList<Integer> groups) {
		int longestStreak = 0;
		int currentStreak = 1;
		int winnerMem = mem.get(0);
		int curMem = mem.get(0);

		
		for(int i=1; i<mem.size(); i++) {
			if(curMem == mem.get(i)) {
				currentStreak++;
			}
			if(currentStreak>longestStreak) {
				longestStreak = currentStreak;
				winnerMem = curMem;
			}
			if(!(curMem == mem.get(i))) {
				curMem = mem.get(i);
				currentStreak = 1;
			}
		}
		
		return winnerMem;
	}
	
	// Loop through array list removing items when they equal memNum or groupNum
	public static void removeElements(ArrayList<Integer> mem, ArrayList<Integer> groups, int memNum) {
		ArrayList<Integer> removeGroups = new ArrayList<Integer>();
		// Checks which groups contain the chosen member
		for(int i=mem.size()-1; i>=0; i--) {
			if(mem.get(i).equals(memNum)) {
				removeGroups.add(groups.get(i));
			}
		}
		
		//Removes all the elements in those groups
		for(int i=mem.size()-1; i>=0; i--) {
			for(int n : removeGroups) {
				if(groups.get(i) == n) {
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
	
    public static void merge(int mem[], int[] grp, int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        int lMem[] = new int[n1];
        int lGrp[] = new int[n1];
        int rMem[] = new int[n2];
        int rGrp[] = new int[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) {
        	lMem[i] = mem[l + i];
        	lGrp[i] = grp[l + i];
        }
        for (int j = 0; j < n2; ++j) {
        	rMem[j] = mem[m + 1 + j];
        	rGrp[j] = grp[m + 1 + j];
        }
            
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (lMem[i] <= rMem[j]) {
                mem[k] = lMem[i];
                grp[k] = lGrp[i];
                i++;
            }
            else {
                mem[k] = rMem[j];
                grp[k] = rGrp[j];
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            mem[k] = lMem[i];
            grp[k] = lGrp[i];
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            mem[k] = rMem[j];
            grp[k] = rGrp[j];
            j++;
            k++;
        }
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    public static void sort(int members[], int[] groups, int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m =l+ (r-l)/2;
 
            // Sort first and second halves
            sort(members, groups, l, m);
            sort(members, groups, m + 1, r);
 
            // Merge the sorted halves
            merge(members, groups, l, m, r);
        }
    }
	
}





































