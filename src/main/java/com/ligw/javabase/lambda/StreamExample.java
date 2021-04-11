package com.ligw.javabase.lambda;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {

    /**
     * stream常见操作api
     * 1. 聚合操作
     * 2. stream的处理流程
     *  a. 获取数据源
     *  b. 进行数据转换
     *  c. 获取结果
     * 3. 获取stream对象
     *  a. 从集合或数组中获取
     *      Collection.stream()
     *      Collection.parallelStream() // 获取可并发处理的stream
     *      Arrays.stream(T t)
     *  b. BufferReaderr
     *      BufferReader.lines()
     *  c. 静态工厂
     *      java.util.stream.IntStream.range()
     *      java.nio.file.File.walk()
     *  d. 自定义构建
     *      java.util.Spliterator
     *  e. 更多方式
     *      Random.ints()
     *      Pattern.splitAsStream()
     * 4. 中间操作API
     *  操作结果是一个stream,中间操作可以有一个或多个连续的中间操作，
     *  需要注意的是中间操作只记录操作方式，不做具体执行，直到结束操作发生时，才做数据的最终执行。
     *  中间操纵就是业务逻辑处理
     *  中间操作过程：
     *      无状态：数据处理时，不受前置中间操作的影响，如： map/filter/peek/parallel/sequential/unordered
     *      有状态：数据处理时，受到前置中间操作的影响，如：distinct/sorted/limit/skip
     * 5. 结束操作/终结操作(Terminal)
     *  一个stream对象之恩有一个Terminal操作，这个操作一旦发生，就会真实处理数据，生成对应的结果
     *  终结操作分类：
     *      非短路操作：当前Stream对象必须处理完集合中所有数据才能得到处理结果
     *          forEach/forEachordered/toArray/reduce/collect/min/max/count/iterator
     *      短路操作(short-circuiting)：当前stream对象在处理过程中，一旦满足某个条件，就可以得到结果
     *          anyMatch/allMatch/noneMatch/findFirst/findAny
     */

    public static void main(String[] args) {
//        streamFilterTest();
//        any2stream2any();
        streamApiShow();

    }

    /**
     * strem中常见的api操作
     *
     */
    private static void streamApiShow() {
        String[] strArray = new String[]{"0", "", "1", "23", " ", "1", "abc"};

        // 1. map(Function<? super T, ? extends R> mapper)  无状态中间操作-自定义函数返回新的值
        List<String> collect = Arrays.stream(strArray).map(x -> "char:" + x).collect(Collectors.toList());
        System.out.println(collect);

        // 2. filter(Predicate<? super T> predicate) 无状态中间操作-自定义函数返回boolean
        List<String> collect1 = Arrays.stream(strArray).filter(x -> x.length() > 1).collect(Collectors.toList());
        System.out.println(collect1);

        // 3. forEach(Consumer<? super T> action) 无状态中间操作：增强型循环
        Map<String, Integer> strLengthMap = new HashMap<>();
        Arrays.stream(strArray).forEach((obj) -> {
            obj = "*" + obj + "^";
            strLengthMap.put(obj, obj.length());
        });
        System.out.println(strLengthMap);
        System.out.println(strArray[3]);
        // 增强型循环与增强型for循环不一样，支持方法引用的操作
        Arrays.stream(strArray).forEach(System.out::println);

        // 4. peek(Consumer<? super T> action) 无状态中间操作，适用于迭代数据完成数据数据的依次处理过程
        Arrays.stream(strArray)
                .peek(obj -> System.out.println("peek1:" + obj))
                .peek(obj -> System.out.println("peek2:" + obj))
                .forEach(System.out::println);

        // stream中对于数字运算的支持
        Integer[] intArray = new Integer[]{1, 0, 5, 10, 4, 16, 200, 7, 7, 88};

        // 5. skip(long n) 有状态的中间操作-跳过一部分数据
        Arrays.stream(intArray).skip(3).forEach(System.out::println);

        // 6.  limit(long maxSize) 有状态的中间操作 - 限制输出数据量
        Arrays.stream(intArray).skip(4).limit(6).forEach(System.out::println);

        // 7. distinct() 有状态的中间操作 - 剔除重复数据(依据Hash值判定是否重复)
        Arrays.stream(intArray).skip(4).limit(6).distinct().forEach(System.out::println);

        // 8. sorted() 有状态的中间操作 - 排序，包装类需自定义排序规则
        Arrays.stream(strArray).sorted((o1, o2) -> o2.hashCode()-o1.hashCode()).forEach(obj -> System.out.println(obj.hashCode()));

        // 9. OptionalInt min() 非短路终结操作-最小值 这里重写了排序规则，把最大的弄成了最小的
        Optional min = Arrays.stream(intArray).min((o1, o2) -> o2 - o1);
        System.out.println(min.isPresent() + ":" + min.get());

        // 10. OptionalInt max() 非短路终结操作-最大值 正序
        Optional max = Arrays.stream(intArray).max((o1, o2) -> o1 - o2);
        System.out.println(max.isPresent() + ":" + max.get());

        // 11. reduce(BinaryOperator<T> accumulator) 非短路终结操作-求和
        Optional<Integer> reduce = Arrays.stream(intArray).reduce((sum, x) -> sum + x);
        System.out.println(reduce.isPresent() + ":" + reduce.get());

        // 12 并行流 TODO parallelStream vs stream.parallel
        List<String> strList = Arrays.asList(strArray);
        strList.stream().parallel().forEach(obj -> System.out.print(Thread.currentThread().getId() + ":" + obj+";"));
        strList.parallelStream().forEach(obj -> System.out.print(Thread.currentThread().getId() + ":" + obj+"|"));

        // 13 串行流
        Arrays.stream(strArray).sequential().forEach(obj -> System.out.print(Thread.currentThread().getId() + ":" + obj+"#"));

        // 14 乱流(不改变值)
        Arrays.stream(strArray).unordered().forEach(obj -> System.out.print(Thread.currentThread().getId() + ":" + obj+"%"));
    }

    /**
     * stream与常见类型的相互转换
     */
    private static void any2stream2any() {
        // any -> stream
        // 1.多个数据
        Stream stream1 = Stream.of("1", "2");

        // 2. 数组
        Stream stream2 = Arrays.stream(new String[]{"1", "2"});

        // 3. 列表
        List<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        Stream<String> stream3 = arrayList.stream();

        // 4. 集合
        Set<String> hashSet = new HashSet<>();
        hashSet.add("1");
        hashSet.add("2");
        Stream<String> stream4 = hashSet.stream();

        // 4. Map
        Map<String, String> hashMap = new HashMap();
        hashMap.put("1", "a");
        hashMap.put("2", "b");
        Stream<Map.Entry<String, String>> stream5 = hashMap.entrySet().stream();

        // stream对象对于基本数据类型的功能封装 : 旨在最终一次操作过程中完成一次装箱一次拆箱操作
        IntStream.of(new int[]{10, 20, 30}).forEach(System.out::println);
        IntStream.range(1, 10).forEach(System.out::println);
        IntStream.rangeClosed(1, 10).forEach(System.out::println);

        // stream -> any
        String[] strArray = new String[]{"0", "", "1", "23", " ", "1", "abc"};
        // 1. 数组
        Stream streama = Arrays.stream(strArray);
        Object[] objects = streama.toArray();
        System.out.println(objects[3]);
        Stream<String> streamb = Arrays.stream(strArray);
        String[] strings = streamb.toArray(String[]::new);
        System.out.println(strings[1]);

        // 2. 字符串
        Stream<String> streamc = Arrays.stream(strArray);
        String str = streamc.collect(Collectors.joining()).toString();
        System.out.println(str);

        // 3. 列表 底层使用ArrayList的add/addAll方法
        // ！ [可以发现都是用Collectors的内置函数进行转换的]
        Stream<String> streamd = Arrays.stream(strArray);
        List<String> collect = streamd.collect(Collectors.toList());
        System.out.println(collect);

        // 4. 集合 底层使用HashSet的add/addAll方法
        Stream<String> streame = Arrays.stream(strArray);
        Set<String> collect1 = streame.collect(Collectors.toSet());
        System.out.println(collect1);

        // 5. Map 会有key重复的情况，需要自己写方法合并key
        // 合并key后的value取值代码:java.util.Map.merge
        Stream<String> streamf = Arrays.stream(strArray);
        Map<String, Integer> collect2 = streamf.collect(Collectors.toMap(obj -> obj, obj -> obj.length(), (o1, o2) -> o1));
        System.out.println(collect2);
    }

    /**
     * stream的filter方法
     */
    private static void streamFilterTest() {
        List<String> accountList = new ArrayList<>();
        accountList.add("lulcy");
        accountList.add("lili");
        accountList.add("pusuli");

        // 需求： 筛选出账号长度大于等于5的账号
        int minValidLength = 5;

        // for
        for (String account : accountList) {
            if (minValidLength <= account.length()) {
                System.out.println(account);
            }
        }

        // iter
        Iterator<String> it = accountList.iterator();
        while (it.hasNext()) {
            String account = it.next();
            if (minValidLength <= account.length()) {
                System.out.println(account);
            }
        }

        // streasm
        List<String> validAccountList = accountList.stream().filter(account -> minValidLength >= account.length())
                .collect(Collectors.toList());
        System.out.println(validAccountList);
    }
}
