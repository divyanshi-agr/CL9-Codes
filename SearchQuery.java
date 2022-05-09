import java.rmi.*;
import java.rmi.server.*;

public class SearchQuery extends UnicastRemoteObject implements Search{
    public SearchQuery() throws RemoteException{
        super();
    }
     public String query(String search) throws RemoteException{
        String result=""; 
        if(search.equals("Hello"))
         {
            result = " found";
         }
         else{
            result = " not found";
         }
         return result;
     }
}
