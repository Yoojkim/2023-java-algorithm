//import java.util.*;
//import java.io.*;
//
//public class Main{
//    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        String line=br.readLine();
//        LinkedList<Character> list=new LinkedList<>();
//
//        list.addLast('.');
//        for(char c:line.toCharArray()){
//            list.addLast(c);
//            list.addLast('.');
//        }
//
//        list.listIterator()
//
//        ListIterator<Character> it=list.listIterator(list.size()-1);
//
//        int m=Integer.parseInt(br.readLine());
//        for(int i=0;i<m;i++){
//            String command=br.readLine();
//            processCmd(command, it);
//        }
//
//        StringBuilder sb=new StringBuilder();
//        for(char c:list){
//            if(c=='.')
//                continue;
//
//            sb.append(c);
//        }
//
//        System.out.print(sb);
//    }
//
//    //iterator idx 동작 과정 확인
//    private static void processCmd(String command, ListIterator<Character> it){
//        if(command.length()==1){
//            char cmd=command.toCharArray()[0];
//
//            //뒤로
//            if(cmd=='D'){
//                //뒤로 두 칸
//                if(!it.hasNext())
//                    return;
//
//                for(int i=0;i<2;i++){
//                    it.next();
//                }
//
//                return;
//            }
//
//            if(!it.hasPrevious())
//                return;
//
//            if(cmd=='L'){
//                for(int i=0;i<2;i++){
//                    it.previous();
//                }
//            } else{
//                //B
//                for(int i=0;i<2;i++){
//                    it.remove();
//                    it.remove();
//                }
//            }
//        } else{
//            StringTokenizer st= new StringTokenizer(command);
//            st.nextToken();
//            char c=st.nextToken().toCharArray()[0];
//
//            it.add(c);
//            it.add()
//        }
//    }
//
//    /*
//    private static int processCmd(LinkedList<Character> list, int cursor, String command){
//        if(command.length()==1){
//            char cmd=command.toCharArray()[0];
//
//            if(cmd=='D'){
//                if (cursor==list.size()-1)
//                    return cursor;
//
//                cursor+=2;
//            } else{
//                if(cursor==0)
//                    return cursor;
//
//                cursor-=2;
//                if(cmd=='B'){
//                    list.remove(cursor+1);
//                    list.remove(cursor+1);
//                }
//            }
//        } else{
//            StringTokenizer st= new StringTokenizer(command);
//            st.nextToken();
//            char c=st.nextToken().toCharArray()[0];
//            list.add(cursor, c);
//            list.add(cursor, '.');
//
//            cursor+=2;
//        }
//
//        return cursor;
//    }
//    **/
//}
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        LinkedList<Character> list=new LinkedList();
        for(char c:line.toCharArray()){
            list.addLast(c);
        }

        ListIterator<Character> it = list.listIterator(list.size());

        int m = Integer.parseInt(br.readLine());
        for(int i=0;i<m;i++){
            String command=br.readLine();
            processCmd(command, it);
        }

        StringBuilder sb = new StringBuilder();
        for(char c:list){
            sb.append(c);
        }

        System.out.print(sb);
    }

    private static void processCmd(String command, ListIterator<Character> it){
        if(command.length()!=1){
            char data=command.toCharArray()[2];
            it.add(data);

            return;
        }

        char cmd=command.toCharArray()[0];

        if((cmd=='L' || cmd=='B') && !it.hasPrevious())
            return;
        if(cmd=='D' && !it.hasNext())
            return;

        switch(cmd){
            case 'D':
                it.next();
                return;
            case 'L':
                it.previous();
                return;
            case 'B':
                it.previous();
                it.remove();
                return;
        }
    }
}