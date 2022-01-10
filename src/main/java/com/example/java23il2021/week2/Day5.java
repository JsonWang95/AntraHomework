package com.example.java23il2021.week2;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Thread pool
 *      1. fix thread pool -> core pool size = max pool size
 *      2. cached thread pool -> core pool size < max pool size
 *      3. scheduled thread pool -> delayed work queue
 *      4. fork join pool
 *
 *
 * ThreadPoolExecutor
 *      [][][]blockingqueue[][][] -> worker1
 *                                -> worker2
 *      1. min pool size / core pool size
 *      2. max pool size
 *      3. alive time
 *      4. time unit
 *      5. blocking queue
 *      6. thread factory
 *
 * ForkJoinPool(stealing algorithm)
 *      [][][]blockingqueue[][]  -> worker1 [x1][x2][x3][]
 *                               -> worker2 [x4][][][]
 *                               -> worker3 [x3][][][]
 *
 * public void execute(String message, delayed - time)
 *
 * delayed queue / time wheel
 *
 *         Node
 *          |
 * second [Node][][Node][]..[][]   len = 60
 *          |
 *        pointer
 *
 * minute [ ][Node]
 *             |
 *        pointer
 *
 *
 *
 * Future + callable
 *
 */
class Day5FutureTest {
    private static final ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) throws Exception {
        List<Integer> num1 = Arrays.asList(2, 2, 2, 2);
        List<Integer> num2 = Arrays.asList(1, 1, 1, 1);
        System.out.println(sum(num1, num2));
        System.out.println(sum(num1, num2));
        System.out.println(sum(num1, num2));
    }

    private static int sum(List<Integer> num1, List<Integer> num2) throws Exception {
        Future<Integer> f1 = pool.submit(() -> sum(num1));
        Future<Integer> f2 = pool.submit(() -> sum(num1));
        return f1.get() + f2.get();
    }

    private static int sum(List<Integer> numbers) {
        int res = 0;
        for(int num: numbers) {
            res += num;
        }
        return res;
    }
}
/**
 * hashtable vs concurrent hashmap
 *
 * volatile
 *
 * int a = 5;
 * volatile int b = 6;
 * T1:
 *      a = 10;
 *      b = 10;
 * T2:
 *      if(b = 10) {
 *          a == 10;
 *      }
 *
 * concurrenthashmap
 * T1  : 1st..2nd....... 3rd........1000th * put(key, value)
 * T2  :           get1()                                      get2()
 *  questions
 *      1. get1() got blocked ?
 *      2. get1() == get2() ?
 *      3. get1() 100% getting 2nd put value ?
 *
 *
 *  ReentrantLock
 *      state = 0 unlocked
 *      state > 0 locked
 *  Semaphore
 *      permit
 *  CountdownLatch(50)
 *  CyclicBarrier
 */

/**
 *  Oracle live sql
 *  Tuesday: sql query
 *  Wednesday: transaction
 *  Thursday: index + tuning
 *  Friday: table design
 */
class A {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(5);
        Stream<Integer> s = l.stream();
        l.add(5);
        System.out.println(s.collect(Collectors.toList()));
    }
}