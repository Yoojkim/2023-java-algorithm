import java.util.*;
import java.io.*;

public class Main{

    static int n, m;
    static char[][] fields;
    static int ans=0;

    private static void dfsRow(int x, int y, boolean before){
        if(x==n)
            return;

        if(y==m){
            dfsRow(x+1, 0, false);
            return;
        }

        if(fields[x][y]=='-'){
            if(!before)
                ans++;
            dfsRow(x, y+1, true);
        }else{
            dfsRow(x, y+1, false);
        }
    }

    private static void dfsCol(int x, int y, boolean before){
        if(y==m)
            return;

        if(x==n){
            dfsCol(0, y+1, false);
            return;
        }

        if(fields[x][y]=='|'){
            if(!before)
                ans++;
            dfsCol(x+1, y, true);
        }else{
            dfsCol(x+1, y, false);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nums=br.readLine().split(" ");
        n=Integer.parseInt(nums[0]);
        m=Integer.parseInt(nums[1]);

        fields=new char[n][m];
        for(int i=0;i<n;i++){
            String newLine=br.readLine();
            char[] newRow=newLine.toCharArray();
            for(int j=0;j<m;j++){
                fields[i][j]=newRow[j];
            }
        }

        dfsRow(0, 0, false);
        dfsCol(0, 0, false);

        System.out.print(ans);
    }
}