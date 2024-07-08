import java.io.*;
import java.util.*;

public class Main{
    static int N;
    static int M;
    static StringJoiner mainSj = new StringJoiner("\n");
    static Stack<Integer> stack = new Stack<>();
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] values = br.readLine().split(" ");
        
        N = Integer.parseInt(values[0]);
        M = Integer.parseInt(values[1]);
        visited = new boolean[N+1];
        
        
        backTracking(1);
        System.out.print(mainSj);
    }
    
    public static void backTracking(int depth){
        if(depth == M+1){
            StringJoiner sj = new StringJoiner(" ");
            for(int val:stack){
                sj.add(Integer.toString(val));
            }
            
            mainSj.add(sj.toString());
            
            return;
        }
        
        for(int i=1;i<=N;i++){
            if(visited[i]){
                continue;
            }
            
            stack.push(i);
            visited[i]= true;
            
            backTracking(depth+1);
            
            stack.pop();
            visited[i]= false;
        }
    }
}