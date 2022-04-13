import java.util.HashMap;
import java.util.HashSet;

public class Solution {
    static int lengthOfLongestSubstring(String s){
        int startBound;
        int endBound = 0;
        int maxSize = 0;
        int curSize;
        HashSet<Character> hasBeenAdd = new HashSet<>();
        for(startBound = 0; startBound < s.length(); startBound++){
            if(endBound<=startBound)
                endBound++;
            hasBeenAdd.add(s.charAt(startBound));
            while(endBound<s.length() && !hasBeenAdd.contains(s.charAt(endBound))){//ATTENTION, the ORDER of compare is esential
                hasBeenAdd.add(s.charAt(endBound));
                endBound++;
            }
            curSize = endBound - startBound;
            if(curSize>maxSize){
                maxSize = curSize;
            }
            if(endBound==s.length())//if end bound has rich the right most of the string,just stop
                return maxSize;
            hasBeenAdd.remove(s.charAt(startBound));
        }
        return maxSize;
    }

    public static void main(String[] args){
        String s = "p";
        //String s = "pwwkew";
        int res = lengthOfLongestSubstring(s);
        System.out.println(res);
    }

}
