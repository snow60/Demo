package com.zt.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description TODO
 * @Author ZT
 * @Date 2024/5/31 14:58
 * @Version V1.0.0
 * @Company 宁波捷创
 */
public class ListTest {
    public static void main(String[] args) {

        // 不要在foreach循环里进行元素的remove / add操作。remove元素请使用iterator方式， 如果并发操作，需要对iterator对象加锁。 正例：
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("2".equals(item)) {
                iterator.remove();
            }
        }
        // 反例
        for (String item : list) {
            if ("1".equals(item)) {
                list.remove(item);
            }
        }
        System.out.println(list);
        // 说明：反例中的执行结果肯定会出乎大家的意料，那么试一下把“1”换成“2”会是同样的结果吗？
    }
}
