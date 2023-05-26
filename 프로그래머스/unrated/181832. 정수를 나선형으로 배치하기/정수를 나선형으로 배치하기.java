class Solution {
    public int[][] solution(int n) {
        int [][] res= new int [n][n];
        
        int num=1;
        int minX=0; int maxX=n-1;
        int minY=0; int maxY=n-1;
        int x=0; int y=0;
        while(num<=n*n){
            for (int i=minX; i<=maxX; i++){
                x=i;
                res[y][x]=num++;
            }
            minY+=1;
            
            for(int i=minY; i<=maxY; i++){
                y=i;
                res[y][x]=num++;
            }
            maxX-=1;
            
            for(int i=maxX; i>=minX; i--){
                x=i;
                res[y][x]=num++;
            }
            maxY-=1;
            
            for(int i=maxY; i>=minY; i--){
                y=i;
                res[y][x]=num++;
            }
            minX+=1;
        }
        
        return res;
    }
}