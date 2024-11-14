import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] optimal = new int[N][N];
        int[][] original = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                optimal[i][j] = Integer.parseInt(row[j]);
                original[i][j] = Integer.parseInt(row[j]);
            }
        }

        //플로이드-워셜
        for(int k=0;k<N;k++){
            for(int a=0;a<N;a++){
                for(int b=0;b<N;b++){
                    if(k==a || k==b){
                        continue;
                    }

                    int cost = optimal[a][k]+optimal[k][b];
                    if(cost < optimal[a][b]){
                        System.out.print(-1);
                        return;
                    }

                    if(cost == optimal[a][b]){
                        original[a][b]=0;
                        original[b][a]=0;
                    }
                }
            }
        }

        int sum=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sum+=original[i][j];
            }
        }

        System.out.print(sum/2);
    }
}
