学习笔记
主要是对动态规划整理；包括字符串操作和字符串有关的动态规划题目。

题目1:
在学习总结中，写出不同路径 2 这道题目的状态转移方程。
遇到障碍物：dp[j] = 0; 
其他 dp[j] += dp[j - 1]

题目2:reverseOnlyLetters
class Solution {
    fun reverseOnlyLetters(S: String): String {
        if(S.isEmpty() || S.length <= 1) return S

        var array = S.toCharArray()
        var i = 0
        var j = S.length - 1
        while(i < j) { 
            while(i < j && !array[i].isLetter()) i++
            while(i < j && !array[j].isLetter()) j--
            if(i < j) {
                var temp = array[i]
                array[i] = array[j]
                array[j] = temp
                i++
                j--
            }
        }

        return array.joinToString("")
    }
}