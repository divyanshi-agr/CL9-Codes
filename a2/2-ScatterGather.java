import mpi.MPI;
import java.io.File;
import java.io.FileWriter;

    public class ScatterGather {
   	 public static void main(String args[]){
	//Initialize MPI execution environment
   	 MPI.Init(args);
	//Get the id of the processes
   	 int rank = MPI.COMM_WORLD.Rank();
	//total number of processes is stored in size
   	 int size = MPI.COMM_WORLD.Size();
   	 int root=0;
	//array which will be filled with data by root process
   	 int sendbuf[]=null;

   	 sendbuf= new int[size];

	//creates data to be scattered
   	 if(rank==root){
   		 sendbuf[0] = 10;
   		 sendbuf[1] = 20;
   		 sendbuf[2] = 30;
   		 sendbuf[3] = 40;

   		 System.out.print("Processor "+rank+" has data: ");
   		 for(int i = 0; i < size; i++){
   			 System.out.print(sendbuf[i]+" ");
   		 }
   		 System.out.println();
   	 }
   	 int recvbuf[] = new int[1];
	

	//Operations
   	 MPI.COMM_WORLD.Scatter(sendbuf, 0, 1, MPI.INT, recvbuf, 0, 1, MPI.INT, root);
   	 System.out.println("Processor "+rank+" has data: "+recvbuf[0]);
   	 System.out.println("Processor "+rank+" is doubling the data");
   	 recvbuf[0]=recvbuf[0]*2;
	
   	 MPI.COMM_WORLD.Gather(recvbuf, 0, 1, MPI.INT, sendbuf, 0, 1, MPI.INT, root);
   	 if(rank==root){
   		System.out.println("Process 0 has data: ");
   		 for(int i=0;i<4;i++){
   			 System.out.print(sendbuf[i]+ " ");
   		 }
   	 }
   	 MPI.Finalize();
    }
}
//export MPJ_HOME=/home/sakshi/Downloads/mpj
//export PATH=$MPJ_HOME/bin:$PATH
//javac -cp $MPJ_HOME/lib/mpj.jar Average.java
//$MPJ_HOME/bin/mpjrun.sh -np 2 Average