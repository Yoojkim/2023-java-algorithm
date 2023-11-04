import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size=Integer.parseInt(br.readLine());
        
        int[] s=new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<size;i++){
            s[i]=Integer.parseInt(st.nextToken());
        }
        
        boolean[] visited=new boolean[100000*20+1];
        
        backTracking(s, visited, 0, 0, size);
        System.out.println(getMinNumber(visited));
    }
    
    private static void backTracking(int[] s, boolean[] visited, int index, int sum, int size){
        if(index==size){
            return;
        }
        
        visited[sum+s[index]]=true;
        
        backTracking(s, visited, index+1, sum, size);
        backTracking(s, visited, index+1, sum+s[index], size);
    }
    
    private static int getMinNumber(boolean[] visited){
        int res=-1;
        
        for(int i=1;i<=100000*20;i++){
           if(!visited[i]){
               res=i;
               break;
           }
        }            
        
        return res;
    }
}