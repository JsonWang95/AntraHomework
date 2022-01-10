package com.example.java23il2021.week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * instance vs class
 * oop
 *      polymorphism : overriding and overloading
 *      encapsulation : private, default, protected, public
 *      inheritance : extends class single inheritance / interface impl, extends multi inheritance
 *      abstraction :
 *          abstract class
 *
 *          interface
 */
class Day1ObjectTest {
    public static void main(String[] args) {
        List<Integer> obj = new ArrayList<>();
    }
}

/**
 * overriding + inheritance
 */
class Day1A {
    void print() {
        System.out.println("this is A");
    }

    public void print(int a) {

    }
}

class Day1B extends Day1A {

    /**
     * 1. runtime error
     * 2. this is B,  this is A
     * 3. this is B
     * 4. this is A
     */
    public static void main(String[] args) {
        new Day1B().print();
    }
}

/**
 *      interface + default method
 */
interface Day1C {
    int a = 5;
    default void print() {

    }
}
/**
 *      constructor
 */

class Day1ConstructorTest {
    public Day1ConstructorTest(int a) {
        System.out.println("this is parent");
    }
}

class Day1ConstructorSubTest extends Day1ConstructorTest {
    public Day1ConstructorSubTest() {
        super(5);
        System.out.println("this is sub");
    }

    public static void main(String[] args) {
        new Day1ConstructorSubTest();
    }
}
/**
 *  * static
 */

class Day1StaticTest {
    private static int a = 5;
    public void print() {

    }

    public static void main(String[] args) {
        new Day1StaticTest().print();
        System.out.println(Day1StaticTest.a);
    }
}
/**
 *  * pass by value
 */
class Day1PassByValue {
    /**
     *   list1  ->  [1, 2, 3]
     *   list2 -> [1, 2, 3] <- list1
     *
     */
    //0x777 list2[0x111]
    public static void helper(List<Integer> list2) {
//        0x777 list2[88x0]   -> 88x0 []
//        list2 = new ArrayList<>();
    }

    private static void helper(int a) {
        a = 5;
    }
    //list1 ->
    //          obj
    //list2 ->
    //

    public static void main(String[] args) {
        //0x555 list1[0x111]   ->  0x111  [1, 2, 3]
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        //helper(0x111)
        helper(list1);
        System.out.println(list1);

        int a = 10;
        helper(a);
        System.out.println(a);
    }
}
/**
 *  * final
 */
class Day1Student {
    public String name;
}
class Day1FinalTest {
    private static final Day1Student stu = new Day1Student();

    public static void main(String[] args) {
        stu.name = "Tom";
    }
}
 /**
  * immutable(String / Integer..)
  *
  * constant string pool("abc")
  * */
 class Day1StringTest {
     public static void main(String[] args) {
//         String s1 = "abc";
//         String s2 = "abc";
//         String s3 = new String("abc");
//         String s4 = "a" + "bc";
//         String s5 = "a";
//         s5 = s5 + "bc";
////         System.out.println(s1 == s3);
////         System.out.println(s1.equals(s3));
//         System.out.println(s1 == s5);
         Integer v1 = 1;
         Integer v2 = 1;
         System.out.println(v1 == v2);
         Integer v3 = 128;
         Integer v4 = 128;
         System.out.println(v3.equals(v4));
     }
 }
 /**
 *  * exception + finally
  *
  *  Runtime exception / Compile time exception / error / Throwable
  *           Throwable
  *        /            \
  *     Error          Exception(compile time exception)
  *                         \
  *                      RuntimeException
  *
  *  Runtime exception(NullPointerException) vs Compile time exception(IOException)
 */
 class Day1ExceptionTest {
     public static void print() {
         throw new RuntimeException("..");
     }
     public static void main(String[] args) {
         try {
             print();
         } catch (RuntimeException ex) {
             //logging in file
             throw ex;
         } catch (Exception ex) {

         } finally {

         }

         try {

         } finally {

         }
     }
 }

 /**
  *     diff final vs finally vs finalize(phantom reference + reference queue)
  */


 /*
 public String reverse(String s) {
    null
    StringBuilder res = new StringBuilder();
    char[] chs = s.toCharArray();
    for(int i = s.length() - 1; i >= 0; i--) {
        res.append(s.charAt(i));
    }
    for(int l = 0, r = chs.length - 1; l < r; l++, r--) {

    }
    return res.toString();
    return new String(chs);
 }
  */


/**
 *  generic
 *  collections (list, map, set)
 *  array vs arraylist vs linkedlist
 *  hashmap(equals + hashcode) vs treemap(comparator + comparable)
 *  hashset vs treeset
 *  priorityqueue / queue / stack / deque
 */

class SuperA {
    public SuperA() {
    }

    public SuperA(int a) {
    }
}
class SuperB extends SuperA {

    public SuperB() {
        super();
    }

    public static void main(String[] args) {
        new SuperB();
    }
}