import java.util.*;

public class Solution {
    static int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length-k+1];
        PriorityQueue<Integer> PQofWindows = new PriorityQueue<>(new numberComparator());
        int startIdx = 0;
        int endIdx = startIdx+k;
        int resIdx = 0;
        for (int i = startIdx; i < endIdx; i++){
            PQofWindows.add(nums[i]);
        }
        res[resIdx] = PQofWindows.peek();

        while (endIdx < nums.length){
            resIdx++;
            PQofWindows.remove(nums[startIdx]);
            startIdx++;
            endIdx++;
            PQofWindows.add(nums[endIdx-1]);
            res[resIdx] = PQofWindows.peek();
        }
        return res;
    }
    public  static void main(String[] args){
        int[] nums = {1,3,-1};//,-3,5,3,6,7};
        int k = 3;
        int[]res = maxSlidingWindow(nums, k);
        for(int i :res){
            System.out.print(i+" ");
        }
    }
}


class numberComparator implements Comparator<Integer> {
    @Override
    public int compare(Integer a, Integer b) {
        return b-a;
    }
}