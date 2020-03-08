//if you want instruction, please refer to notes
//first, let's not think that deep, let's first thinking about how to iterate every 0s in the grid only once?
//it feels like backtracking, which means we need to try every way, and when things not right, we pruning branches.
//four way dfs, when you enter a grid which is not end and left&right&up&down can't move, pruning the branches.
//and we use a variable to count for the time successfully reach the end point
class Solution {
    public int uniquePathsIII(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;

        int num = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    num++;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    helper(grid, i, j, count, num);
                }
            }
        }
        return count;
    }

    private void helper(int[][] grid, int i, int j, int count, int num) {
        if (grid[i][j] == 2) {
            if (num == 0) count++;
            return;
        }

        //if grid[i][j] == 1, proceed
        if (grid[i][j] == -1) { //means we hit the one we tranversed before
            return;
        }

        int m = grid.length;
        int n = grid[0].length;
        //num--;
        if (i>0 && grid[i-1][j] == 0) {
            grid[i][j] = -1; //current position mark as tranversed
            helper(grid, i-1, j, count, num-1);
            //grid[i][j] = 0;//after use it, get back
        }
        if (i< m-1 && grid[i+1][j] == 0) {
            grid[i][j] = -1;
            helper(grid, i+1, j, count, num-1);
            //grid[i][j] = 0;
        }
        if (j>0 && grid[i][j-1] == 0) {
            grid[i][j] = -1;
            helper(grid, i, j-1, count, num-1);
            //grid[i][j] = 0;
        }
        if (j< n-1 && grid[i][j+1] == 0) {
            grid[i][j] = -1;
            helper(grid, i, j+1, count, num-1);
            //grid[i][j] = 0;
        }
        num++;
        grid[i][j] = 0;
