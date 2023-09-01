import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums=br.readLine().split(" ");
        int n=Integer.parseInt(nums[0]);
        int m=Integer.parseInt(nums[1]);
        
        LinkedList<Integer> queue=new LinkedList();
        LinkedList<Integer>[] nodes=new LinkedList[n+1];
        int[] degrees=new int[n+1];
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<=n;i++){
            nodes[i]=new LinkedList();
        }
        
        for(int i=0;i<m;i++){
            nums=br.readLine().split(" ");
            int s1=Integer.parseInt(nums[0]); int s2=Integer.parseInt(nums[1]);
            
            LinkedList<Integer> node=nodes[s1];
            node.addLast(s2);
            degrees[s2]=degrees[s2]+1;
        }
        
        //진입차수 0 탐색
        for(int i=1;i<=n;i++){
            if(degrees[i]==0)
                queue.addLast(i);
        }
        
        //본격적 수행
        while(queue.size()!=0){
            int node=queue.poll();
            sb.append(node).append(" ");
            LinkedList<Integer> nears=nodes[node];
            for(int near: nears){
                if(degrees[near]==1)
                    queue.addLast(near);
                
                degrees[near]=degrees[near]-1;
            }
        }
        
        System.out.println(sb);
    }
}