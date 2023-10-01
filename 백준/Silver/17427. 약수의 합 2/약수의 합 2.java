import java.util.*;

//O(n)
class Main{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);

        int n=scanner.nextInt();

        System.out.print(getAns(n));
    }

    private static long getAns(int n){
        long sum=0;

        for(int i=1;i<=n;i++){
            long div=n/i;
            sum+=div*i;
        }

        return sum;
    }
}