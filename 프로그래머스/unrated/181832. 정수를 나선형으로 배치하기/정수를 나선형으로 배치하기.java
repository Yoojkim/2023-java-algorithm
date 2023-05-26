class Solution {
    public int[][] solution(int n) {
        int [][] res= new int [n][n];
        
        int num=1;
        int minX=0; int maxX=n-1;
        int minY=0; int maxY=n-1;
        while(num<=n*n){
            for (int i=minX; i<=maxX; i++){
                res[minX][i]=num++;
            }
            minY+=1;
            
            for(int i=minY; i<=maxY; i++){
                res[i][maxX]=num++;
            }
            maxX-=1;
            
            for(int i=maxX; i>=minX; i--){
                res[maxY][i]=num++;
            }
            maxY-=1;
            
            for(int i=maxY; i>=minY; i--){
                res[i][minX]=num++;
            }
            minX+=1;
        }
        
        return res;
    }
}