package com.wangf.lib;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 127. 单词接龙
// 双向广度优先搜索
class WordLadderSolution2 {

    public static void main(String[] args) {
        int result = ladderLength("hit", "cog", new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog")));
        System.out.println(result);
    }

    private static int length = 0;
    private static Map<String, List<String>> allComboDict = new HashMap<>();

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 异常判断
        if (!wordList.contains(endWord)) {
            return 0;
        }
        // 创建图数据
        length = beginWord.length();
        for(String word: wordList) {
            for (int i = 0; i < length; i++) {
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, length);
                List<String> transformations = allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        }

        // 创建2个队列
        Queue<Pair<String, Integer>> Q_begin = new LinkedList<>();
        Queue<Pair<String, Integer>> Q_end = new LinkedList<>();
        // 2个队列分别加beginWord和endWord
        Q_begin.add(new Pair<>(beginWord, 1));
        Q_end.add(new Pair<>(endWord, 1));

        // Visited to make sure we don't repeat processing same word.
        Map<String, Integer> visitedBegin = new HashMap<>();
        Map<String, Integer> visitedEnd = new HashMap<>();
        visitedBegin.put(beginWord, 1);
        visitedEnd.put(endWord, 1);

        while (!Q_begin.isEmpty() && !Q_end.isEmpty()) {
            // One hop from begin word
            int ans = visitWordNode(Q_begin, visitedBegin, visitedEnd);
            if (ans > -1) {
                return ans;
            }
            // One hop from end word
            ans = visitWordNode(Q_end, visitedEnd, visitedBegin);
            if (ans > -1) {
                return ans;
            }
        }

        return 0;
    }

    private static int visitWordNode(Queue<Pair<String, Integer>> Q, Map<String, Integer> visited, Map<String, Integer> othersVisited) {
        Pair<String, Integer> node = Q.poll();
        String word = node.getKey();
        int level = node.getValue();
        for (int i = 0; i < length; i++) {
            String newWord = word.substring(0, i) + '*' + word.substring(i + 1, length);
            // 循环匹配模式单词对应的单词集合
            for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<>())) {
                if (othersVisited.containsKey(adjacentWord)) {
                    return level + othersVisited.get(adjacentWord);
                }
                if (!visited.containsKey(adjacentWord)) {
                    visited.put(adjacentWord, level + 1);
                    Q.add(new Pair<>(adjacentWord, level + 1));
                }
            }
        }
        return -1;
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