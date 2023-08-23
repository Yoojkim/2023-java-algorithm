import java.util.*;
import java.io.*;

public class Main{
    static int max=Integer.MIN_VALUE;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int people=0;
        
        for(int i=0;i<10;i++){
            String[] nums=br.readLine().split(" ");
            int out=Integer.parseInt(nums[0]);
            int in=Integer.parseInt(nums[1]);
            people+=(in-out);
            
            if(people>max)
                max=people;
        }
        
        System.out.print(max);
    }
}