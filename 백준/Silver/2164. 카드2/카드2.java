import java.util.*;

class Main{
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int n=scanner.nextInt();
        
        LinkedList<Integer> queue=new LinkedList();
        for(int i=1;i<=n;i++){
            queue.addLast(i);
        }
        
        while(queue.size()!=1){
            queue.poll();
            int last=queue.poll();
            queue.addLast(last);
        }
        
        System.out.print(queue.poll());
    }
}