import java.util.*;
import java.io.*;

public class Main{

    static int[][] dps={
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int m=Integer.parseInt(st.nextToken()); int n=Integer.parseInt(st.nextToken());
        int[][] tomatos=new int[n][m];

        int nonRipe=0;
        LinkedList<int[]> queue=new LinkedList();
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                int tomato=Integer.parseInt(st.nextToken());
                tomatos[i][j]=tomato;

                if(tomato==0)
                    nonRipe++;

                if(tomato==1){
                    int[] point={i, j};
                    queue.addLast(point);
                }
            }
        }

        int ans=0;
        if(nonRipe!=0){
            ans=getMinDays(n, m, tomatos, queue, nonRipe);
        }

        System.out.print(ans);
    }

    private static int getMinDays(int n, int m, int[][] tomatos, LinkedList<int[]> queue, int nonRipe){
        int days=0; int ripeCnt=0;

        while(!queue.isEmpty()){

            //모든 queue tomato 빼기
            List<int[]> tempTomatos=new ArrayList(queue);
            queue.clear();

            boolean ripe=false;
            for(int[] tomato:tempTomatos){
                //상, 하, 좌, 우 + 안 익음
                for(int[] dp:dps){
                    int newI=dp[0]+tomato[0];
                    int newJ=dp[1]+tomato[1];

                    if(newI<0 || newI>=n || newJ<0 || newJ>=m || tomatos[newI][newJ]!=0)
                        continue;

                    int[] newPoint={newI, newJ};
                    queue.addLast(newPoint);

                    tomatos[newI][newJ]=1;
                    ripeCnt++;
                    ripe=true;
                }
            }

            if(ripe)
                days++;
        }

        if(nonRipe!=ripeCnt)
            days=-1;

        return days;
    }
}