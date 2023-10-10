import java.util.*;
import java.io.*;

//수 6개를 고르는 로또
class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb=new StringBuilder();

        while(true){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int n=Integer.parseInt(st.nextToken());

            if(n==0)
                break;

            int[] ns=new int[n];
            for(int i=0;i<n;i++){
                ns[i]=Integer.parseInt(st.nextToken());
            }

            Arrays.sort(ns);
            pickLotto(sb, ns, 0, new LinkedList<Integer>());
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void pickLotto(StringBuilder sb, int[] ns, int idx, List<Integer> ans){
        if(ans.size()==6){
            for(int a:ans){
                sb.append(a).append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");

            return;
        }

        for(int i=idx;i<ns.length;i++){
            ans.add(ns[i]);
            pickLotto(sb, ns, i+1, ans);
            ans.remove(ans.size()-1);
        }
    }
}