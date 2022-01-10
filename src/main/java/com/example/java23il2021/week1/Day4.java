package com.example.java23il2021.week1;

/**
 *  synchronized
 *
 *   T2, T3, T4(Blocked),
 *       |
 *  synchronized(Object) {
 *      wait(time);          ->   waiting list ->
 *      notify();
 *  }
 *
 *  example 1:
 *  synchronized(obj1) {
 *      wait()
 *  }
 *  synchronized(obj1) {
 *      notify()
 *  }
 *
 *  example 2:
 *  synchronized(obj1) {
 *      wait()
 *  }
 *  synchronized(obj2) {
 *      notify()
 *  }
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   1. multiple waiting list
 *   2. try synchronized / try lock
 *   3. fair lock
 *   4. lock in one method + unlock in another method
 *
 *   volatile + CAS
 *
 *   class MyLock implements Lock{
 *      private volatile int status = 0;
 *      private volatile Thread holder;
 *      public void lock() {
 *           if(CAS(status, expect = 0,  new value = 1)) {
 *               //no one holding lock
 *               holder = Thread.currentThread();
 *           } else if(Thread.currentThread() == holder){
 *               //current thread locked it
 *               status++;
 *           } else {
 *               //blocked queue
 *               //fair lock
 *               if(queue is not empty) {
 *                  // Node(Thread1) -> Node(Thread2) -> Node(Thread3) -> Node(Thread4)
 *                  add current T4 into queue
 *               }
 *               //unfair lock
 *               // Node(Thread1) -> Node(Thread2) -> Node(Thread3) -> Node(Thread4)
 *               // T5 current thread
 *               // T5 get lock
 *               if(CAS(status, expect = 0,  new value = 1)) {
 *                   T5 get lock
 *               } else {
 *                   add T5 into blocked queue
 *               }
 *           }
 *      }
 *
 *      public void unlock() {
 *           ..
 *      }
 *   }
 *
 *   MyLock lock = new MyLock();
 *   T1(lock), T2(lock)
 *   lock.lock();
 *   lock.lock();
 *   ..
 *   lock.unlock();
 *   lock.unlock();
 *
 *   ************************************************
 *    producer  ->  queue  ->  consumer
 *   queue is empty ?  consumer need to wait
 *   queue is full ?   producer need to wait
 *
 *
 *   class MyBlockingQueue<E> {
 *       private final Object[] queue;
 *       private final ReentrantLock lock = new ReentrantLock();
 *       private final Condition isFull = lock.newCondition();
 *       private final Condition isEmpty = lock.newCondition();
 *       private int tailElementIdx = 0;
 *       private int headElementIdx = 0;
 *       private int size = 0;
 *
 *       public MyBlockingQueue(int size) {
 *           queue = new Object[size];
 *       }
 *
 *       //consumer wants to poll element from queue
 *       public E poll() {
 *           lock.lock();
 *           try {
 *               while(size == 0) {
 *                   isEmpty.await();
 *               }
 *               E res = (E)queue[headElementIdx];
 *               headElementIdx = (headElementIdx + 1) % queue.length;
 *               size--;
 *               isFull.signal();
 *           } finally {
 *               lock.unlock();
 *           }
 *       }
 *
 *       //producer want s to add element into queue
 *       public void offer(E e) {
 *           lock.lock();
 *           try {
 *               while(size == queue.length) {
 *                   isFull.await();
 *               }
 *               queue[tailElementIdx] = e;
 *               tailElementIdx = (tailElementIdx + 1) % queue.length;
 *               size++;
 *               isEmpty().signal();
 *           } finally {
 *               lock.unlock();
 *           }
 *       }
 *   }
 *
 *   ConcurrentHashMap vs HashTable
 *   CopyOnWriteList
 *   ThreadPool
 *   DelayQueue
 *   Semaphore
 *   CountdownLatch
 *   CyclicBarrier
 */