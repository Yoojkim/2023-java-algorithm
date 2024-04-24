import java.util.*;
import java.io.*;

class Result{
    int strike;
    int ball;

    public Result(int strike, int ball){
        this.strike = strike;
        this.ball = ball;
    }
}

public class Main{
    public static void main(String[] args)throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        //todo: 세자리 다 다른 수 + 0이 포함되지 않은 수
        Queue<Integer> queue = new LinkedList<>();
        for(int i=111;i<=999;i++){
            String valueToStr = Integer.toString(i);
            char[] valueToCharArr = valueToStr.toCharArray();
            
            if(valueToCharArr[0] == valueToCharArr[1] || valueToCharArr[1] == valueToCharArr[2] || valueToCharArr[0] == valueToCharArr[2]){
                continue;
            }
            
            if(valueToCharArr[0]=='0' || valueToCharArr[1]=='0' || valueToCharArr[2] =='0'){
                continue;
            }

            queue.add(i);
        }

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            String[] values = br.readLine().split(" ");
            int value = Integer.parseInt(values[0]);
            int strike = Integer.parseInt(values[1]);
            int ball = Integer.parseInt(values[2]);

            Queue<Integer> newQueue = new LinkedList<>();
            while(!queue.isEmpty()){
                int queueValue = queue.poll();
                Result result = getResult(queueValue, value);

                if(result.strike == strike && result.ball == ball){
                    newQueue.add(queueValue);
                }
            }

            queue = newQueue;
        }

        System.out.println(queue.size());
    }

    private static Result getResult(int queueValue, int value){
        int strike = 0;
        int ball = 0;

        for(int i=2;i>=0;i--){
            int queueWord = (int)(queueValue/Math.pow(10, i));

            int newValue = value;
            for(int j=2;j>=0;j--){
                int newValueWord = (int)(newValue/Math.pow(10, j));

                if(newValueWord == queueWord){
                    if(i==j){
                        strike++;
                    }else{
                        ball++;
                    }
                }

                newValue%=Math.pow(10, j);

            }

            queueValue%=Math.pow(10, i);
        }

        return new Result(strike, ball);
    }
}