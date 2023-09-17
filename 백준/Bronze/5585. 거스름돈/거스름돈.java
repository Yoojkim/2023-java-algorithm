import java.util.*;

class Main{
    
    static int[] coins={500, 100, 50, 10, 5, 1};
    
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        
        n=1000-n;
        int cnt=0;
        for(int coin:coins){
            if(n==0)
                break;
            
            cnt+=n/coin;
            n%=coin;
        }
        
        System.out.print(cnt);
    }
}