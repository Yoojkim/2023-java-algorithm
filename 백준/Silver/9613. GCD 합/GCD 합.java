import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringJoiner sj = new StringJoiner("\n");
        while(T-->0){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int[] numbers = new int[N];

            for(int i=0;i<N;i++){
                numbers[i] = Integer.parseInt(st.nextToken());;
            }

            long sum =0;
            for(int i=0;i<N-1;i++){
                int a= numbers[i];

                for(int j=i+1;j<N;j++){
                    int b = numbers[j];

                    int gcd = getGCD(Math.max(a, b), Math.min(a,b));
                    sum+= gcd;
                }
            }

            sj.add(Long.toString(sum));
        }

        System.out.print(sj);
    }

    static int getGCD(int max, int min){
        while(min!=0){
            int remainder = max%min;

            max = min;
            min = remainder;
        }

        return max;
    }
}