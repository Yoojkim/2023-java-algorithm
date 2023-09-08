import java.util.*;

class Main{

    static String[] bases="baby sukhwan tururu turu very cute tururu turu in bed tururu turu baby sukhwan".split(" ");

    public static void main(String args[]){
        Scanner scanner =new Scanner(System.in);
        int n=scanner.nextInt();
        int basesSize=bases.length;

        int quotient=n/basesSize;
        int remainder=n%basesSize;
        remainder=(remainder!=0?remainder:basesSize);

        String base=bases[remainder-1];
        int cnt=0;
        for(int i=0;i<base.length()-1;i++){
            if(base.charAt(i)=='r' && base.charAt(i+1)=='u'){
                i++; cnt++;
            }
        }


        cnt=(cnt!=0?cnt+quotient:cnt);

        if(cnt==0){
            System.out.print(base);
        } else if(cnt<5){
            System.out.print(base);
            for(int i=0;i<quotient;i++)
            System.out.print("ru");
        } else{
            System.out.print("tu+ru*"+cnt);
        }

    }
}