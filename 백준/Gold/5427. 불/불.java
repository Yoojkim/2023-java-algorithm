import java.io.*;
import java.util.*;

class Point{
    int i;
    int j;
    int sec;
    boolean people;

    public Point(int i, int j, int sec, boolean people){
        this.i=i;
        this.j=j;
        this.sec=sec;
        this.people=people;
    }
}

public class Main{

    static int[][] dps={
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        for(int c=0;c<tc;c++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w=Integer.parseInt(st.nextToken());
            int h=Integer.parseInt(st.nextToken());
            char[][] bd=new char[h][w];

            LinkedList<Point> queue=new LinkedList();
            List<Point> peopleList=new ArrayList();
            for(int i=0;i<h;i++){
                char[] row=br.readLine().toCharArray();
                for(int j=0;j<w;j++){
                    bd[i][j]=row[j];

                    if(row[j]=='@')
                        peopleList.add(new Point(i, j, 0, true));
                    if(row[j]=='*')
                        queue.addLast(new Point(i, j, 0, false));
                }
            }

            for(Point p:peopleList){
                queue.addLast(p);
            }

            int ans=getSeconds(queue, h, w, bd);
            if(ans<0)
                System.out.println("IMPOSSIBLE");
            else
                System.out.println(ans);
        }
    }

    private static int getSeconds(LinkedList<Point> queue, int n, int m, char[][] bd){
        while(!queue.isEmpty()){
            Point now=queue.poll();
            int sec=now.sec;

            for(int[] dp:dps){
                int newI=dp[0]+now.i;
                int newJ=dp[1]+now.j;

                if(!now.people){
                    if(newI<0 || newI>=n || newJ<0 || newJ>=m || bd[newI][newJ]=='#' || bd[newI][newJ]=='*')
                        continue;

                    queue.addLast(new Point(newI, newJ, sec+1, false));
                    bd[newI][newJ]='*';
                } else{
                    if(newI<0 || newI>=n || newJ<0 || newJ>=m)
                        return sec+1;

                    if(bd[newI][newJ]=='.') {
                        queue.addLast(new Point(newI, newJ, sec + 1, true));
                        bd[now.i][now.j]='*'; //visited 처리
                        bd[newI][newJ]='*'; //visited 처리 
                    }
                }
            }
        }
        return -1;
    }
}