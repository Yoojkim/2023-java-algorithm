import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n=Integer.parseInt(br.readLine());
        
        Map<Integer, Integer> map=new HashMap();
        
        StringTokenizer st=new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            int num=Integer.parseInt(st.nextToken());
            
            if(map.containsKey(num)){
                map.put(num, map.get(num)+1);
            } else{
                map.put(num, 1);
            }
        }
        
        int m=Integer.parseInt(br.readLine());
        st=new StringTokenizer(br.readLine());
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<m;i++){
            int num=Integer.parseInt(st.nextToken());
            
            if(map.containsKey(num))
                sb.append(map.get(num));
            else
                sb.append(0);
            
            sb.append(" ");
        }
        
        System.out.print(sb);
    }
}