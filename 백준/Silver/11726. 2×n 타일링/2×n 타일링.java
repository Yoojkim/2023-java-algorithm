import java.util.*;

class Main{
    static int n;
    static long[] d;

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        n=scanner.nextInt();
        d=new long[n+1];

        dp(n);

        System.out.print(d[n]);
    }

    private static long dp(int col){
        if(col==0)
            return 1;

        long cnt=0;
        if(col-1>=0){
            if(d[col-1]==0)
                d[col-1]=dp(col-1);
            cnt+=d[col-1];
        }

        if(col-2>=0){
            if(d[col-2]==0)
                d[col-2]=dp(col-2);
            cnt+=d[col-2];
        }

        return d[col]=cnt%10007;
    }
}