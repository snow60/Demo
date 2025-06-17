package 剑指offer第二版;

/**
 * @author ZT
 * @version 1.0.0
 * @description $
 * @date 2025/6/11
 */
public class two {
    public class FindDuplicateWithoutModify {
        /**
         * 使用二分法+抽屉原理找出重复数字
         * @param nums 输入数组（长度n+1，数字范围1~n）
         * @return 重复的数字
         */
        public static int findDuplicate(int[] nums) {
            // 1. 鲁棒性校验（空数组/越界值）
            if (nums == null || nums.length == 0) {
                throw new IllegalArgumentException("数组不能为空");
            }
            for (int num : nums) {
                if (num < 1 || num >= nums.length) {
                    throw new IllegalArgumentException("数字超出范围 [1, " + (nums.length-1) + "]");
                }
            }

            // 2. 二分法初始化（数值范围区间）
            int low = 1;
            int high = nums.length - 1;  // n的范围

            // 3. 二分搜索核心逻辑
            while (low < high) {
                int mid = low + (high - low) / 2;  // 防溢出
                int count = 0;

                // 4. 抽屉原理应用：统计≤mid的数字个数 📦[1,6](@ref)
                for (int num : nums) {
                    if (num <= mid) {
                        count++;
                    }
                }

                // 5. 关键判定：超容说明重复数在左区间
                if (count > mid) {    // 抽屉原理：实际数量 > 理论容量
                    high = mid;       // 缩小到左区间 [low, mid]
                } else {
                    low = mid + 1;    // 否则在右区间 [mid+1, high]
                }
            }
            return low;  // 此时 low == high
        }

        // 测试用例
        public static void main(String[] args) {
            // 示例1：多重重复（2和3重复）
            int[] test1 = {2, 3, 5, 4, 3, 2, 6, 7};
            System.out.println("示例1重复数字: " + findDuplicate(test1)); // 输出2或3

            // 示例2：边界值验证（最大数重复）
            int[] test2 = {1, 2, 3, 4, 5, 5};
            System.out.println("示例2重复数字: " + findDuplicate(test2)); // 输出5
        }
    }
}
