package com.ligw.lambda;

import java.util.*;

/**
 * @Description: 性能测试
 * @Author: Amo
 * @CreateDate: 2021/3/31
 */
public class StreamPerformance {

    public static void main(String[] args) {
//        dealIntArrayListPerformance();

        StreamPerformance sf = new StreamPerformance();
        int tryTimes = 20;
        Random random = new Random();
        List<List<MyProject>> projectListList = new ArrayList<>();
        for (int j = 0; j < tryTimes; j++) {
            List<MyProject> projectList = new ArrayList<>();
            for (int i = 0; i < 100000; i++) {
                projectList.add(new MyProject("MyProject" + i, i, random.nextInt(Integer.MAX_VALUE)));
            }
            projectListList.add(projectList);
        }

        // 连续测试 求平均值
        Map<String, Long> methodCostMap = new HashMap<>();
        for (int j = 0; j < tryTimes; j++) {
            List<MyProject> projectList = projectListList.get(j);
            Long projectListStream = (null == methodCostMap.get("projectListStream")) ? 0L : methodCostMap.get("projectListStream");
            methodCostMap.put("projectListStream", projectListStream + sf.projectListStream(projectList));

            Long projectListParallelStream = (null == methodCostMap.get("projectListParallelStream")) ? 0L : methodCostMap.get("projectListParallelStream");
            methodCostMap.put("projectListParallelStream", projectListParallelStream + sf.projectListParallelStream(projectList));

            Long projectListStreamThenParallel = (null == methodCostMap.get("projectListStreamThenParallel")) ? 0L : methodCostMap.get("projectListStreamThenParallel");
            methodCostMap.put("projectListStreamThenParallel", projectListStreamThenParallel + sf.projectListStreamThenParallel(projectList));

            Long projectListForLoop = (null == methodCostMap.get("projectListForLoop")) ? 0L : methodCostMap.get("projectListForLoop");
            methodCostMap.put("projectListForLoop", projectListForLoop + sf.projectListForLoop(projectList));

            Long projectListStrongForLoop = (null == methodCostMap.get("projectListStrongForLoop")) ? 0L : methodCostMap.get("projectListStrongForLoop");
            methodCostMap.put("projectListStrongForLoop", projectListStrongForLoop + sf.projectListStrongForLoop(projectList));

            Long projectListIter = (null == methodCostMap.get("projectListIter")) ? 0L : methodCostMap.get("projectListIter");
            methodCostMap.put("projectListIter", projectListIter + sf.projectListIter(projectList));
        }
        methodCostMap.entrySet().stream().forEach(obj -> System.out.println(obj.getKey() + ":" + obj.getValue()));
        // projectListStream:145
        // projectListIter:54
        // projectListParallelStream:50
        // projectListForLoop:51
        // projectListStrongForLoop:53
        // projectListStreamThenParallel:62
    }

    /**
     * intList 遍历性能测试
     */
    private static void dealIntArrayListPerformance() {
        int tryTimes = 20;
        Random random = new Random();
        List<List<Integer>> intListList = new ArrayList<>();
        for (int j = 0; j < tryTimes; j++) {
            List<Integer> intList = new ArrayList<>();
            for (int i = 0; i < 1000000; i++) {
                intList.add(random.nextInt(Integer.MAX_VALUE));
            }
            intListList.add(intList);
        }

        // 连续测试 求平均值
        StreamPerformance sf = new StreamPerformance();
        Map<String, Long> methodCostMap = new HashMap<>();
        for (int j = 0; j < tryTimes; j++) {
            List<Integer> intList = intListList.get(j);
            Long intListStream = (null == methodCostMap.get("intListStream")) ? 0L : methodCostMap.get("intListStream");
            methodCostMap.put("intListStream", intListStream + sf.intListStream(intList));

            Long intListParallelStream = (null == methodCostMap.get("intListParallelStream")) ? 0L : methodCostMap.get("intListParallelStream");
            methodCostMap.put("intListParallelStream", intListParallelStream + sf.intListParallelStream(intList));

            Long intListStreamThenParallel = (null == methodCostMap.get("intListStreamThenParallel")) ? 0L : methodCostMap.get("intListStreamThenParallel");
            methodCostMap.put("intListStreamThenParallel", intListStreamThenParallel + sf.intListStreamThenParallel(intList));

            Long intListForLoop = (null == methodCostMap.get("intListForLoop")) ? 0L : methodCostMap.get("intListForLoop");
            methodCostMap.put("intListForLoop", intListForLoop + sf.intListForLoop(intList));

            Long intListStrongForLoop = (null == methodCostMap.get("intListStrongForLoop")) ? 0L : methodCostMap.get("intListStrongForLoop");
            methodCostMap.put("intListStrongForLoop", intListStrongForLoop + sf.intListStrongForLoop(intList));

            Long intListIter = (null == methodCostMap.get("intListIter")) ? 0L : methodCostMap.get("intListIter");
            methodCostMap.put("intListIter", intListIter + sf.intListIter(intList));
        }
        methodCostMap.entrySet().stream().forEach(obj -> System.out.println(obj.getKey() + ":" + obj.getValue()));
        // intListForLoop:85
        // intListParallelStream:193
        // intListStream:337
        // intListStreamThenParallel:164
        // intListIter:94
        // intListStrongForLoop:94
    }

