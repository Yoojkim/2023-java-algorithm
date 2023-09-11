import java.util.*;

class Main{
    static long[] p;

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        p=new long[n+1];

        long ans=pibo(n);

        System.out.print(ans);
    }

    private static long pibo(int num){
        if(num==1) return 1;
        if(num==2) return 1;
        if(p[num]!=0) return p[num];

        return p[num]=pibo(num-1)+pibo(num-2);
    }
}