import java.util.HashMap;
import java.util.HashSet;

public class Solution {//Wrong method
    static int subarraysWithKDistinct(int[] nums, int k){
        int res = 0;
        int startBound;
        int endBound = 0;
        HashMap<Integer,Integer> hasBeenAdd = new HashMap<>();

        for (startBound = 0; startBound< nums.length; startBound++){
            if(endBound <= startBound)
                endBound++;

            if(hasBeenAdd.containsKey(nums[startBound])){
                hasBeenAdd.put(nums[startBound],hasBeenAdd.get(nums[startBound])+1);
            }
            else hasBeenAdd.put(nums[startBound],1);
            while (endBound< nums.length && hasBeenAdd.size()<k){
                if(hasBeenAdd.containsKey(nums[endBound])){
                    hasBeenAdd.put(nums[endBound],hasBeenAdd.get(nums[endBound])+1);
                }
                else hasBeenAdd.put(nums[endBound],1);
                endBound++;

            }
            if (hasBeenAdd.size() == k){
                res++;
            }
            if(hasBeenAdd.get(nums[startBound])==1)
                hasBeenAdd.remove(nums[startBound]);
            else
                hasBeenAdd.put(nums[startBound], hasBeenAdd.get(nums[startBound])-1);
        }
        return res;
    }
    public static void main(String[] args){
        int nums[] = {1,2,1,2,3};
        int res = subarraysWithKDistinct(nums, 2);
        System.out.println(res);
    }

}
