package com.example.java23il2021.week1;


/**
 *  stack
 *  heap
 *      [eden area][s0][s1] young generation : minor GC
 *      [                 ] old generation : major GC (CMS / G1)
 *
 *      perm generation(class info) : full GC = minor + major GC
 *
 *  GC root
 *  STW
 *  CMS
 *      1. initial mark (STW)
 *      2. concurrent mark
 *      3. final mark (STW)
 *      4. concurrent sweep
 *  G1
 *      [][][][][][]
 *      [][][][][][]
 *      [][][][][][]
 *      [][][][][][]
 *  out of memory error
 *      SoftReference / WeakReference
 *      heap dump
 *      static collection
 *
 *
 *              load balancer
 *         |        \       \
 *   server1,    server2,   server3,
 *
 */



/**
 *  Thread + runnable
 *  synchronized
 *  volatile
 *  CAS
 */
class Day3ThreadTest1 {
    private static int a = 0;

    private static synchronized void increment() {
        try {
            Thread.sleep(3000);
        } catch (Exception ex) {

        }
        System.out.println("increment");
    }

    private static void decrement() {
        synchronized (Day3ThreadTest1.class) {
            try {
                Thread.sleep(3000);
            } catch (Exception ex) {

            }
            System.out.println("decrement");
        }
    }

    public static void main(String[] args) throws Exception{
        Runnable inc = () -> increment();
        Runnable dec = () -> decrement();
        Thread t1 = new Thread(inc);
        Thread t2 = new Thread(dec);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}


class Day3ThreadTest2 {
    //this
    private void increment() {
        synchronized (this) {
            try {
                Thread.sleep(3000);
            } catch (Exception ex) {

            }
            System.out.println(this);
        }
    }

    public static void main(String[] args) throws Exception{
        Day3ThreadTest2 d1 = new Day3ThreadTest2();
        Day3ThreadTest2 d2 = new Day3ThreadTest2();
        Runnable inc = () -> d1.increment();
        Runnable dec = () -> d1.increment();
        Thread t1 = new Thread(inc);
        Thread t2 = new Thread(dec);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}


class Day3ThreadTest3 {
    //this
    private void increment() {
        synchronized (this) {
            try {
                Thread.sleep(3000);
            } catch (Exception ex) {

            }
            System.out.println(this);
        }
    }
    private static synchronized void decrement() {
        try {
            Thread.sleep(3000);
        } catch (Exception ex) {

        }
        System.out.println("decrement");
    }

    public static void main(String[] args) throws Exception{
        Day3ThreadTest3 d1 = new Day3ThreadTest3();
        Runnable inc = () -> d1.increment();
        Runnable dec = () -> decrement();
        Thread t1 = new Thread(inc);
        Thread t2 = new Thread(dec);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
/**
 *      a++ -> thread safe + performance
 *
 *      CAS / Atomic instruction / Atomic operation
 *
 *      cas(object ref, offset of 'attribute', expected value, new value) -> true / false
 *
 *      do {
 *          int curValue = volatile attribute;
 *      } while( !cas(.., expected value = curValue, new value = curValue + x))
 */




/**
 *  CPU1                    CPU2
 *  L1                      L1
 *  L2                      L2
 *  L3                      L3
 *
 *      Shared main memory
 *            a = 0
 *
 *
 *  volatile
 *      1. provide visibility
 *      2. prevent reordering / happen before - rule  (barrier / fence)
 *          -------> time line
 *          write write read
 *
 */

class VolatileTest {
    private static volatile boolean flag = false;
    public static void main(String[] args) throws Exception{
        Thread t1 = new Thread(() -> {
            while(!flag) {

            }
            System.out.println("outside of while loop");
        });
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true;
            System.out.println("flag currently equals to " + flag);
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}

/**
 * tomorrow: ReentrantLock + blockingqueue
 */
