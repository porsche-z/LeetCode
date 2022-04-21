import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    static boolean wordBreak(String s, List<String> wordDict){
        TrieSet dict = new TrieSet();
        for(String S : wordDict){
            dict.add(S);
        }
        boolean res = dict.containsPart(s,0);
        return res;
    }

    public static void main(String[] args){
        List<String> wordDict = new ArrayList<>();
        /*wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("cat");
        wordDict.add("sand");
        wordDict.add("and");*/
        wordDict.add("a");
        wordDict.add("b");
        boolean res ;
        res=wordBreak("ab", wordDict);
        System.out.println(res);
        res = wordBreak("cat", wordDict);
        System.out.println(res);
    }
}

class TrieSet{
    private Node root;
    private boolean[] containsSinceI = new boolean[301];//memory search-back
    private static class Node{
        private boolean isKey;
        private HashMap<Character, Node> children;

        private Node(boolean isKey){
            this.isKey = isKey;
            this.children = new HashMap<>();
        }
    }

    public TrieSet(){
        this.root = new Node(false);
        for(int i = 0; i<containsSinceI.length;i++){
            containsSinceI[i] = true;
        }
    }

    public void add(String key) {
        Node curNode = root;
        char curChar;
        int i = 0;
        for(i = 0; i<key.length()-1; i++){
            curChar = key.charAt(i);
            if(!curNode.children.containsKey(curChar))
                curNode.children.put(curChar, new Node(false));
            curNode = curNode.children.get(curChar);
        }
        curChar = key.charAt(i);
        if(!curNode.children.containsKey(curChar))
            curNode.children.put(curChar, new Node(true));
        else curNode.children.get(curChar).isKey = true;
    }

    /*public boolean containsPart(String target) {//This method is totaly wrong. This method always match s with the longest word in dictionary. So input dict with a,b,abc,cd and s:abcd will return false
        Node curNode = root;
        char curChar;
        int i;
        for (i = 0; i < target.length() - 1; i++) {
            curChar = target.charAt(i);
            if (curNode.children.containsKey(curChar)) {
                curNode = curNode.children.get(curChar);
            } else {
                if (curNode.isKey) {
                    curNode = root;
                    i--;
                }
                else return false;
            }
        }
        if ((curNode.children.containsKey(target.charAt(i)) && curNode.children.get(target.charAt(i)).isKey) || root.children.containsKey(target.charAt(i)) && root.children.get(target.charAt(i)).isKey) {
            return true;
        }
        else return false;
    }*/
    public boolean containsPart(String target, int start){
        if(start >= target.length())//all the former letters are matched well;
            return true;
        if (!containsSinceI[start])
            return false;

        Node curNode = root;
        char curChar;
        int i;
        for (i = start; i < target.length(); i++) {
            curChar = target.charAt(i);
            if (!curNode.children.containsKey(curChar)) {
                return false;
            }
            curNode = curNode.children.get(curChar);
           if(curNode.isKey){//searching from target[start] to now,find target[start~currentI] can math some words in the dict,
               if(containsPart(target, i+1))//if the rest of target can match some words,we can draw the conclusion that target can divide into some word in dict since start
                   return true;
               containsSinceI[i+1] = false;
           }
        }
        return  false;//from start to the end of target, we can't find a word in dict to mach target[start--length];
    }
}
