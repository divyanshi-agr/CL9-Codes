import mpi.MPI;
import java.io.File;
import java.io.FileWriter;

public class Double {
    public static void main(String args[]) {

        // initialize MPI execution env
        MPI.init(args);

         // get no. of processes by size
        int size = MPI.COMM_WORLD.Size();
        // get id of processes by rank
        int rank = MPI.COMM_WORLD.Rank();

        int root = 0;
        int sendbuf[] = new int[size];

       // initialize sendbuf array in root
       if(rank == root) {
           sendbuf[0] = 10;
           sendbuf[1] = 20;
           sendbuf[2] = 30;
           sendbuf[3] = 40;

           System.out.print("Processor " + rank + "has data : ");
           for(int i = 0; i < size; i++) {
               System.out.print(sendbuf[i] + "");
           }
           System.out.println();
       }

       int recvbuf[] = new int[1];

       //scatter
       MPI.COMM_WORLD.Scatter(sendbuf, 0, 1, MPI.INT, recvbuf, 0, 1, MPI.INT, root);
       System.out.print("Processor " + rank + "has data : " + recvbuf[0]);
       System.out.print("Processor " + rank + "is doubling the data");

       // operation
       recvbuf[0] = recvbuf[0]*2;

       //gather
       MPI.COMM_WORLD.Gather(recvbuf, 0, 1, MPI.INT, sendbuf, 0, 1, MPI.INT, root);
       if(rank == root){
           System.out.print("Processor 0 finally has data : ");
           for(int i = 0; i < size; i++){
               System.out.print(sendbuf[i] + " ");
           }
       }

       // end MPI execution
       MPI.Finalize();
       
    }
}