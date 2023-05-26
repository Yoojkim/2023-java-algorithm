class Solution {

    public int[] solution(int[] arr, int[] query) {
        for (int q=0;q<query.length;q++) {
            int boundary=query[q];
            
            if (q % 2 == 0) {
                int[] temp = new int[boundary + 1];
                for (int i = 0; i <= boundary; i++) {
                    temp[i] = arr[i];
                }
                arr = temp;
            } else {
                int[] temp = new int[arr.length - boundary];
                int tempIdx=0;
                for (int i = boundary; i < arr.length; i++) {
                    temp[tempIdx++] = arr[i];
                }
                arr = temp;
            }
        }

        return arr;
    }
}