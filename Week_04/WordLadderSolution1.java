package com.wangf.lib;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

// 127. 单词接龙
// 广度优先搜索
public class WordLadderSolution1 {

    public static void main(String[] args) {
        int result = ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog")));
        System.out.println(result);
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        // 转化图
        int len = beginWord.length();
        HashMap<String, ArrayList<String>> allComboDict = new HashMap<>();
        for(String curWord : wordList) {
            for (int i = 0; i < len; i++) {
                String comboWord = curWord.substring(0, i) + "*" + curWord.substring(i + 1, len);
                ArrayList<String> comboWordList = allComboDict.getOrDefault(comboWord, new ArrayList<>());
                comboWordList.add(curWord);
                allComboDict.put(comboWord, comboWordList);
            }
        }
        // 广度优先遍历队列
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        Set<String> hasVisitedList = new HashSet<>();
        queue.add(new Pair<>(beginWord, 1));
        hasVisitedList.add(beginWord);
        // 广度优先遍历，逐个取出队列中元素进行操作
        while (!queue.isEmpty()) {
            // 循环当前层节点
            for(int count = queue.size(); count > 0; --count) {
                Pair<String, Integer> node = queue.remove();
                String currWord = node.getKey();
                int level = node.getValue();
                for (int i = 0; i < len; i++) {
                    String currComboWord = currWord.substring(0, i) + "*" + currWord.substring(i + 1, len);
                    ArrayList<String> currComboWordList = allComboDict.getOrDefault(currComboWord, new ArrayList<>());
                    for (String word : currComboWordList) {
                        if (word.equals(endWord))
                            return level + 1;
                        if (!hasVisitedList.contains(word)){
                            queue.add(new Pair<>(word, level + 1));
                            // 标记它为已访问
                            hasVisitedList.add(word);
                        }
                    }
                }
            }
        }
        return 0;
    }

    static class Pair<K, V> {
        K k;
        V v;
        public Pair(K key, V value) {
            k = key;
            v = value;
        }
        public K getKey() {
            return k;
        }
        public V getValue() {
            return v;
        }
    }
}