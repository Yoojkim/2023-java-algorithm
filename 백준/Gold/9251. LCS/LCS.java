import java.util.*;
import java.io.*;

public class Main{
    static String prev;
    static String now;
    static int size;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        prev = br.readLine();
        now = br.readLine();

        size = Math.max(prev.length(), now.length());
        int[][] dp = new int[size+1][size+1];

        for(int i=1;i<=size;i++){ //now
            for(int j=1;j<=size;j++){ //prev
                char prevChar = getChar(prev, j-1);
                char nowChar = getChar(now, i-1);

                if(prevChar == nowChar){
                    dp[i][j] = dp[i-1][j-1]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.print(dp[size][size]);
    }

    static char getChar(String str, int idx){
        if(str.length() <= idx){
            return ' ';
        }

        return str.charAt(idx);
    }
}