import java.util.*;
import java.io.*;

/**
 * 이 코드가 적합하지 않은 이유: 이진탐색 시간복잡도가 O(logN) 이라고 하더라도 add의 시간 복잡도가 있음 -> 적합하지 않음
 * O(n)을 고려했을 때 해당 시간 복잡도는 적합하지 않음
 */

class Main {
    static PriorityQueue<int[]> asc;
    static PriorityQueue<int[]> desc;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        for (int c = 0; c < tc; c++) {
            boolean[] alreadyRemoved = new boolean[1000000];
            //Priority Queue Initialize
            //int[] o={value, idx}
            asc = new PriorityQueue<>(new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
                }
            });

            //내림차순
            desc = new PriorityQueue<>(new Comparator<int[]>() {
                public int compare(int[] o1, int[] o2) {
                    return o2[0] - o1[0];
                }
            });

            int ops = Integer.parseInt(br.readLine());
            for (int i = 0; i < ops; i++) {
                //연산 수행
                String[] nums = br.readLine().split(" ");

                String operation = nums[0];
                int value = Integer.parseInt(nums[1]);

                if (operation.equals("I")) {
                    add(value, i);
                } else {
                    remove(value, alreadyRemoved);
                }
            }

            print(alreadyRemoved);
        }
        System.out.println(sb);
    }

    private static void print(boolean[] alreadyRemoved) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        while (!desc.isEmpty()) {
            int[] now = desc.poll();

            if (!alreadyRemoved[now[1]]) {
                if (now[0] > max)
                    max = now[0];

                if (now[0] < min)
                    min = now[0];
            }

        }

        if(max==Integer.MIN_VALUE && min==Integer.MAX_VALUE)
            sb.append("EMPTY\n");
        else
            sb.append(max).append(" ").append(min).append("\n");
    }

    private static void add(int val, int idx) {
        int[] newNode = {val, idx};

        asc.add(newNode);
        desc.add(newNode);
    }

    private static void remove(int option, boolean[] alreadyRemoved) {
        if (option > 0)
            removeFromQueue(desc, alreadyRemoved);
        else
            removeFromQueue(asc, alreadyRemoved);
    }

    private static void removeFromQueue(PriorityQueue<int[]> queue, boolean[] alreadyRemoved) {
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (alreadyRemoved[now[1]])
                continue;

            alreadyRemoved[now[1]] = true;
            break;
        }
    }
}