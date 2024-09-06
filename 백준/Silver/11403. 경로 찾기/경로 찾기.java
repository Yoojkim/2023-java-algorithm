import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        
        boolean[][] dist = new boolean[N+1][N+1];
        
        StringTokenizer st;
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=N;j++){
                int available = Integer.parseInt(st.nextToken());
                
                if(available == 0){
                    continue;
                }
                
                dist[i][j] = true;
            }
        }
        
        //플로이드 와샬
        for(int k=1;k<=N;k++){
            for(int i=1;i<=N;i++){
                for(int j=1;j<=N;j++){
                    dist[i][j] = (dist[i][k]&&dist[k][j])||dist[i][j];
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(dist[i][j]){
                    sb.append(1).append(" ");
                    
                    continue;
                }
                sb.append(0).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
    }
}