import java.util.*;
import java.io.*;

class Main{
    static int N, L, R;
    static int[][] fields;
    static int[][] dps = {
            {-1,0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        fields = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                fields[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        boolean move = true;
        int days = 0;
        while(move){
            move = day();

            if(move){
                days++;
            }
        }

        System.out.print(days);
    }

    static boolean day(){
        boolean[][] visited = new boolean[N][N];
        boolean nextDay = false;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(visited[i][j]){
                    continue;
                }

                //i, j가 기준이 됨
                Queue<int[]> queue = new LinkedList<>();
                List<int[]> history = new ArrayList<>();
                queue.add(new int[]{i, j});
                visited[i][j] = true;
                while(!queue.isEmpty()){
                    int[] next = queue.poll();
                    history.add(next);

                    //상하좌우
                    for(int[] dp:dps){
                        int newI = next[0]+dp[0];
                        int newJ = next[1]+dp[1];

                        if(newI<0 || newI>=N || newJ<0 ||newJ>=N){
                            continue;
                        }

                        if(visited[newI][newJ]){
                            continue;
                        }

                        int diff = Math.abs(fields[next[0]][next[1]]-fields[newI][newJ]);
                        if(!(diff >=L && diff<=R)){
                            continue;
                        }

                        visited[newI][newJ] = true;
                        queue.add(new int[]{newI, newJ});
                    }
                }

                if(history.size()==1){
                    continue;
                }

                nextDay = true;

                //distribute
                int sum=0;
                for(int[] h:history){
                    sum+=fields[h[0]][h[1]];
                }

                int each=sum/history.size();
                for(int[] h:history){
                    fields[h[0]][h[1]] = each;
                }
            }
        }

        return nextDay;
    }
}