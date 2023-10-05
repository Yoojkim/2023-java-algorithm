import java.util.*;
import java.io.*;

class Point{
    int x;
    int y;

    public Point(int x, int y){
        this.x=x;
        this.y=y;
    }
}

class Main{
    static int[][] dps={
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        int cnt=1;
        while(true){
            int n=Integer.parseInt(br.readLine());

            if(n==0)
                break;

            int arr[][]=new int[n][n];
            for(int i=0;i<n;i++){
                StringTokenizer st=new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    arr[i][j]=Integer.parseInt(st.nextToken());
                }
            }

            int ans=bfs(n, arr);
            sb.append("Problem ").append(cnt).append(": ").append(ans).append("\n");
            cnt++;
        }

        System.out.print(sb);
    }

    //visited 처리
    private static int bfs(int n, int[][] arr){

        LinkedList<Point> queue=new LinkedList<>();
        int[][] ans=new int[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                ans[i][j]=Integer.MAX_VALUE;
            }
        }

        queue.addLast(new Point(0, 0));
        ans[0][0]=arr[0][0];

        while(!queue.isEmpty()){
            Point now=queue.poll();

            for(int[] dp:dps){
                int newX=now.x+dp[0];
                int newY=now.y+dp[1];

                if(newX<0 || newX>=n || newY<0 || newY>=n)
                    continue;

                int newVal=ans[now.x][now.y]+arr[newX][newY];

                if(ans[newX][newY]<=newVal)
                    continue;

                ans[newX][newY]=newVal;

                queue.addLast(new Point(newX, newY));
            }
        }

        return ans[n-1][n-1];
    }
}