import java.util.*;
import java.io.*;

class Coord implements Comparable<Coord>{
    int x;
    int y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Coord c){
        if(this.x<c.x)
            return -1;
        else if(this.x==c.x){
            return this.y-c.y;
        }
        else
            return 1;
    }
}

class Main {
    static int n;
    static int[][] sea;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        sea = new int[n][n];

        StringTokenizer st;
        Coord start = null;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(st.nextToken());
                sea[i][j] = num;

                if (num == 9)
                    start = new Coord(i, j);

            }
        }

        System.out.print(bfs(start));
    }

    static int[][] dps = {
            {-1, 0}, {0, -1}, {1, 0}, {0, 1}
    };

    private static int bfs(Coord sharkCoord){
        LinkedList<Coord> queue=new LinkedList<>();
        boolean[][] visited=new boolean[n][n];

        queue.addLast(sharkCoord);
        sea[sharkCoord.x][sharkCoord.y]=0;
        visited[sharkCoord.x][sharkCoord.y]=true;

        int sharkSize=2;
        int sharkAte=0;

        int cnt=0;
        int sumCnt=0;

        PriorityQueue<Coord> canEat=new PriorityQueue<>();

        while(!queue.isEmpty()){
            cnt++;

            if(sharkAte==sharkSize) {
                sharkAte = 0;
                sharkSize++;
            }

            int queueSize= queue.size();
            for(int i=0;i<queueSize;i++){
                Coord now = queue.poll();

                for(int[] dp:dps){
                    int newX = now.x + dp[0];
                    int newY = now.y + dp[1];

                    if (newX < 0 || newX >= n || newY < 0 || newY >= n)
                        continue;

                    if (visited[newX][newY] || sea[newX][newY] > sharkSize)
                        continue;

                    visited[newX][newY] = true;
                    Coord newCoord=new Coord(newX, newY);
                    queue.addLast(newCoord);

                    //먹을 수 있으면
                    if (sea[newX][newY]>0 && sea[newX][newY]<sharkSize)
                        canEat.add(newCoord);

                }
            }

            if(!canEat.isEmpty()){
                Coord fish=canEat.poll();

                queue.clear();
                canEat.clear();
                visited=new boolean[n][n];

                queue.add(fish);
                visited[fish.x][fish.y]=true;
                sea[fish.x][fish.y]=0;
                sharkAte++;
                sumCnt+=cnt;

                cnt=0;
            }
        }

        return sumCnt;
    }
}