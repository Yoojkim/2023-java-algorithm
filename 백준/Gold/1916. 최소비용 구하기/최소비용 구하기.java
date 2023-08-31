import java.util.*;
import java.io.*;

/**
가중치가 있으니까 bfs로 구현할 수 없어 dfs로 구현하기로 결정
양방향 아님 

현재 문제상황: 스케쥴을 어떻게 구조화할지 
*/

public class Main{
    
    static LinkedList<int[]>[] schedule; //선언형 ㄱㅊ은지?
    static int n;
    
    private static int calAs(int start, int end){
        int[] table=new int[n+1];
        boolean[] visited=new boolean[n+1];
        
        for(int i=0;i<=n;i++)
            table[i]=Integer.MAX_VALUE;
        
        table[start]=0;
        
        while(start>0){
            //중복 계산 안하도록 visited 사용
            visited[start]=true;
            
            LinkedList<int[]> nears=schedule[start];
            
            for(int[] near:nears){
                int newCity=near[0];
                int cost=table[start]+near[1];
                
                if(table[newCity]>cost)
                    table[newCity]=cost;
            }
            
            //이후 start 명명
            int min=Integer.MAX_VALUE;
            for(int i=0;i<=n;i++){
                if(min>table[i] && !visited[i]){
                    min=table[i];
                    start=i;
                }
            }
            
            start=(min==Integer.MAX_VALUE)?-1:start;
        }
        
        return table[end];
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());
        int m=Integer.parseInt(br.readLine());
        
        schedule=new LinkedList[n+1];
        for(int i=0;i<=n;i++){
            schedule[i]=new LinkedList<int[]>();
        }
        
        for(int i=0;i<m;i++){
            String[] nums=br.readLine().split(" ");
            int s=Integer.parseInt(nums[0]);
            int e=Integer.parseInt(nums[1]);
            int c=Integer.parseInt(nums[2]);
            
            LinkedList<int[]> sList=schedule[s];
            int[] newNear={e, c};
            sList.addLast(newNear);
        }
        
        String[] nums=br.readLine().split(" ");
        int s=Integer.parseInt(nums[0]);
        int e=Integer.parseInt(nums[1]);
        
        System.out.print(calAs(s, e));
    }
}