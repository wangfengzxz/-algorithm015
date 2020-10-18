主要讲了 Trie,并查集；高级搜索（剪枝，双向BFS，A*）;红黑树和AVL树

*Trie树遗留的复杂度分析
单词搜索 II 的时间和空间复杂度分析
时间复杂度O(mn*4*3^(L-1)), mn为网格的行和列，L为最长单词长度，dfs第一步有4种走法，剩下的只有3种走法
空间复杂度：O(N)，其中 NN 是字典中的字母总数。
算法消耗的主要空间是我们构建的 Trie 数据结构。在最坏的情况下，如果单词之间没有前缀重叠，
则 Trie 将拥有与所有单词的字母一样多的节点。也可以选择在 Trie 中保留单词的副本。因此，我们可能需要 2N 的空间用于 Trie。

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

作业3:
前缀树的实现
class Trie {
    static class TrieNode {
        TrieNode[] children;
        boolean isWord;
        public TrieNode() {
            children = new TrieNode[26]; // 写错过，类型不要写错
            isWord = false;
        }
    }

    TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if(word == null || word.length() == 0) return; // 边界条件需要添加上
        TrieNode node = root;
        for(char c : word.toCharArray()) {
            int index = c - 'a';
            if(node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isWord  = true; // 错误点：插入的是单词，标志需要添加上
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = searchWith(word);
        return node != null && node.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        return searchWith(prefix) != null;
    }

    private TrieNode searchWith(String prefix) {
        if(prefix == null || prefix.length() == 0) return null; // 边界条件需要添加上
        TrieNode node = root;
        for(char c : prefix.toCharArray()) {
            int index = c - 'a';
            if(node.children[index] == null) return null;
            node = node.children[index];
        }
        return node;
    }
}


作业5:
单词搜索
class Solution {

    int[] dirX = {-1, 1, 0, 0};
    int[] dirY = {0, 0, -1, 1};

    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        if(board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) return res;
        TrieNode root = buildTrie(words);
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                dfs(board, i, j , root, res);
            }
        }
        return res;
    }

    public void dfs(char[][] board, int i, int j , TrieNode cur, List<String> res) {
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        char ch = board[i][j];
        int n = ch - 'a';
        if(ch == '#' || cur.children[ch - 'a'] == null) return;
        if(cur.children[n].word != null) {
            res.add(cur.children[n].word);
            cur.children[n].word = null;
        }
        cur = cur.children[n];
        board[i][j] = '#';
        for(int k = 0; k < 4; k++) {
            int tx = dirX[k] + i;
            int ty = dirY[k] + j;
            dfs(board, tx, ty, cur, res);
        }
        board[i][j] = ch;
    }

    public TrieNode buildTrie(String[] words) { // 错误点：参数类型写成String 了
        TrieNode root = new TrieNode();
        for(String word : words) {
            TrieNode node = root;
            for(char c : word.toCharArray()) {
                int n = c - 'a';
                if(node.children[n] == null) {
                    node.children[n] = new TrieNode();
                }
                node = node.children[n];
            }
            node.word = word;
        }
        return root;
    }
}
class TrieNode {
    String word;
    TrieNode[] children = new TrieNode[26];
}