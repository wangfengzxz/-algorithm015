package com.wangf.lib;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 529. 扫雷游戏
 * bfs
 *  情况：和dfs类似
 */
public class MineSweeperSolution2 {

    public static void main(String[] args) {
        char[][] board = {
                {'E','E','E','E','E'},
                {'E','E','M','E','E'},
                {'E','E','E','E','E'},
                {'E','E','E','E','E'}
        };
        updateBoard(board, new int[]{3, 0});
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
    }

    // bfs
    static int[] offsetX = {0, 0, 1, 1, 1, -1, -1, -1};
    static int[] offsetY = {1, -1, 1, 0, -1, 1, 0, -1};
    public static char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if(board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            bfs(board, x, y);
        }
        return board;
    }

    private static void bfs(char[][] board, int x, int y) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        Queue<Integer> queue = new LinkedList<>();
        int m = board.length;
        int n = board[0].length;
        queue.offer(x * n + y); // queue可以是二维转一维的Integer，也可以new int[2]
        visited[x][y] = true; // 设置当前节点访问过
        while(!queue.isEmpty()) {
            for(int count = queue.size(); count > 0; --count) {// 可取可不去，更好理解
                int code = queue.poll(); // 一维转二维
                int tx = code / n;
                int ty = code % n;
                int cnt = 0; // 计算附近地雷个数
                for(int i = 0; i < 8; i++) {
                    int ox = tx + offsetX[i];
                    int oy = ty + offsetY[i];
                    if(ox < 0 || ox >= m || oy < 0 || oy >= n) {
                        continue;
                    }
                    if(board[ox][oy] == 'M') {
                        ++cnt;
                    }
                }
                if(cnt > 0) { // 地雷有，则数字字符表示
                    board[tx][ty] = (char)('0' + cnt);
                } else { // 附近没有地雷，有效点进入队列，设置访问过，防止多次访问
                    board[tx][ty] = 'B';
                    for(int i = 0; i < 8; i++) {
                        int rx = tx + offsetX[i];
                        int ry = ty + offsetY[i];
                        if(rx < 0 || rx >= m || ry < 0 || ry >=n || board[rx][ry] != 'E' || visited[rx][ry]) {
                            continue;
                        }
                        queue.offer(rx * n + ry);
                        visited[rx][ry] = true;
                    }
                }
            }
        }

    }
}
