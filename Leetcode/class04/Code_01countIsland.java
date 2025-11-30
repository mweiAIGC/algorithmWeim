package class04;

public class Code_01countIsland {
    public static int countIslands(int[][] grid) {
        int res = 0;
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j< grid[0].length; j++){
                if(grid[i][j] == 1){
                    res++;
                    infect(grid, i,j,grid.length,grid[0].length);
                }

            }
        }
        return res;

    }

    public static void infect(int[][] grid, int i, int j, int length, int width){

        if(i < 0 || i >= length || j < 0 || j >= width || grid[i][j] != 1) return;
        grid[i][j] = 2;
        infect(grid,i+1,j,length,width);
        infect(grid,i-1,j,length,width);
        infect(grid,i,j+1,length,width);
        infect(grid,i,j-1,length,width);
    }

}
