package com.wangf.lib;


/**
 * 200. 岛屿数量
 * dfs
 * 技巧：
 * 二维数组转一维数组 grid[i][j] = > b[i * n + j]
 * 一维数组转二维数组  i = i * n + j / n, j = i * n + j % n
 */
class NumberOfIslandsSolution2 {

    public static void main(String[] args) {
        char[][] chars = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
//                {'1'},
//                {'1'}
        };
        int count = numIslands(chars);
        System.out.println(count);
    }

    public static int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') { // 循环到 '1'， 则开始dfs
                    ++count;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') return; // 无效位置或已经被访问过
        grid[i][j] = '0';
        dfs(grid, i - 1, j);// 上
        dfs(grid, i + 1, j);// 下
        dfs(grid, i, j - 1);// 左
        dfs(grid, i, j + 1);// 右
    }
}
