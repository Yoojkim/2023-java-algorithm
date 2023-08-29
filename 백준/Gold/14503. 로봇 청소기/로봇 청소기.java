import java.util.*;
import java.io.*;

public class Main{
    
    static int[][] fields;
    static int n, m;
    static int ans=0;
    
    //북동남서
    static int[][] displacements={
        {-1, 0}, {0, 1}, {1, 0}, {0, -1}
    };
    
    private static void dfs(int x, int y, int d){
        if(fields[x][y]==0){
            ans++;
            fields[x][y]=2; //clean
        }
        
        boolean nearNotClean=notCleanRoom(x, y);
        if(nearNotClean){
            int newD=(d-1<0?3:d-1);
            int newX=x+displacements[newD][0];
            int newY=y+displacements[newD][1];
            
            if(newX>=0 && newX<n && newY>=0 && newY<m && fields[newX][newY]==0){
                dfs(newX, newY, newD);
            } else{
                dfs(x, y, newD);
            }
            
        } else{
            //back
            int newD=(d+2)%4;
            int newX=x+displacements[newD][0];
            int newY=y+displacements[newD][1];
            
            if(newX<0 || newX>=n || newY<0 || newY>=m || fields[newX][newY]==1)
                return;
            
            dfs(newX, newY, d);
        }
    }
    
    private static boolean notCleanRoom(int x, int y){
        for(int[] dp:displacements){
            int newX=x+dp[0];
            int newY=y+dp[1];
            
            if(newX>=0 && newX<n && newY>=0 && newY<m && fields[newX][newY]==0)
                return true;
        }
        
        return false;
    } 
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] roomSize=br.readLine().split(" ");
        n=Integer.parseInt(roomSize[0]);
        m=Integer.parseInt(roomSize[1]);
        
        String[] conditions=br.readLine().split(" ");
        int r=Integer.parseInt(conditions[0]);
        int c=Integer.parseInt(conditions[1]);
        int d=Integer.parseInt(conditions[2]);
        
        fields=new int[n][m];
        
        for(int i=0;i<n;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                fields[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(r, c, d);
        System.out.print(ans);
    }
}