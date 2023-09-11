import java.util.*;

class Main{
    static int[] d;
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        d=new int[n+1];

        dp(n);
        System.out.print(d[n]);
    }

    private static int dp(int num){
        if(num==1)
            return 0;

        int ans=Integer.MAX_VALUE;

        if(num%3==0){
            int temp;

            if(d[num/3]!=0)
                 temp=d[num/3]+1;
            else
                temp=dp(num/3)+1;

            if(ans>temp)
                ans=temp;
        }

        if(num%2==0){
            int temp;
            if(d[num/2]!=0)
                temp=d[num/2]+1;
            else
                temp=dp(num/2)+1;

            if(ans>temp)
                ans=temp;
        }

        int temp;
        if(d[num-1]!=0)
            temp=d[num-1]+1;
        else
            temp=dp(num-1)+1;

        if(ans>temp)
            ans=temp;

        return d[num]=ans;
    }
}