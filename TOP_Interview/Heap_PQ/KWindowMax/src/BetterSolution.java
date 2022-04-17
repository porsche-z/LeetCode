import java.util.*;

public class BetterSolution {
    static int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length-k+1];
        PriorityQueue<PQNode> PQofWindows = new PriorityQueue<PQNode>(new numberComparator2());
        int startIdx = 0;
        int endIdx = startIdx+k;
        int resIdx = 0;
        for (int i = startIdx; i < endIdx; i++){
            PQofWindows.add(new PQNode(nums[i],i));
        }
        res[resIdx] = PQofWindows.peek().val;

        while (endIdx < nums.length){
            resIdx++;
            startIdx++;
            endIdx++;

            while (PQofWindows.size()>0 && PQofWindows.peek().index < startIdx){// we only remove nodes when it affect the peak and then affect the result.
                PQofWindows.poll();
            }
            PQofWindows.add(new PQNode(nums[endIdx-1],endIdx-1));
            res[resIdx] = PQofWindows.peek().val;
        }
        return res;
    }
    public  static void main(String[] args){
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 1;
        int[]res = maxSlidingWindow(nums, k);
        for(int i :res){
            System.out.print(i+" ");
        }
    }
}


class numberComparator2 implements Comparator<PQNode> {
    @Override
    public int compare(PQNode a, PQNode b) {
        return b.val-a.val;
    }
}

class PQNode{
    int val;
    int index;
    PQNode(int val, int index){
        this.val = val;
        this.index = index;
    }
}
