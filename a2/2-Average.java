import mpi.MPI;

public class Average {
    public static void main(String args[]){
        MPI.Init(args);
        
        int size = MPI.COMM_WORLD.Size();
        int rank = MPI.COMM_WORLD.Rank();

        int root=0;
        int sendbuf[] = new int[4];
        int chunkSize = 4/size;
        double temp_avg[] = new double[size];

        if(rank == root)
        {
            sendbuf[0]=10;
            sendbuf[1]=50;
            sendbuf[2]=100;
            sendbuf[3]=500;

            System.out.print("Processor "+rank+" has data: ");
            for(int i = 0; i < size; i++){
                System.out.print(sendbuf[i]+" ");
            }
   		    System.out.println();
        }
        int recvbuf[] = new int[chunkSize];
        double avg[] = new double[1];

        MPI.COMM_WORLD.Scatter(sendbuf,0,chunkSize,MPI.INT,recvbuf,0,chunkSize,MPI.INT,root);
        System.out.println("Processor "+rank+" has data: "+recvbuf[0]);
   	    System.out.println("Processor "+rank+" is doubling the data");
        
        //operation
        for(int num : recvbuf)
        {
            avg[0]+=num;
        }
        avg[0]/= recvbuf.length;

        MPI.COMM_WORLD.Gather(avg,0,1,MPI.DOUBLE,temp_avg,0,1,MPI.DOUBLE,root);
        if(rank==root){
            System.out.println("Process 0 has data: ");
            double final_avg = 0;
             for(double num: temp_avg){
                 System.out.print(num+ " ");
                 final_avg+=num;
             }
             final_avg/=temp_avg.length;
             System.out.println("\n Average = "+final_avg);
         }

         MPI.Finalize();

    }
}
