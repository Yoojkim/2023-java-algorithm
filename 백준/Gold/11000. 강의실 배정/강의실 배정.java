import java.util.*;
import java.io.*;

class Lesson{
    int s;
    int t;

    public Lesson(int s, int t){
        this.s=s;
        this.t=t;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Lesson> lessons = new ArrayList<>();
        while(N-->0){
            String[] values = br.readLine().split(" ");

            int s = Integer.parseInt(values[0]);
            int t = Integer.parseInt(values[1]);

            lessons.add(new Lesson(s, t));
        }

        Collections.sort(lessons, (Lesson l1, Lesson l2)-> Integer.compare(l1.s,l2.s));

        PriorityQueue<Lesson> queue = new PriorityQueue<>(
                (Lesson l1, Lesson l2) -> Integer.compare(l1.t, l2.t)
        );

        for(int i=0;i<lessons.size();i++){
            Lesson nowLesson = lessons.get(i);
            if(queue.isEmpty()){
                queue.add(nowLesson);
                continue;
            }

            Lesson queueLesson = queue.peek();

            if(queueLesson.t > nowLesson.s){
                queue.add(nowLesson);
                continue;
            }

            queue.poll();
            queue.add(nowLesson);
        }

        System.out.print(queue.size());
    }
}