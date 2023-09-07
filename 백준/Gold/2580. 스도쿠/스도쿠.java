import java.util.*;
import java.io.*;

class Main{
    static int[][] arr=new int[9][9];
    static int[][] rowCheck=new int[10][9];
    static int[][] colCheck=new int[10][9];
    static boolean pause=false;
    static StringBuilder sb = new StringBuilder();
    static int cnt=0;
    static int blank=0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<=9;i++){
            for(int j=0;j<9;j++){
                rowCheck[i][j]=-1;
                colCheck[i][j]=-1;
            }
        }

        //arr, rowCheck, colCheck 초기화
        for(int i=0;i<9;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            for(int j=0;j<9;j++){
                int num=Integer.parseInt(st.nextToken());
                arr[i][j]=num;

                //check 배열 초기화
                rowCheck[num][i]=j;
                colCheck[num][j]=i;

                if(num==0)
                    blank++;

            }
        }

        backTracking(0, 0);

        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void backTracking(int row, int col){
        if(pause || blank==cnt || row==9){
            pause=true;
            return;
        }

        int newRow=(col+1<9?row:row+1); int newCol=(col+1<9?col+1:0);

        if(arr[row][col]!=0){
            backTracking(newRow, newCol);
            return;
        }
        //분기..
        for(int num=1;num<=9;num++){
            if(isPossible(row, col, num)){

                rowCheck[num][row]=col;
                colCheck[num][col]=row;
                cnt++;

                arr[row][col]=num;

                backTracking(newRow, newCol);

                if(pause || blank==cnt)
                    return;

                //여기가 문제인 것 같음
                arr[row][col]=0;

                rowCheck[num][row]=-1;
                colCheck[num][col]=-1;
                cnt--;
            }
        }
    }

    private static boolean isPossible(int row, int col, int num){
        if(rowCheck[num][row]!=-1)
            return false;

        if(colCheck[num][col]!=-1)
            return false;

        //3X3 비교
        int startRow=(row/3)*3;
        int startCol=(col/3)*3;

        for(int i=startRow;i<startRow+3;i++){
            for(int j=startCol;j<startCol+3;j++){
                if(arr[i][j]==num)
                    return false;
            }
        }

        return true;
    }
}