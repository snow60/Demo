package LeetCode热题目100;

/**
 * LeetCode 2. 两数相加
 * 
 * <p>给你两个非空的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，
 * 并且每个节点只能存储一位数字。请你将两个数相加，并以相同形式返回一个表示和的链表。</p>
 * 
 * <p>你可以假设除了数字 0 之外，这两个数都不会以 0 开头。</p>
 * 
 * <p>示例：</p>
 * <ul>
 *   <li>输入：l1 = [2,4,3], l2 = [5,6,4] 输出：[7,0,8] 解释：342 + 465 = 807</li>
 *   <li>输入：l1 = [0], l2 = [0] 输出：[0]</li>
 *   <li>输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9] 输出：[8,9,9,9,0,0,0,1]</li>
 * </ul>
 * 
 * <p>提示：</p>
 * <ul>
 *   <li>每个链表中的节点数在范围 [1, 100] 内</li>
 *   <li>0 <= Node.val <= 9</li>
 *   <li>题目数据保证列表表示的数字不含前导零</li>
 * </ul>
 * 
 * @author ZT
 * @version 1.0.0
 */
public class AddTwoNumbers {

    /**
     * 链表节点的定义，表示一个单向链表的节点。
     * 
     * <p>在本题中，每个节点存储一个0-9之间的数字。</p>
     */
    public static class ListNode {
        /** 节点值，范围：[0,9] */
        int val;
        
        /** 指向下一个节点的引用，如果是最后一个节点则为null */
        ListNode next;
        
        /**
         * 默认构造函数
         */
        ListNode() {}
        
        /**
         * 使用指定值创建节点
         * 
         * @param val 节点值，必须在[0,9]范围内
         * @throws IllegalArgumentException 如果节点值不在有效范围内
         */
        ListNode(int val) {
            this.val = val;
            validateNodeValue(val);
        }
        
