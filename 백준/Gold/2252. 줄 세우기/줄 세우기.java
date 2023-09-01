import java.util.*;
import java.io.*;

//DFS로 짜보기

public class Main{
    static Stack<Integer> stack=new Stack(); 
    static LinkedList<Integer>[] nodes;
    static boolean[] visited;
    static StringBuilder sb=new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums=br.readLine().split(" ");
        int n=Integer.parseInt(nums[0]);
        int m=Integer.parseInt(nums[1]);
        
        nodes=new LinkedList[n+1];
        visited=new boolean[n+1];
        for(int i=0;i<=n;i++){
            nodes[i]=new LinkedList();
        }
        
        for(int i=0;i<m;i++){
            nums=br.readLine().split(" ");
            int s1=Integer.parseInt(nums[0]); int s2=Integer.parseInt(nums[1]);
            
            LinkedList<Integer> node=nodes[s1];
            node.addLast(s2);
        }
        
        for(int i=1;i<=n;i++){
            if(!visited[i])
                dfsUtil(i);
        }
        
        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        
        System.out.println(sb);
    }
    
    private static void dfsUtil(int s){
        visited[s]=true;
        
        LinkedList<Integer> nears=nodes[s];
        for(int near:nears){
            if(!visited[near])
                dfsUtil(near);
        }
        
        stack.push(s);
    }
}