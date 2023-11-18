import java.util.*;

public class Main{

    static List<Long> list =  new ArrayList<>();

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        for(int i=0;i<=9;i++){
            backTracking(0, i);
        }

        Collections.sort(list);

        long ans = -1;
        if(list.size()>=n+1){
            ans = list.get(n);
        }

        System.out.print(ans);
    }

    private static void backTracking(int depth, long num){
        list.add(num);

        for(int i=0;i<num%10;i++){
            backTracking(depth+1, num*10+i);
        }
    }
}