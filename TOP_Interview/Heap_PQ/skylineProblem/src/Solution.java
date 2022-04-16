import java.util.*;

public class Solution {
    static List<List<Integer>> getSkyline(int[][] buildings){
        PriorityQueue<int[]> KeyPointsHeap = buildingSolver(buildings);
        PriorityQueue<Integer> heightPriorityQueue = new PriorityQueue<>((a,b)->b-a);
        List<List<Integer>> res = new LinkedList<>();
        int preHeight = 0;
        heightPriorityQueue.add(0);
        while(!KeyPointsHeap.isEmpty()){
            int[] curKeyPoint = KeyPointsHeap.poll();
            if(curKeyPoint[1] >= 0){
                heightPriorityQueue.add(curKeyPoint[1]);
            }
            else {
                heightPriorityQueue.remove(-curKeyPoint[1]);
            }
            int Top = heightPriorityQueue.peek();
            if(Top != preHeight){
                int StartPoint = curKeyPoint[0];
                ArrayList<Integer> subRes = new ArrayList<>();
                subRes.add(StartPoint);
                subRes.add(Top);
                preHeight = Top;

                res.add(subRes);
            }
        }
        return res;
    }
    static PriorityQueue<int[]> buildingSolver(int [][] buildings){
        PriorityQueue<int[]> KeyPointsHeap = new PriorityQueue<>(new keyPointComparator());
        for(int[] B : buildings){
            int[] KeyLeft = {B[0],B[2]};
            int[] KeyRight = {B[1],-B[2]};
            KeyPointsHeap.add(KeyLeft);
            KeyPointsHeap.add(KeyRight);
        }
        return KeyPointsHeap;
    }


    public static void main(String[] args){
       // int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
        int[][] buildings = {{0,2,3},{2,5,3}};
        List<List<Integer>> res = getSkyline(buildings);
        System.out.println(res);
    }
}


class keyPointComparator implements Comparator<int[]>{
    @Override
    public int compare(int[] t1, int[] t2) {
        if(t1[0] != t2[0]){
            return t1[0] - t2[0];
        }
        /*else {
            if(t1[1]>0 && t2[1]>0){
                return t2[1] - t1[1];//if both of the points are left key point, add the lower one first such as[[1,2,1],[1,2,3]]
            }
            else if (t1[1]<0 && t2[1]<0){
                return t2[1] - t1[1];//if both of the points are right key point, remove the lower one first;
            }
            else
                return t2[1] - t1[1];//if one of them is left, the other is right,add the left one(add the next building first)
        }*/
        return t2[1] - t1[1];
    }
}

