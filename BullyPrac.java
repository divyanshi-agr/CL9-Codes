import java.io.InputStream;
import java.io.PrintStream;


public class BullyPrac {

    static int num_proc = 6;
    static boolean state[] = new boolean[num_proc];
    static int coordinator = 0;

    public static void elect(int startid) {
        int temp_coord = startid;
        int i = startid;
        int j = i + 1;

        for(i = startid; i < num_proc; i++) {
            for(j = i + 1; j < num_proc ; j++) {
                if(state[i] == true) {
                    System.out.println("Message sent from " + i + " to " + j);
                }
            }

            for(j = i + 1; j < num_proc; j++) {
                if(state[i] == true && state[j] == true) {
                    System.out.println("OK Message sent from " + j + " to " + i);
                    temp_coord = j;
                }
            }
        }

        coordinator = temp_coord;
        System.out.println("Coordinator is : " + coordinator);
    }

    public static void bringUp(int proc_id) {
        state[proc_id] = true;
        elect(proc_id);
    }

    public static void bringDown(int proc_id) {
        state[proc_id] = false;
    }

    public static void main(String args[]) {
        for (int i = 0; i < BullyPrac.num_proc; i++) {
            BullyPrac.state[i] = true;
        }

        System.out.println("5 active processes are: ");
        System.out.println("p1 p2 p3 p4 p5");
        System.out.println("Process 5 is coordinator");
        BullyPrac.bringDown(5);
        BullyPrac.bringDown(4);
        BullyPrac.elect(2);
        BullyPrac.bringUp(5);
        BullyPrac.bringUp(4);
    }
}
