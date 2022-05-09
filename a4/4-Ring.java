import java.io.*;
import java.util.*;

public class Ring {

    static Rr proc[];
    
    public static int elect(int pid, int numProc) {
        
        List<Integer> activeList = new ArrayList<Integer>();
        activeList.add(pid);

        int idx = (pid + 1)%numProc;
        while(idx != pid) {
            if(proc[idx].state == "active" && proc[(idx - 1 + numProc)%numProc].state == "active")
               System.out.println("Message sent from " + proc[(idx - 1 + numProc)%numProc].id + " to " + proc[idx].id);
            if(proc[idx].state == "active") {
                activeList.add(idx);
            }
            idx = (idx+1)%numProc;
        }

        int coordinator = pid;
        for(int proc : activeList) {
            coordinator = Math.max(coordinator, proc);
        }

        return proc[coordinator].id;
    }

	public static void main(String[] args) {

		int temp, i, j;
		char str[] = new char[10];
		
        proc = new Rr[10];
        // object initialisation
		for (i = 0; i < proc.length; i++)
			proc[i] = new Rr();

        // scanner used for getting input from console
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of process : ");
		int num = in.nextInt();

        // getting input from users
		for (i = 0; i < num; i++) {
			//proc[i].index = i;
			System.out.println("Enter the id of process : ");
			proc[i].id = in.nextInt();
			proc[i].state = "active";
		}


        // sorting the processes from on the basis of id
		for (i = 0; i < num - 1; i++) {
			for (j = 0; j < num - 1; j++) {
				if (proc[j].id > proc[j + 1].id) {
					temp = proc[j].id;
					proc[j].id = proc[j + 1].id;
					proc[j + 1].id = temp;
				}
			}
		}


		for (i = 0; i < num; i++) {
			System.out.print("  [" + i + "]" + " " + proc[i].id);
		}




		int init;
		int ch;
		int temp1;
		int temp2;
		int ch1;
		int arr[] = new int[10];

		proc[num - 1].state = "inactive";

		// System.out.println("\n process " + proc[num - 1].id + "select as co-ordinator");

		while (true) {
			System.out.println("\n 1.election 2.quit ");
			ch = in.nextInt();

			switch (ch) {
			case 1:
				System.out.println("\n Enter the Process number who initialsied election : ");
				init = in.nextInt();
                int crdntr = elect(init, num);
				System.out.println("Process " + crdntr + " selected as co-ordinator.");
				break;
			case 2:
            System.out.println("Program terminated ...");
            return ;
			default:
				System.out.println("\n invalid response \n");
				break;
			}


		}
	}

}

class Rr {

	//public int index;   // to store the index of process
	public int id;      // to store id/name of process
	String state;       // indiactes whether active or inactive state of node

}