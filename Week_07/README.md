主要讲了 Trie,并查集；高级搜索（剪枝，双向BFS，A*）;红黑树和AVL树
内容比较杂。只看了一半的视频。
作业1:
双向BFS代码模版
		// 开始双端广度优先搜索
        Queue<Pair<String, Integer>> q_begin = new LinkedList<>();
        Queue<Pair<String, Integer>> q_end = new LinkedList<>();
        q_begin.offer(new Pair<>(beginWord, 1));
        q_end.offer(new Pair<>(endWord, 1));
        Map<String, Integer> beginVisited = new HashMap<>();
        Map<String, Integer> endVisited = new HashMap<>();
        beginVisited.put(beginWord, 1);
        endVisited.put(endWord, 1);
        while(!q_begin.isEmpty() && !q_end.isEmpty()) {
            int res = curNode(q_begin, beginVisited, endVisited);
            if(res != -1) {
                // 返回
            }
            res = curNode(q_end, endVisited, beginVisited);
            if(res != -1) {
                // 返回
            }
        }
作业2:
爬楼梯剪枝：
class Solution {
    // 斐波那契数列：1 1；2 2；3 3； n n-1 + n -2
    public int climbStairs(int n) {
        if(n <= 3) return n;
        int i = 2, j = 3, res = 0;
        for(int k = 4; k <= n; k++) {
            res = i + j;
            i = j;
            j = res;
        }
        return res;
    }
}