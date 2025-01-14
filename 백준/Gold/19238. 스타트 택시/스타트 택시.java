import java.util.*;
import java.io.*;

class Point{
    int i;
    int j;

    public Point(int i, int j){
        this.i=i;
        this.j=j;
    }
}

class Guest implements Comparable<Guest>{
    Point start;
    Point end;
    boolean success = false;
    int dist;

    public Guest(Point start, Point end){
        this.start = start;
        this.end = end;
    }

    void setDist(int dist){
        this.dist = dist;
    }

    public int compareTo(Guest g){
        if(this.dist<g.dist){
            return -1;
        } else if(this.dist>g.dist){
            return 1;
        } else {
            if(this.start.i<g.start.i){
                return -1;
            } else if(this.start.i>g.start.i){
                return 1;
            } else {
                return Integer.compare(this.start.j, g.start.j);
            }
        }
    }
}

public class Main{
    static int N, M, gas;
    static int[][] fields;
    static Point taxi;
    static List<Guest> guests = new ArrayList<>();
    static int[][] dps= {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    }; //상하좌우
    static boolean[][] impossible;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        gas = Integer.parseInt(st.nextToken());
        fields = new int[N][N];
        impossible = new boolean[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int field = Integer.parseInt(st.nextToken());

                if(field == 1){
                    impossible[i][j] = true;
                }

                fields[i][j] = field;
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Point(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            Point start = new Point(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);
            Point end = new Point(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1);

            guests.add(new Guest(start, end));
        }

        while(true){
            //남은 승객이 있는지?
            if(!getGuestPossible()){
                System.out.print(gas);

                return;
            }

            //운송 더 해야되는 경우
            Guest closeGuest = calCloseGuest();

            //가장 가까운 게스트까지 갈 수 있는지?
            if(closeGuest.dist == -1){
                break;
            }
            if(gas < closeGuest.dist){
                break;
            }

            gas-= closeGuest.dist;

            //현재 guest 위치에서 목적지까지 얼마나 걸리는지?
            int distStartToDest = calPointToPoint(closeGuest.start, closeGuest.end);
            if(distStartToDest == -1){
                break;
            }
            if(gas < distStartToDest){
                break;
            }

            taxi = closeGuest.end;
            gas+=distStartToDest;
            closeGuest.success=true;
        }

        System.out.print(-1); //impossible!
    }

    static boolean getGuestPossible(){
        for(Guest g:guests){
            if(!g.success){
                return true;
            }
        }

        return false;
    }

    static Guest calCloseGuest(){
        int[][] result = bfs(taxi);

        for(Guest g:guests){
            if(g.success){
                continue;
            }

            Point start = g.start;
            g.setDist(result[start.i][start.j]);
        }

        Collections.sort(guests);

        for(Guest g:guests){
            if(!g.success){
                return g;
            }
        }

        return null; //error!
    }

    static int calPointToPoint(Point start, Point end){
        int[][] result = bfs(start);

        return result[end.i][end.j];
    }

    static int[][] bfs(Point start){
        //start ~ 모든 배열까지의 거리
        int[][] result = new int[N][N];
        for(int i=0;i<N;i++){
            Arrays.fill(result[i], -1);
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        result[start.i][start.j] = 0;

        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point now = queue.poll();
                int dist = result[now.i][now.j];

                for (int[] dp : dps) {
                    int newI = now.i + dp[0];
                    int newJ = now.j + dp[1];

                    if (newI < 0 || newI >= N || newJ < 0 || newJ >= N) {
                        continue;
                    }

                    if (result[newI][newJ] >= 0) {
                        continue;
                    }

                    //벽인 경우
                    if(impossible[newI][newJ]){
                        continue;
                    }

                    result[newI][newJ] = dist+1;
                    queue.add(new Point(newI, newJ));
                }
            }
        }

        return result;
    }
}