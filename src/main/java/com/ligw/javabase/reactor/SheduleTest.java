package com.ligw.javabase.reactor;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SheduleTest
 * @Description: TODO
 * @Author: ligangwei
 * @CreateDate: 2021/4/28 11:13
 */
public class SheduleTest {
    public static void main(String[] args) {

        // Flux是一个多元素的生产者，它可以生产多个元素，组成元素序列，供订阅者使用。
        Flux<Integer> flux = Flux.range(0, 10);
        flux.subscribe(i -> {
            System.out.println("run1: " + i);
        });
        flux.subscribe(i -> {
            System.out.println("run2: " + i);
        });

        // Mono和Flux的区别在于，它只能生产一个元素供生产者订阅，也就是数量的不同。
        // Mono的一个常见的应用就是Mono<ServerResponse>作为WebFlux的返回值。毕竟每次请求只有一个Response对象，所以Mono刚刚好。
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");
        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);

        Mono<String> noData = Mono.empty();
        Mono<String> data = Mono.just("foo");

        Flux<Integer> numbersFromFiveToSeven = Flux.range(5, 3);


        Flux<Integer> integerFlux = Flux.range(0, 10);
        integerFlux.subscribe(i -> {
            System.out.println("run");
            System.out.println(i);
        }, error -> {
            System.out.println("error");
        }, () -> {
            System.out.println("done");
        }, p -> {
            p.request(2);
        });


        Schedulers.parallel().schedule(() -> {
            System.out.println("当前线程是：" + Thread.currentThread().getName());
            System.out.println("6541");
        });
        Schedulers.parallel().schedule(() -> {
            System.out.println("当前线程是：" + Thread.currentThread().getName());
            System.out.println("9874");
        });
    }
}
