package com.wangf.lib;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量
 * bfs
 * 技巧：
 * 二维数组转一维数组 grid[i][j] = > b[i * n + j]
 * 一维数组转二维数组  i = i * n + j / n, j = i * n + j % n
 */
class NumberOfIslandsSolution1 {

    public static void main(String[] args) {
        char[][] chars = {
                { '1', '1', '1', '1', '0'},
                { '1', '1', '0', '1', '0'},
                { '1', '1', '0', '0', '0'},
                { '0', '0', '0', '0', '0'},
//                {'1'},
//                {'1'}
        };
        int count = numIslands(chars);
        System.out.println(count);
    }

    public static int numIslands(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j =0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    bfs(grid, i, j);
                }
            }
        }

        return count;
    }

    private static void bfs(char[][] grid, int i, int j) {
        grid[i][j] = '0';
        // m * n = Rows * Columns
        int m = grid.length;
        int n = grid[0].length;
        // 二维数组转一维数组 grid[i][j] = > b[i * n + j]
        int code = i * n + j;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(code);
        while(!queue.isEmpty()) {
            // 一维数组转二维数组  grid[i][j] = > b[i * n + j]
            int val = queue.poll();
            int x = val / n;
            int y = val % n;
            if(x > 0 && grid[x - 1][y] == '1') { // 上
                queue.offer((x - 1) * n + y);
                grid[x - 1][y] = '0';
            }
            if(x + 1 < m && grid[x + 1][y] == '1') { // 下
                queue.offer((x + 1) * n + y);
                grid[x + 1][y] = '0';
            }
            if(y > 0 && grid[x][y - 1] == '1') { // 左
                queue.offer(x  * n + y - 1);
                grid[x][y - 1] = '0';
            }
            if(y + 1 < n && grid[x][y + 1] == '1') { // 右
                queue.offer(x * n + y + 1);
                grid[x][y + 1] = '0';
            }
        }
    }
}
