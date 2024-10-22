import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] solutions = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            solutions[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N-1;

        int resultStartSolution =0;
        int resultEndSolution =0;

        long optimizedMix = Long.MAX_VALUE;

        while(start<end){
            int startSolution = solutions[start];
            int endSolution = solutions[end];

            long mix = startSolution + endSolution;

            if(Math.abs(mix) < optimizedMix){
                optimizedMix = Math.abs(mix);
                resultStartSolution = startSolution;
                resultEndSolution = endSolution;
            }

            if(mix < 0){
                start++;
                continue;
            }

            if(mix > 0){
                end--;
                continue;
            }

            break;
        }

        System.out.println(resultStartSolution + " " + resultEndSolution);
    }
}