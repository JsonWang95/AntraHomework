package com.example.java23il2021.week1;

import java.util.*;

/**
 *  generic
 *  collections (list, map, set)
 *  array vs arraylist vs linkedlist
 *  hashmap(equals + hashcode) vs treemap(comparator + comparable)
 *  hashset vs treeset
 *  priorityqueue / queue / stack / deque
 */

class Day2Generic1<T> {
    T val;

    public static void main(String[] args) {
//        Day2Generic1<Integer> instance1 = new Day2Generic1<>();
//        instance1.val = "abc";
//        Day2Generic1 instance2 = new Day2Generic1();
//        instance2.val = "abc";
    }
}

class Day2Generic2 {
    Object val;
}

class Day2Generic3<E> {
    E val;
}

/**
 *  Array vs ArrayList
 *
 *  arraylist  [1, 2, 3, 4]
 *      add(index:0, element:5)
 *          grow() -> get larger array
 *          [5, 1, 2, 3, 4]
 *      add(element: 5)
 *          grow -> get larger array
 *          [5, 1, 2, 3, 4, 5]
 *      get(index) -> O(1)
 *  linkedlist
 *      Node(1) <-> Node(2) <-> Node(3)
 */

class Day2ArrayTest {
    public static void main(String[] args) {
//        List<Integer> list1 = new ArrayList<>();
//        List<Integer> list2 = new ArrayList<>();
//        int[] arr1 = new int[10];
//        Integer[] arr2 = new Integer[10];
        System.out.println(Arrays.asList(1, 2, 3, 4));
        System.out.println(new int[]{1, 2, 3, 4});
    }
}

/**
 *  hashcode vs equals
 *
 *  hashing
 *
 *
 *  hash(customer id) % length
 *      0 % 3 = 0
 *      1 % 3 = 1
 *      2 % 3 = 2
 *      3 % 3 = 0
 *      4 % 3 = 1
 *
 *
 *      0 % 4 = 0
 *      1 % 4 = 1
 *      2 % 4 = 2
 *      3 % 4 = 3
 *      4 % 4 = 0
 *  storage0,   storage1,   storage2,   storage3
 *    5           6            4
 *
 *
 *    hashmap:
 *      1. put(k, v)
 *      2. putVal(hash(key), key, value, false, true)
 *            hash = hash(key) -> based on key's hashcode
 *      3. lazy initialize Node<K, V>[] table
 *      4. get index i from hash & length of table
 *      5. if table[i] == null
 *              table[i] = new Node(k, v);
 *         else
 *              firstNode = table[i];
 *              if(== || equals)
 *                  firstNode.value = v;
 *                  return;
 *              else if(firstNode is tree node)
 *                  look up red black tree(firstNode)
 *              else
 *                  for(int binCount = 0;; binCount++)
 *                      if(== || equals)
 *                           node.value = v;
 *                           break;
 *                      if(next node is null)
 *                           node.next = new Node(k, v);
 *                           convert to red black tree(..)
 *                           break;
 *                      node = node.next;
 *         size++;
 *         resize();
 *
 *   only override equals
 *         map.put(s1, 5);    [][][][s1, 5][][]
 *         map.put(s2, 10);   case1: [][][][s1, 10][][]
 *                            case2: [s2, 10][][][s1, 5][][]
 *         System.out.println(map.get(s3));
 *                      return 5 / 10 / null
 *   not overriding any of them
 *         map.put(s1, 5);
 *         map.put(s2, 10);
 *         System.out.println(map.get(s3));
 *                      100% return null
 *  only override hashcode
 *         map.put(s1, 5);
 *         map.put(s2, 10);
 *         System.out.println(map.get(s3));
 *                      100% return null
 */
class Day2Student {
    int id;

    public Day2Student(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day2Student that = (Day2Student) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
class Day2HashMap {
    public static void main(String[] args) {
        Day2Student s1 = new Day2Student(1);
        Day2Student s2 = new Day2Student(1);
        Day2Student s3 = new Day2Student(1);
        Map<Day2Student, Integer> map = new HashMap<>();
        map.put(s1, 5);   //[][][Node(s1, 5)][][]
        map.put(s2, 10);  //[][][Node(s1, 10)][][]
//        s1.id = 10;     //s1.id = 10,  s3.equals(s1) -> false
        s2.id = 10;       //s2.id = 10
        System.out.println(map.get(s3));

        TreeMap<Day2Student, Integer> treeMap = new TreeMap<>((v1, v2) -> v1.id - v2.id);
        treeMap.put(s1, 10);
        System.out.println(treeMap.get(s2));

    }
}


/**
 * code review section
 */

class Day2Solution {
    // "aac" -> 'c'
    // "acdac" -> 'd'
    // "abcd" -> 'a'
    // "" / "aa" -> null

    public static Character firstUniqueCharacter(String str) {
        if(str == null) {
            throw new IllegalArgumentException("input cannot be null");
        }
        Map<Character, Integer> cnt = new LinkedHashMap<>();
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            cnt.put(ch, cnt.getOrDefault(ch, 0) + 1);
        }
        //solution 1
//        for(int i = 0; i < str.length(); i++) {
//            char ch = str.charAt(i);
//            if(cnt.get(ch) == 1) {
//                return ch;
//            }
//        }
        //solution2 LinkedHashMap
//        for(char ch: cnt.keySet()) {
//            if(cnt.get(ch) == 1) {
//                return ch;
//            }
//        }
        //solution3 LinkedHashMap
//        for(Map.Entry<Character, Integer> entry: cnt.entrySet()) {
//            if(entry.getValue() == 1) {
//                return entry.getKey();
//            }
//        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(firstUniqueCharacter("acdac"));
        System.out.println(firstUniqueCharacter("aac"));
        System.out.println(firstUniqueCharacter("abcd"));
        System.out.println(firstUniqueCharacter(null));
    }
}

/**
 *  stack
 *  heap
 *  Thread
 *  volatile
 *
 *  CAS
 *  synchronized
 */
