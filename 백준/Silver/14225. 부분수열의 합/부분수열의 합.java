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
        
        List<Integer> result=new ArrayList<>();
        backTracking(s, result, 0, 0, size);
        System.out.println(getMinNumber(result));
    }
    
    private static void backTracking(int[] s, List<Integer> result, int index, int sum, int size){
        if(index==size){
            return;
        }
        
        result.add(sum+s[index]);
        
        backTracking(s, result, index+1, sum, size);
        backTracking(s, result, index+1, sum+s[index], size);
    }
    
    private static int getMinNumber(List<Integer> result){
        Collections.sort(result);
        
        int start=1;
        for(int sum:result){
            if(sum>start){
                break;
            }
        
            if(sum==start){
                start++;
            }
        }
        
        return start;
    }
}