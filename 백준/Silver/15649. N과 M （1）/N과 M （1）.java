import java.io.*;
import java.util.*;

public class Main{
    static int N;
    static int M;
    static StringJoiner mainSj = new StringJoiner("\n");
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] values = br.readLine().split(" ");
        
        N = Integer.parseInt(values[0]);
        M = Integer.parseInt(values[1]);
        visited = new boolean[N+1];
        
        int[] ans = new int[M];
        backTracking(0, ans);
        System.out.print(mainSj);
    }
    
    public static void backTracking(int depth, int[] ans){
        if(depth == M){
            StringJoiner sj = new StringJoiner(" ");
            for(int val:ans){
                sj.add(Integer.toString(val));
            }
            
            mainSj.add(sj.toString());
            
            return;
        }
        
        for(int i=1;i<=N;i++){
            if(visited[i]){
                continue;
            }
            
            visited[i]= true;
            
            ans[depth] = i;
            backTracking(depth+1, ans);
            
            visited[i]= false;
        }
    }
}