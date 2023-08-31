import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n=Integer.parseInt(br.readLine());

        StringTokenizer st=new StringTokenizer(br.readLine());
        List<Integer> field=new ArrayList();
        for(int i=0;i<n;i++){
            int num=Integer.parseInt(st.nextToken());
            
            field.add(num);
        }
        
        //배열 정렬
        Collections.sort(field);
        
        int m=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            int num=Integer.parseInt(st.nextToken());
            int ans=0;

            int low=0; int high=n-1;
            while(low<=high){
                int mid=(low+high)/2;
                
                int midNum=field.get(mid);
                if(midNum==num){
                    ans=1;
                    break;
                }
                
                if(midNum>num)
                    high=mid-1;
                else
                    low=mid+1;
            }

            System.out.println(ans);
        }
    }
}