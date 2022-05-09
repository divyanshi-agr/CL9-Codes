import java.rmi.*;
import java.util.*;

public class SearchClient {
    public static void main(String args[])
    {
        try
        {
            String ans, value ="Hello";
            Search access = (Search) Naming.lookup("rmi://localhost:1900/myServer");

            ans = access.query(value);
            System.out.println("Article named " + value + " " + ans + " at myServer");

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