        /**
         * 使用指定值和下一个节点创建节点
         * 
         * @param val 节点值，必须在[0,9]范围内
         * @param next 下一个节点的引用
         * @throws IllegalArgumentException 如果节点值不在有效范围内
         */
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
            validateNodeValue(val);
        }
    }

    /**
     * 验证节点值是否在有效范围内
     * 
     * @param val 节点值
     * @throws IllegalArgumentException 如果节点值不在[0,9]范围内
     */
    private static void validateNodeValue(int val) {
        if (val < 0 || val > 9) {
            throw new IllegalArgumentException("Node value must be between 0 and 9, got: " + val);
        }
    }

    /**
     * 验证链表长度是否在有效范围内
     * 
     * @param head 链表头节点
     * @return 链表长度
     * @throws IllegalArgumentException 如果链表长度不在[1,100]范围内
     */
    private int validateListLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            if (length > 100) {
                throw new IllegalArgumentException("List length must not exceed 100 nodes");
            }
            current = current.next;
        }
        if (length < 1) {
            throw new IllegalArgumentException("List must contain at least 1 node");
        }
        return length;
    }

    /**
     * 两数相加方法
     * 
     * <p>将两个非空链表表示的非负整数相加，返回一个表示和的链表。</p>
     * <p>链表中的数字是按照逆序存储的，即个位在链表头部。</p>
     * 
     * @param l1 第一个链表的头节点，表示第一个数字
     * @param l2 第二个链表的头节点，表示第二个数字
     * @return 相加后的新链表的头节点
     * @throws IllegalArgumentException 如果输入参数不符合要求
     * 
     * <p>时间复杂度：O(max(m,n))，其中m和n分别为两个链表的长度</p>
     * <p>空间复杂度：O(max(m,n))，新链表的长度最多为max(m,n)+1</p>
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 输入参数验证
        if (l1 == null || l2 == null) {
            throw new IllegalArgumentException("Input lists cannot be null");
        }

        // 验证链表长度
        validateListLength(l1);
        validateListLength(l2);

        try {
            // 创建一个哑节点作为结果链表的头部
            ListNode dummyHead = new ListNode(0);
            ListNode curr = dummyHead;
            
            // 进位值，初始为0
            int carry = 0;
            
            // 当l1或l2不为空，或者有进位时，继续循环
            while (l1 != null || l2 != null || carry != 0) {
                // 获取l1当前节点的值，如果l1为空则为0
                int x = (l1 != null) ? l1.val : 0;
                
                // 获取l2当前节点的值，如果l2为空则为0
                int y = (l2 != null) ? l2.val : 0;
                
                // 计算当前位的和，包括上一位的进位
                int sum = x + y + carry;
                
                // 计算新的进位值
                carry = sum / 10;
                
                // 创建新节点，值为当前位的值（对10取余）
                curr.next = new ListNode(sum % 10);
                curr = curr.next;
                
                // 移动l1和l2到下一个节点，如果存在的话
                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;
            }
            
            // 验证结果链表长度不超过100
            validateListLength(dummyHead.next);
            
            // 返回结果链表的头节点（跳过哑节点）
            return dummyHead.next;
        } catch (Exception e) {
            System.err.println("Error in addTwoNumbers: " + e.getMessage());
            throw e; // 重新抛出异常，让调用者处理
        }
    }
    
    /**
     * 创建链表的辅助方法
     * 
     * <p>从整数数组创建一个链表，每个数组元素对应链表中的一个节点。</p>
     * 
     * @param digits 表示数字的数组，每个元素应该在[0,9]范围内
     * @return 创建的链表头节点
     * @throws IllegalArgumentException 如果输入不符合要求
     */
    public static ListNode createList(int[] digits) {
        if (digits == null || digits.length == 0) {
            throw new IllegalArgumentException("Digits array cannot be null or empty");
        }
        
        if (digits.length > 100) {
            throw new IllegalArgumentException("List length must not exceed 100 nodes");
        }
        
        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        
        for (int digit : digits) {
            if (digit < 0 || digit > 9) {
                throw new IllegalArgumentException("Each digit must be between 0 and 9, got: " + digit);
            }
            current.next = new ListNode(digit);
            current = current.next;
        }
        
        return dummyHead.next;
    }
    
    /**
     * 将链表转换为整数的辅助方法
     * 
     * <p>将链表表示的逆序数字转换为整数值。例如，链表 7->0->8 表示数字 807。</p>
     * <p>注意：此方法仅适用于小型链表，因为大数可能会导致长整型溢出。</p>
     * 
     * @param head 链表的头节点
     * @return 链表表示的整数
     */
    public static long listToNumber(ListNode head) {
        if (head == null) {
            return 0;
        }
        
        long result = 0;
        long multiplier = 1;
        ListNode current = head;
        
        while (current != null) {
            result += current.val * multiplier;
            multiplier *= 10;
            current = current.next;
        }
        
        return result;
    }
    
    /**
     * 将链表转换为字符串表示
     * 
     * <p>生成链表的字符串表示，格式为：val1->val2->...->valN</p>
     * 
     * @param head 链表的头节点
     * @return 链表的字符串表示
     */
    public static String toString(ListNode head) {
        if (head == null) {
            return "Empty list";
        }
        
        StringBuilder sb = new StringBuilder();
        ListNode current = head;
        while (current != null) {
            sb.append(current.val);
            if (current.next != null) {
                sb.append("->");
            }
            current = current.next;
        }
        
        return sb.toString();
    }
    
    /**
     * 主方法，提供简单的使用示例
     */
    public static void main(String[] args) {
        try {
            // 创建示例链表
            ListNode l1 = createList(new int[]{2, 4, 3});  // 表示数字342
            ListNode l2 = createList(new int[]{5, 6, 4});  // 表示数字465
            
            System.out.println("示例：");
            System.out.println("第一个数 (逆序): " + toString(l1) + " (实际值: " + listToNumber(l1) + ")");
            System.out.println("第二个数 (逆序): " + toString(l2) + " (实际值: " + listToNumber(l2) + ")");
            
            // 执行加法运算
            AddTwoNumbers solution = new AddTwoNumbers();
            ListNode result = solution.addTwoNumbers(l1, l2);
            
            System.out.println("结果 (逆序): " + toString(result) + " (实际值: " + listToNumber(result) + ")");
            System.out.println("验证: " + listToNumber(l1) + " + " + listToNumber(l2) + " = " + listToNumber(result));
            
        } catch (Exception e) {
            System.err.println("示例执行出错: " + e.getMessage());
        }
    }
}
