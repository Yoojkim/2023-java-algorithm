import java.util.*;
import java.io.*;

public class Main{
    //상하좌우
    static int[][] dps = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    static int R;
    static int C;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");

        R = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);

        char[][] fields = new char[R][C];


        for(int i=0;i<R;i++){
            char[] row = br.readLine().toCharArray();
            for(int j=0;j<C;j++){
                fields[i][j] = row[j];
            }
        }

        //BFS (물 먼저, 다음 고슴도치 방문)
        Queue<int[]> waters = new LinkedList<>();
        //초기화
        int[] D = null;
        int[] S = null;
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(fields[i][j] == '*'){
                    waters.add(new int[]{i, j});
                    continue;
                }

                if(fields[i][j] == 'D'){
                    D = new int[]{i, j};
                    continue;
                }

                if(fields[i][j] == 'S'){
                    S = new int[]{i, j};
                    continue;
                }
            }
        }

        Queue<int[]> starts = new LinkedList<>();
        starts.add(S);
        fields[S[0]][S[1]] = 'V';
        int turn=0;

        while(!starts.isEmpty()){
            turn++;
            waters = turnWater(waters, fields);
            starts = turnStart(starts, fields);

            if(fields[D[0]][D[1]] == 'V'){
                System.out.println(turn);
                return;
            }
        }

        System.out.println("KAKTUS");
    }

    static Queue<int[]> turnStart(Queue<int[]> starts, char[][] fields){
        Queue<int[]> newQueue = new LinkedList<>();

        while(!starts.isEmpty()){
            int[] now = starts.poll();

            for(int[] dp:dps){
                int newX = now[0]+dp[0];
                int newY = now[1]+dp[1];

                if(newX <  0 || newX >=R || newY <0 || newY>=C){
                    continue;
                }

                char field = fields[newX][newY];
                if(field != '.' && field !='D'){
                    continue;
                }

                newQueue.add(new int[]{newX, newY});
                fields[newX][newY] = 'V';
            }
        }

        return newQueue;
    }

    static Queue<int[]> turnWater(Queue<int[]> waters, char[][] fields){
        //한 턴씩 물이 차오르도록

        Queue<int[]> newQueue = new LinkedList<>();

        while(!waters.isEmpty()){
            int[] now = waters.poll();

            for(int[] dp:dps){
                int newX = now[0]+dp[0];
                int newY = now[1]+dp[1];

                if(newX <  0 || newX >=R || newY <0 || newY>=C){
                    continue;
                }

                if(fields[newX][newY] != '.'){
                    continue;
                }

                fields[newX][newY] = 'X';
                newQueue.add(new int[]{newX, newY});
            }
        }

        return newQueue;
    }
}