import java.io.*;

class Main{
    static int N;
    static int DIGIT = 6;
    static int min;
    static boolean[] forbidden = new boolean[10];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        //DIGIT = getDigit(N);

        int m = Integer.parseInt(br.readLine());

        if(m>0) {
            String[] forbiddenNums = br.readLine().split(" ");
            for (String forbiddenNum : forbiddenNums) {
                int num = Integer.parseInt(forbiddenNum);

                forbidden[num] = true;
            }
        }

        min = getDiff(N, 100);
        createNum(1, 0);
        System.out.print(min);
    }

    static int getDiff(int n1, int n2){
        return (n1>n2)?n1-n2:n2-n1;
    }

    static int getDigit(int n){
        int digit = 0;

        while(n/(int)(Math.pow(10, digit))!=0){
            digit++;
        }

        return digit;
    }

    static void createNum(int digit, int num){
        if(digit == DIGIT+2) {
            return;
        }

        if(digit>1){
            int res = getDiff(num, N)+digit-1;

            if(min > res){
                min = res;
            }
        }

        for(int i=0;i<=9;i++){
            if(forbidden[i]){
                continue;
            }

            createNum(digit+1, (int)(num+Math.pow(10, digit-1)*i));
        }
    }
}