    private long intListStream(List<Integer> intList) {
        long start = System.currentTimeMillis();
        Optional<Integer> maxOpt = intList.stream().max(Integer::compareTo);
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(String.format("intListStream,getMax：%s,cost：%dms", maxOpt.get(), cost));
        return cost;
    }

    private long intListParallelStream(List<Integer> intList) {
        long start = System.currentTimeMillis();
        Optional<Integer> maxOpt = intList.parallelStream().max(Integer::compareTo);
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(String.format("intListParallelStream,getMax：%s,cost：%dms", maxOpt.get(), cost));
        return cost;
    }

    private long intListStreamThenParallel(List<Integer> intList) {
        long start = System.currentTimeMillis();
        Optional<Integer> maxOpt = intList.stream().parallel().max(Integer::compareTo);
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(String.format("intListStreamThenParallel,getMax：%s,cost：%dms", maxOpt.get(), cost));
        return cost;
    }

    private long intListForLoop(List<Integer> intList) {
        long start = System.currentTimeMillis();
        Integer max = Integer.MIN_VALUE;
        for (int i = 0; i < intList.size(); i++) {
            max = (max > intList.get(i)) ? max : intList.get(i);
        }
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(String.format("intListForLoop,getMax：%s,cost：%dms", max, cost));
        return cost;
    }

    private long intListStrongForLoop(List<Integer> intList) {
        long start = System.currentTimeMillis();
        Integer max = Integer.MIN_VALUE;
        for (Integer val : intList) {
            max = (max > val) ? max : val;
        }
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(String.format("intListStrongForLoop,getMax：%s,cost：%dms", max, cost));
        return cost;
    }

    private long intListIter(List<Integer> intList) {
        long start = System.currentTimeMillis();
        Integer max = Integer.MIN_VALUE;
        Iterator<Integer> iterator = intList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            max = (max > next) ? max : next;
        }
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(String.format("intListIter,getMax：%s,cost：%dms", max, cost));
        return cost;
    }

    private long projectListStream(List<MyProject> projectList) {
        long start = System.currentTimeMillis();
        Optional<MyProject> maxHotOpt = projectList.stream().max((o1, o2) -> o1.hot - o2.hot);
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(String.format("projectListStream,getMax：%s,cost：%dms", maxHotOpt.get(), cost));
        return cost;
    }

    private long projectListParallelStream(List<MyProject> projectList) {
        long start = System.currentTimeMillis();
        Optional<MyProject> maxHotOpt = projectList.parallelStream().max((o1, o2) -> o1.hot - o2.hot);
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(String.format("projectListParallelStream,getMax：%s,cost：%dms", maxHotOpt.get(), cost));
        return cost;
    }

    private long projectListStreamThenParallel(List<MyProject> projectList) {
        long start = System.currentTimeMillis();
        Optional<MyProject> maxHotOpt = projectList.stream().parallel().max((o1, o2) -> o1.hot - o2.hot);
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(String.format("projectListStreamThenParallel,getMax：%s,cost：%dms", maxHotOpt.get(), cost));
        return cost;
    }

    private long projectListForLoop(List<MyProject> projectList) {
        long start = System.currentTimeMillis();
        MyProject maxHot = projectList.get(0);
        for (int i = 0; i < projectList.size(); i++) {
            MyProject val = projectList.get(i);
            maxHot = (maxHot.hot > val.hot) ? maxHot : val;
        }
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(String.format("projectListForLoop,getMax：%s,cost：%dms", maxHot, cost));
        return cost;
    }

    private long projectListStrongForLoop(List<MyProject> projectList) {
        long start = System.currentTimeMillis();
        MyProject maxHot = projectList.get(0);
        for (MyProject val : projectList) {
            maxHot = (maxHot.hot > val.hot) ? maxHot : val;
        }
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(String.format("projectListStrongForLoop,getMax：%s,cost：%dms", maxHot, cost));
        return cost;
    }

    private long projectListIter(List<MyProject> projectList) {
        long start = System.currentTimeMillis();
        MyProject maxHot = projectList.get(0);
        Iterator<MyProject> iterator = projectList.iterator();
        while (iterator.hasNext()) {
            MyProject next = iterator.next();
            maxHot = (maxHot.hot > next.hot) ? maxHot : next;
        }
        long end = System.currentTimeMillis();
        long cost = end - start;
        System.out.println(String.format("projectListIter,getMax：%s,cost：%dms", maxHot, cost));
        return cost;
    }
}

class MyProject {
     String name;
     Integer index;
     Integer hot;

    public MyProject(String name, Integer index, Integer hot) {
        this.name = name;
        this.index = index;
        this.hot = hot;
    }

    @Override
    public String toString() {
        return "MyProject{" +
                "name='" + name + '\'' +
                ", hot=" + hot +
                '}';
    }
}

