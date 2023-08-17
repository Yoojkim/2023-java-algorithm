import java.util.*;
import java.io.*;

class Cal{
    int n; //숫자
    int[] nums;
    int max;
    int min;
    
    public Cal(int n, int[] nums){
        this.n=n;
        this.nums=nums;
        
        //max, min 초기화 
        min=Integer.MAX_VALUE;
        max=Integer.MIN_VALUE;
    }
    
    void calculate(int a, int s, int m, int d){
        int val=nums[0];
        
        util(val, 1, a, s, m, d);
    }
    
    void util(int val, int idx, int a, int s, int m, int d){
        //이번이 마지막 구간이면 
        if(idx==n-1){
            int res;
            
            if(a>0){
                res=val+nums[idx];
                compare(res);
            }
            
            if(s>0){
                res=val-nums[idx];
                compare(res);
            }
            
            if(m>0){
                res=val*nums[idx];
                compare(res);
            }
            
            if(d>0){
                res=val/nums[idx];
                compare(res);
            }
            
        }
            
        //+
        if(idx<n && a>0)
            util(val+nums[idx], idx+1, a-1, s, m, d);
        
        //-
        if(idx<n && s>0)
           util(val-nums[idx], idx+1, a, s-1, m, d);
        
        //*
        if(idx<n && m>0)
            util(val*nums[idx], idx+1, a, s, m-1, d);
        
        // /
        if(idx<n && d>0)
            util(val/nums[idx], idx+1, a, s, m, d-1);
    }
    
    void compare(int res){
        if(res>max)
            max=res;
        
        if(res<min)
            min=res;
    }
    
    void printRes(){
        System.out.println(max);
        System.out.println(min);
    }
}
public class Main{
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n=Integer.parseInt(br.readLine());
        int[] nums=new int[n];
        
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            int val=Integer.parseInt(st.nextToken());
            nums[i]=val;
        }
        
        st=new StringTokenizer(br.readLine());
        int a=Integer.parseInt(st.nextToken());
        int s=Integer.parseInt(st.nextToken());
        int m=Integer.parseInt(st.nextToken());
        int d=Integer.parseInt(st.nextToken());
        
        Cal cal=new Cal(n, nums);
        cal.calculate(a, s, m, d);
        cal.printRes();
    }
}