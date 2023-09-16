import java.util.*;
import java.io.*;

class Stuff implements Comparable<Stuff>{
    int weight;
    int value;

    public Stuff(int weight, int value){
        this.weight=weight;
        this.value=value;
    }

    public int compareTo(Stuff stuff){
        return this.weight-stuff.weight;
    }
}

class Main{
    static int n, k;
    static int[][] d;
    static Stuff[] stuffs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        d = new int[n + 1][k + 1];
        stuffs = new Stuff[n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            stuffs[i] = (new Stuff(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        //dp
        for(int i=1;i<=n;i++){
            Stuff stuff=stuffs[i];

            for(int j=1;j<=k;j++){
                if(stuff.weight>j)
                    d[i][j]=d[i-1][j];
                else{
                    int inVal=stuff.value+d[i-1][j-stuff.weight];
                    int outVal=d[i-1][j];

                    int resVal=(inVal>outVal?inVal:outVal);
                    d[i][j]=resVal;
                }
            }
        }

        System.out.print(d[n][k]);
    }
}