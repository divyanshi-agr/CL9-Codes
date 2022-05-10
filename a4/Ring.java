import java.io.*;
import java.util.*;

public class Ring {
    static Rr proc[];

    public static int elect(int startid, int numProc) {

        List<Integer> activeList = new ArrayList<Integer>();
        activeList.add(startid);

        int idx = (startid + 1)%numProc;
        
        while(idx != startid) {
            if(proc[idx].state == "active" && proc[(idx - 1 + numProc)%numProc].state == "active") {
                System.out.println("Message sent from process " + proc[(idx - 1 + numProc)%numProc].id + "to " + proc[idx].id);
            }

            if(proc[idx].state == "active") {
                activeList.add(idx);
            }

            idx = (idx + 1)%numProc;
        }

        int coord = startid;
        for (int num : activeList) {
            coord = Math.max(coord, num);
        }

        return proc[coord].id;
    }

    public static void main(String args[]) {
        int num_proc = 0;
        int startid, coordinator;

        // initialize proc obj
        proc = new Rr[10];
        for(int i = 0; i < proc.length; i++) {
            proc[i] = new Rr();
        }

        //scan to get no.of proc from user
        Scanner sc = new Scanner(System.in);
        num_proc = sc.nextInt();

        //get proc id's
        for(int i = 0; i < num_proc; i++){
            System.out.println("Enter processes's id : ");
            proc[i].id = sc.nextInt();
            proc[i].state = "active";
        }

        //sort the proc array based on id's
        for(int i = 0; i < num_proc-1 ; i++) {
            for(int j = 0; j < num_proc-1; j++) {
                if(proc[j].id > proc[j+1].id) {
                    int temp = proc[j].id;
                    proc[j].id =proc[j+1].id;
                    proc[j+1].id = temp;
                }
            }
        }

        //print sorted proc id array
        System.out.println("Sorted array is : ");
        for(int i = 0; i < num_proc; i++) {
            System.out.print(proc[i].id);
        }

        //call elect algo
        proc[num_proc - 1].state = "inactive";
        System.out.println("Start election with process id : ");
        startid = sc.nextInt();

        coordinator = elect(startid, num_proc);
        
        System.out.println("The new coordinator is : " + coordinator);

    }

}

class Rr {
    int id;
    String state;
}