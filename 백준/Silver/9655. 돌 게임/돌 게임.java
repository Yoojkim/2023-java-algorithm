import java.util.*;

class Main{
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int n= scanner.nextInt();

        //상근-홀, 창영-짝
        int cnt=0;
        while(true){
            if (n==0)
                break;

            if(n>=3)
                n-=3;
            else
                n-=1;
            cnt++;
        }

        if(cnt%2==1)
            System.out.print("SK");
        else
            System.out.println("CY");
    }
}