import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs=br.readLine().split(" ");

        int n=Integer.parseInt(inputs[0]);
        int k=Integer.parseInt(inputs[1]);
        int[] seq=new int[k];

        String[] ks=br.readLine().split(" ");
        for(int i=0;i<k;i++){
            seq[i]=Integer.parseInt(ks[i]);
        }

        //계산에 필요한 변수들
        int out=0;
        LinkedList<Integer> uses=new LinkedList();
        int used=0;
        boolean[] visited=new boolean[101];

        for(int i=0;i<k;i++){
            int machine=seq[i];

            //1. 이미 꽂혀있음
            if(visited[machine])
                continue;

            //2. 안 꽂혀있음
            //2-1. 꽂을 수 있음
            if(used<n){
                visited[machine]=true;
                uses.addLast(machine);
                used++;
                continue;
            }

            //2-2. 꽂을 수 없음
            //2-2-a. 현재 멀티탭에 꽂힌 전자기기 중에서 이후에 호출되지 않는 전자기기는 제거
            int removeIdx=-1;
            for(int u=0;u<uses.size();u++){
                boolean notCall=true;
                int nowUses=uses.get(u);
                for(int j=i;j<k;j++){
                    if(seq[j]==nowUses){
                        notCall=false;
                        break;
                    }
                }

                if(notCall){
                    removeIdx=u;
                    break;
                }
            }

            //2-2-a. 제거 후 진행
            if(removeIdx>=0){
                int removedVal=uses.get(removeIdx);
                uses.remove(removeIdx);
                visited[removedVal]=false;

                uses.addLast(machine);
                visited[machine]=true;
                out++;
                continue;
            }

            //2-2-b. 현재 uses에서 가장 나중에 호출되는 값을 삭제
            int max=Integer.MIN_VALUE;
            int maxU=-1;
            for(int u=0;u<uses.size();u++){
                int uNum=uses.get(u);

                for(int j=i;j<k;j++){
                    if(seq[j]==uNum){
                        if(max<j) {
                            max = j;
                            maxU=u;
                        }
                        break;
                    }
                }
            }

            int removedMachine=uses.get(maxU);
            uses.remove(maxU);
            visited[removedMachine]=false;

            visited[machine]=true;
            uses.addLast(machine);
            out++;

        }

        System.out.print(out);
    }
}