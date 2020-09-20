package com.wangf.lib;

/**
 * 529. 扫雷游戏
 * dfs
 *  情况：
 *  1、如果直接地雷M，改X，返回
 *  2、dfs，判断顶点四周地雷个数，如果有个数，则设置数量；如果没有地雷，设置B，当前节点的四周8个节点继续dfs （技巧，循环8次，使用2个数组，可以获取四周的所有坐标，然后需要判断坐标的有效性）
 */
public class MineSweeperSolution1 {

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

    static int[] dirX = {0, 0, 1, 1, 1, -1, -1, -1};
    static int[] dirY = {1, -1, 1, 0, -1, 1, 0, -1};
    // dfs
    public static char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if(board[x][y] == 'M') { // 踩到地雷，则变X返回面板
            board[x][y] = 'X';
        } else { // 不会则dfs
            dfs(board, x, y);
        }
        return board;
    }

    private static void dfs(char[][] board, int x, int y) {
        int cnt = 0; // 计算点附近地雷个数
        for(int i = 0; i < 8; i++) {
            int tx = x + dirX[i];
            int ty = y + dirY[i];
            if(tx < 0 || tx >=board.length || ty < 0 || ty >= board[0].length) {
                continue;
            }
            if(board[tx][ty] == 'M') {
                ++cnt;
            }
        }
        if(cnt > 0) { // 地雷个数有的话，数字展示，这里技巧要注意
            board[x][y] = (char)(cnt + '0');
        } else { // 不是地雷，则变B，附近的有效的点进行dfs
            board[x][y] = 'B';
            for(int i = 0; i < 8; i++) {
                int tx = x + dirX[i];
                int ty = y + dirY[i];
                if(tx < 0 || tx >= board.length || ty < 0 || ty >=board[0].length || board[tx][ty] != 'E') {
                    continue;
                }
                dfs(board, tx, ty);
            }
        }
    }
}
