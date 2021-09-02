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
		groupCount = scan.nextInt();
		
		// groupCount*2 as there needs to be at least one member per group followed by a 0
		ArrayList<Integer> members = new ArrayList<Integer>(groupCount*2);
		ArrayList<Integer> memGroup = new ArrayList<Integer>(groupCount*2);
		
		//Ask the members belonging to each group
		System.out.println("Enter the list of members of each group");
		
		//Collecting all the members
		int grp = 1;
		while(grp <= groupCount) {
			Integer temp = scan.nextInt();
			if(temp != 0) {
				members.add(temp);
				memGroup.add(grp);
			} else {
				grp++;
			}
		}
		
		long start = System.currentTimeMillis();
		
		//Merge sort the two list in sync.
		// O(nlogn)
		sort(members, memGroup, 0, members.size()-1);
		
		//Close Scanner
		scan.close();
		
		//Run pickMembers then remove members until ArrayList is empty
		ArrayList<Integer> selectedMember = new ArrayList<Integer>();
		while(!isAllZeros(members)) {
			int picked = pickMembers(members, memGroup);
			selectedMember.add(picked);
			
			//System.out.println("Picked " + picked);
			
			removeElements(members, memGroup, picked);
		}
		
		long end = System.currentTimeMillis();
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
			if(curMem == 0) {
				curMem = mem.get(i);
			} else if(mem.get(i) != 0) {
				if(curMem == mem.get(i)) {
					currentStreak++;
				}
				if(currentStreak>longestStreak) {
					longestStreak = currentStreak;
					winnerMem = curMem;
				}
				if(curMem != mem.get(i)) {
					curMem = mem.get(i);
					currentStreak = 1;
				}
			}
		}
		
		return winnerMem;
	}
	
	public static Boolean isAllZeros(ArrayList<Integer> mem) {
		
		for(Integer m : mem) {
			if(m != 0) return false;
		}
		
		return true;
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
					mem.set(i, 0);
					groups.set(i, 0);
					break;
				}
			}
		}
		
	}
	
    public static void merge(ArrayList<Integer> mem, ArrayList<Integer> grp, int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
 
        /* Create temp arrays */
        Integer lMem[] = new Integer[n1];
        Integer lGrp[] = new Integer[n1];
        Integer rMem[] = new Integer[n2];
        Integer rGrp[] = new Integer[n2];
 
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) {
        	lMem[i] = mem.get(l + i);
        	lGrp[i] = grp.get(l + i);
        }
        for (int j = 0; j < n2; ++j) {
        	rMem[j] = mem.get(m + 1 + j);
        	rGrp[j] = grp.get(m + 1 + j);
        }
            
 
        /* Merge the temp arrays */
 
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
 
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (lMem[i] <= rMem[j]) {
            	mem.set(k, lMem[i]);
                grp.set(k, lGrp[i]);
                i++;
            }
            else {
                mem.set(k, rMem[j]);
                grp.set(k, rGrp[j]);
                j++;
            }
            k++;
        }
 
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            mem.set(k, lMem[i]);
            grp.set(k, lGrp[i]);
            i++;
            k++;
        }
 
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            mem.set(k, rMem[j]);
            grp.set(k, rGrp[j]);
            j++;
            k++;
        }
    }
 
    // Main function that sorts arr[l..r] using
    // merge()
    public static void sort(ArrayList<Integer> members, ArrayList<Integer> groups, int l, int r)
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





































