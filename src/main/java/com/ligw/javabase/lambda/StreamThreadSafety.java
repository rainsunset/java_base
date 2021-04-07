package com.ligw.javabase.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: stream线程安全
 * @Author: Amo
 * @CreateDate: 2021/3/31
 */
public class StreamThreadSafety {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            intList.add(i);
        }
        System.out.println(intList.size());

        // 串行stream
        List<Integer> list2 = new ArrayList<>();
        intList.stream().forEach(obj -> list2.add(obj));
        System.out.println(list2.size());

        // 并行stream
        List<Integer> list3 = new ArrayList<>();
        intList.parallelStream().forEach(obj -> list3.add(obj));
        System.out.println(list3.size());

        // 并行strem使用的collections本身就是线程不安全的，在多线程操作下会出现数据不一致的错误。
        // 解决办法： collections本身提供的线程同步块儿完成对于数据的同步处理，但是使用collection提供的线程同步块儿会有线程竞争的问题，
        //              如果想避免线程竞争的问题，聚合操作和parallelStream结合在一起使用时，对于非线程安全的集合中的数据不能进行修改。

        // collect(): 当并行执行时，可以实例化，填充和合并多个中间结果，以便保持可变数据结构的隔离。因此，技术与非线程安全的数据结构(arrayList)并行执行，并行还原也不需要额外的同步
        List<Integer> list4 = intList.parallelStream().collect(Collectors.toList());
        System.out.println(list4.size());

        // TODO parallelStream 线程安全的更多问题
    }
}
