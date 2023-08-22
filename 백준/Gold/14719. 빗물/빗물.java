import java.util.*;
import java.io.*;

public class Main{

    private static int rain(int[] ws){
        
        int result=0;
        
        for(int i=1;i<ws.length-1;i++){
            int leftMax=getMaxLeft(ws, i);
            int rightMax=getMaxRight(ws, i);
            
            int maxRain=leftMax;
            if(maxRain>rightMax)
                maxRain=rightMax;
            
            int rain=maxRain-ws[i];
            if(rain>0)
                result+=rain;
        }
        
        return result;
    }
    
    private static int getMaxLeft(int[] ws, int idx){
        //idx 포함하여 제일 큰 left idx 
        int val=ws[idx];
        for(int i=idx; i>=0; i--){
            if (val<ws[i])
                val=ws[i];
        }
        
        return val;
    }
    
    private static int getMaxRight(int[] ws, int idx){
        //idx 포함하여 제일 큰 right idx
        int val=ws[idx];
        for(int i=idx;i<ws.length;i++){
            if(val<ws[i])
                val=ws[i];
        }
        
        return val;
    }



    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int h=Integer.parseInt(st.nextToken());
        int w=Integer.parseInt(st.nextToken());

        int[] ws=new int[w];
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<w;i++){
            ws[i]=Integer.parseInt(st.nextToken());
        }

        System.out.println(rain(ws));
    }
}