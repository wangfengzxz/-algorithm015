package com.wangf.lib;

public class LemonadeChangeSolution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for(int bill: bills) {
            // 放入钱箱
            if(bill == 5) five++;
            if(bill == 10) ten++;
            // 找零钱
            int change = bill - 5;
            if(change > 0) {
                // 找10块
                while(change >= 10 && ten > 0) {
                    change -= 10;
                    ten--;
                }
                // 找5块
                while(change >=5 && five > 0) {
                    change -= 5;
                    five--;
                }
                if(change > 0) return false;
            }
        }
        return true;
    }
}
