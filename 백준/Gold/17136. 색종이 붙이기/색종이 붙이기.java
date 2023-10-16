import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] arr=new int[10][10];
        StringTokenizer st;
        for(int i=0;i<10;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<10;j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        int[] sizes=new int[6]; //1~5

        int ans=backTracking(arr, sizes, 0, 0);
        ans=(ans==Integer.MAX_VALUE)?-1:ans;

        System.out.print(ans);
    }

    private static int backTracking(int[][] arr, int[] sizes, int i, int j){
        if(i==10){
            int cnt=0;
            for(int size:sizes){
                cnt+=size;
            }

            return cnt;
        }

        int[] nextPoint=getNextPoint(i, j);

        int min=Integer.MAX_VALUE;
        if(arr[i][j]==0){
            int res=backTracking(arr, sizes, nextPoint[0], nextPoint[1]);

            if(min>res)
                min=res;
        } else{
            for(int size=5; size>0; size--){
                if(possible(arr, sizes, i, j, size)){
                    //attach
                    attach(arr, i, j, size);
                    sizes[size]++;

                    int res=backTracking(arr, sizes, nextPoint[0], nextPoint[1]);
                    if(min>res)
                        min=res;

                    //detach
                    detach(arr, i, j, size);
                    sizes[size]--;
                }
            }
        }

        return min;
    }

    private static boolean possible(int[][] arr, int[] sizes, int i, int j, int size){
        //현재 i, j에서 size가 가능한지

        //outOfRange 처리
        if(i+size-1 >=10 || j+size-1 >=10)
            return false;


        for(int row=0; row<size;row++){
            for(int col=0;col<size;col++){
                if(arr[i+row][j+col]==0)
                    return false;
            }
        }

        if(sizes[size]==5)
            return false;

        return true;
    }

    private static void attach(int[][] arr, int i, int j, int size){
        for(int row=0; row<size;row++){
            for(int col=0;col<size;col++){
                arr[i+row][j+col]=0;
            }
        }
    }

    private static void detach(int[][] arr, int i, int j, int size){
        for(int row=0; row<size;row++){
            for(int col=0;col<size;col++){
                arr[i+row][j+col]=1;
            }
        }
    }

    private static int[] getNextPoint(int i, int j){
        int[] ans=new int[2];

        if(j+1==10){
            ans[0]=i+1;
            ans[1]=0;
        }else{
            ans[0]=i;
            ans[1]=j+1;
        }

        return ans;
    }

}
