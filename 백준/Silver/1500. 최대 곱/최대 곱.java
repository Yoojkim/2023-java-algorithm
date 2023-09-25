import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);

        int s=scanner.nextInt(); int k=scanner.nextInt();

        long ans=1;

        while(k!=0){
            int val=s/k;
            ans*=val;

            k--; s-=val;
        }

        System.out.print(ans);
    }
}