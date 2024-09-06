import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] inputs = br.readLine().split(" ");

        int N = Integer.parseInt(inputs[0]);
        int H = Integer.parseInt(inputs[1]);

        int[] tops = new int[H+1];
        int[] bottoms = new int[H+1];

        for(int i=1;i<=N/2;i++){
            int bottom = Integer.parseInt(br.readLine());
            int top = Integer.parseInt(br.readLine());

            tops[top]++;
            bottoms[bottom]++;
        }

        //누적합 계산
        for(int i=H-1;i>0;i--){
            tops[i] = tops[i+1] + tops[i];
        }

        for(int i=H-1;i>0;i--){
            bottoms[i] = bottoms[i+1]+bottoms[i];
        }

        int min = Integer.MAX_VALUE; int cnt =0;
        for(int i=1;i<=H;i++){
            int res = bottoms[i] + tops[H-i+1];
            if(res > min){
                continue;
            }

            if(res < min){
                min = res;
                cnt = 0;
            }

            cnt++;
        }

        System.out.print(min+" "+cnt);
    }
}