class Solution {
    
    //y, x
    private final int[][] position={{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
    
    public int solution(int[][] board) {
        int n=board.length;
        for(int row=0;row<n;row++){
            for(int col=0;col<n;col++){
                if (board[row][col]==1)
                    setNonAccessible(row, col, board);
            }
        }
        
        int result=0;
        for(int[] row:board){
            for(int col:row){
                if (col==0)
                    result++;
            }
        }
        
        return result;
    }
    
    private void setNonAccessible(int y, int x, int[][] board){
        int n=board.length;
        
        for(int[] p: position){
            int pY=y+p[0]; int pX=x+p[1];
            if ((pY>=0 && pY<n && pX>=0 && pX<n)){
                if(board[pY][pX]==1)
                    continue;
            
                board[pY][pX]=-1;
            }
        }
    }
}