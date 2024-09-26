package org.techinfinities.practice.IBM.codingAssesment;

import java.util.*;

public class MaximumDistinct {

    /**
     *
     * Problem Statement:
     * a = {2,2,3,3}
     * b = {1,1,3,2,2,4}
     * k = 2
     *
     * Now we have to return maximum distinct in "a" Array if we do k swap from b to a Array:
     *
     * For Example:
     *  if we swap i=0 from a to j=1 then a will be [1.2.3.3]
     *  now for the second operation
     *  we swap i=3 from a to j=5 from b then a will be [1,2,3,4]
     *  and that's the maximum distinct value we can get for a Array by 2 move.
     *
     *  so we have to return 4.
     * */

    //private final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
       List<Integer> a = new ArrayList<Integer>(
               Arrays.asList(1,1,1,1,1,1,1,1,1,1,1,1)
       );
       List<Integer> b = new ArrayList<>(
               Arrays.asList(1,2,3,4,5,6)
       );
       int k = 2;
       System.out.println(getMaximumDistinct(a,b,k));
    }

    private static int getMaximumDistinct(List<Integer> a, List<Integer> b, int k) {

        // So the idea is like first we will create a freq map and then we will create a priority
        // que max heap for b array and if

        // base cae:
        if(a.isEmpty()) {
            return  0;
        }

        HashMap<Integer,Integer> numToFreq = new HashMap<>();

        for (Integer i : a) {
            numToFreq.put(i, numToFreq.getOrDefault(i, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer,Integer>> pq = new PriorityQueue<>(
                (x,y) -> y.getValue() - x.getValue()
        );

        for(Map.Entry<Integer,Integer> entry : numToFreq.entrySet()) {
            pq.offer(entry);
        }


        int i = 0;

        while (i < b.size()) {
            if(k <= 0) {
                break;
            }
            if(!a.contains(b.get(i))) {
                int element = b.get(i);
                if(numToFreq.get(pq.peek().getKey()) > 1) {
                    // get the first element from pq
                    Map.Entry<Integer, Integer> entry = pq.poll();
                    int tempKey = entry.getKey();
                    int tempValue = entry.getValue();
                    entry.setValue(tempValue - 1);
                    pq.offer(entry);
                    a.remove(tempKey);
                    b.add(tempKey);

                    a.add(element);
                    numToFreq.put(element, numToFreq.getOrDefault(element, 0) + 1);
                    Map.Entry<Integer, Integer> entry1 =
                            numToFreq.entrySet().stream()
                                    .filter(x -> x.getKey().equals(element))
                                    .iterator().next();
                    pq.offer(entry1);
                    b.remove(i);
                    b.add(tempKey);
                    k--;
                } else {
                    break;
                }
            }
            i++;
        }

        return pq.size();
    }

}