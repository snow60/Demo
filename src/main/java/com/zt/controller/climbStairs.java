package com.zt.controller;

/**
 * @Description 70. 爬楼梯
 * 提示
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * @Author ZT
 * @Date 2024/7/10 13:58
 * @Version V1.0.0
 */
public class climbStairs {

    // 解法：通项公式为f(n) = f(n-1) + f(n-2)(n >= 3)
    public static void main(String[] args) {
        int n = 3;
        int res = climbStairs(n);
        System.out.println(res);
    }

    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        // 定义一个数组来存储结果
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        // 递推计算每个台阶的方法数
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 返回第 n 阶的方法数
        return dp[n];

        // int p = 0, q = 0, r = 1;
        // for (int i = 1; i <= n; ++i) {
        //     p = q;
        //     q = r;
        //     r = p + q;
        // }
        // return r;
    }
}
