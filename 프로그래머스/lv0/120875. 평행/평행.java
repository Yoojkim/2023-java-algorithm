class Solution {
    
    public int solution(int[][] dots) {

        //d0을 기준
        int dotsSize = dots.length;
        for (int i = 1; i < dotsSize; i++) {
            double dxdy1 = getDxDy(dots[0], dots[i]);

            int[] d2 = (i + 1) % dotsSize != 0 ? dots[(i + 1) % dotsSize] : dots[1];
            int[] d3 = (i + 2) % dotsSize != 0 ? dots[(i + 2) % dotsSize] : dots[1];

            double dxdy2 = getDxDy(d2, d3);

            if (dxdy1/dxdy2==1)
                return 1;
        }

        return 0;
    }

    private double getDxDy(int[] d1, int[] d2) {
        return (double) (d1[1] - d2[1]) / (d1[0] - d2[0]);
    }
}