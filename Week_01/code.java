package com.wangf.memory;

import java.util.Deque;
import java.util.LinkedList;

public class Test {
    // 用 add first 或 add last 这套新的 API 改写 Deque 的代码
    public void work1() {
        Deque<String> deque = new LinkedList<>();

        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");
        System.out.println(deque);

        String str = deque.peekFirst();
        System.out.println(str);
        System.out.println(deque);

        while(deque.size() > 0) {
            System.out.println(deque.removeFirst());
        }
        System.out.println(deque);
    }

    // 加1
    class Solution {
        public int[] plusOne(int[] digits) {
            for(int i = digits.length - 1; i >= 0; i--) {
                digits[i]++;
                if(digits[i] % 10 == 0) {
                    digits[i] = 0;
                } else {
                    return digits;
                }
            }
            digits = new int[digits.length + 1];
            digits[0] = 1;
            return digits;
        }
    }
}
