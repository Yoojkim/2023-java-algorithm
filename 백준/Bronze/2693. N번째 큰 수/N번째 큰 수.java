import java.util.*;
import java.io.*;

public class Main{
    
    public static void cal(int[] arr){
        LinkedList<Integer> ans=new LinkedList();
        for(int i:arr){
            int idx=0;
            for(;idx<ans.size();idx++){
                if(idx==3) break;
                
                if(ans.get(idx)<i)
                    break;
            }
            
            if(idx<3)
                ans.add(idx, i);
        }
        
        System.out.println(ans.get(2));
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n=Integer.parseInt(br.readLine());
        
        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            int[] arr=new int[10];
            
            for(int j=0;j<10;j++){
                arr[j]=Integer.parseInt(st.nextToken());
            }
            
            cal(arr);
        }
    }
}