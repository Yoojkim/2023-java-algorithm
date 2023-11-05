import java.util.*;
import java.io.*;

//comparator 처리 해야되나? 기억이안남..
public class Main{
    private static PriorityQueue<Integer> queue=new PriorityQueue<>();
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n=Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<n;i++){
            int input=Integer.parseInt(br.readLine());
            
            if(input==0)
                sb.append(print()).append("\n");
            else
                add(input);
        }
        
        System.out.print(sb);
    }
    
    private static String print(){
        if(queue.isEmpty()){
            return "0";
        }
        
        return Integer.toString(queue.poll());
    }
    
    private static void add(int input){
        queue.add(input);
    }
}