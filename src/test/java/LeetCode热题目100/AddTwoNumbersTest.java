package LeetCode热题目100;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import LeetCode热题目100.AddTwoNumbers.ListNode;

/**
 * AddTwoNumbers的单元测试类
 *
 * @author ZT
 * @version 1.0.0
 */
class AddTwoNumbersTest {

    private final AddTwoNumbers solution = new AddTwoNumbers();

    @Test
    @DisplayName("测试标准情况：342 + 465 = 807")
    void testNormalCase() {
        // 准备测试数据
        ListNode l1 = AddTwoNumbers.createList(new int[]{2, 4, 3});  // 342
        ListNode l2 = AddTwoNumbers.createList(new int[]{5, 6, 4});  // 465

        // 执行测试
        ListNode result = solution.addTwoNumbers(l1, l2);

        // 验证结果
        assertEquals(807, AddTwoNumbers.listToNumber(result));
        assertEquals("7->0->8", AddTwoNumbers.toString(result));
    }

    @Test
    @DisplayName("测试不同长度链表：9999 + 99 = 10098")
    void testDifferentLengths() {
        ListNode l1 = AddTwoNumbers.createList(new int[]{9, 9, 9, 9});  // 9999
        ListNode l2 = AddTwoNumbers.createList(new int[]{9, 9});        // 99

        ListNode result = solution.addTwoNumbers(l1, l2);

        assertEquals(10098, AddTwoNumbers.listToNumber(result));
        assertEquals("8->9->0->0->1", AddTwoNumbers.toString(result));
    }

    @Test
    @DisplayName("测试空链表")
    void testNullInput() {
        ListNode l1 = AddTwoNumbers.createList(new int[]{1, 2, 3});
        ListNode l2 = null;

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> solution.addTwoNumbers(l1, l2)
        );

        assertEquals("Input lists cannot be null", exception.getMessage());
    }

    @Test
    @DisplayName("测试节点值超出范围")
    void testInvalidNodeValue() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> AddTwoNumbers.createList(new int[]{1, 2, 10})
        );

        assertTrue(exception.getMessage().contains("Each digit must be between 0 and 9"));
    }

    @Test
    @DisplayName("测试最大长度限制")
    void testMaxLength() {
        int[] digits = new int[101]; // 创建一个长度为101的数组
        for (int i = 0; i < digits.length; i++) {
            digits[i] = 1;
        }

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> AddTwoNumbers.createList(digits)
        );

        assertEquals("List length must not exceed 100 nodes", exception.getMessage());
    }

    @Test
    @DisplayName("测试零值输入：0 + 0 = 0")
    void testZeroInput() {
        ListNode l1 = AddTwoNumbers.createList(new int[]{0});
        ListNode l2 = AddTwoNumbers.createList(new int[]{0});

        ListNode result = solution.addTwoNumbers(l1, l2);

        assertEquals(0, AddTwoNumbers.listToNumber(result));
        assertEquals("0", AddTwoNumbers.toString(result));
    }


}
