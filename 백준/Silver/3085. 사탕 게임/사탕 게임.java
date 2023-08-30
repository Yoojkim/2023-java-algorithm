import java.util.*;
import java.io.*;

public class Main{
    static char[][] fields;
    static int n;
    static int ans=Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n=Integer.parseInt(br.readLine());

        fields=new char [n][n];
        for(int i=0;i<n;i++){
            fields[i]=br.readLine().toCharArray();
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(i+1<n && fields[i+1][j]!=fields[i][j]){
                    char temp=fields[i+1][j];
                    fields[i+1][j]=fields[i][j];
                    fields[i][j]=temp;

                    count();

                    fields[i][j]=fields[i+1][j];
                    fields[i+1][j]=temp;
                }

                if(j+1<n && fields[i][j+1]!=fields[i][j]){
                    char temp=fields[i][j+1];
                    fields[i][j+1]=fields[i][j];
                    fields[i][j]=temp;

                    count();

                    fields[i][j]=fields[i][j+1];
                    fields[i][j+1]=temp;
                }
            }
        }

        System.out.print(ans);
    }

    private static void count(){
        for(int i=0;i<n;i++){
            char before='N';
            int cnt=0;
            for(int j=0;j<n;j++){
                if(fields[i][j]==before){
                    cnt++;
                }else{
                    if(cnt>ans)
                        ans=cnt;

                    before=fields[i][j];
                    cnt=1;
                }
            }

            if(cnt>ans)
                ans=cnt;

        }

        for(int j=0;j<n;j++){
            char before='N';
            int cnt=0;

            for(int i=0;i<n;i++){
                if(fields[i][j]==before){
                    cnt++;
                }else{
                    if(cnt>ans)
                        ans=cnt;

                    before=fields[i][j];
                    cnt=1;
                }
            }

            if(cnt>ans)
                ans=cnt;
        }
    }
}