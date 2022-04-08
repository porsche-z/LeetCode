import java.util.*;

public class Solution {//solution of 429, N-tree level ordered iterate
     static Node generateTree(List<Integer> generateQueue){
         if (generateQueue.get(0) == null)//ATTETION take null-root into consideration!
             return null;
         Node cur = new Node(0,new ArrayList<>());
         Node res = cur;
         Queue<Node> queue = new LinkedList<Node>();
         for(Integer i : generateQueue){
             if(i != null){
                 Node newNode = new Node(i,new ArrayList<>());
                 cur.children.add(newNode);
                 queue.add(newNode);
             }
             else cur = queue.poll();
         }
         return res.children.get(0);
    }

    static List<List<Integer>> readTopToBottom(Node root){
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

         List<List<Integer>> res= new ArrayList<List<Integer>>();
        Queue<Node> queue = new LinkedList<Node>();
        //Queue<Node> queueNext = new LinkedList<Node>();
        List<Integer> thisLeve = new ArrayList<>();
        queue.add(root);
        int size = queue.size();
        while (!queue.isEmpty()){
            size = queue.size();
            //while (!queue.isEmpty()){
            while (size>0){//Nodes in This Level has been added to list;
                Node curNode = queue.poll();
                for(Node N : curNode.children){
                    queue.add(N);
                }
                size--;
                thisLeve.add(curNode.val);
            }
            res.add(thisLeve);
            //queue = queueNext;
            //queueNext = new LinkedList<Node>();
            thisLeve = new ArrayList<>();
        }
        return res;
    }
    public static void main(String[] args){
         int[] input = {1,0,2,3,4,5,0,0,6,7,0,8,0,9,10,0,0,11,0,12,0,13,0,0,14};
        //int[] input = {0,0,2,3,4,5,0,0,6,7,0,8,0,9,10,0,0,11,0,12,0,13,0,0,14};
         List<Integer> Input = new ArrayList<>();
         for(int i : input){
             if(i == 0){
                 Input.add(null);
             }
             else
                 Input.add(i);
         }
         Node root = generateTree(Input);
         List<List<Integer>> res = readTopToBottom(root);
         System.out.println(res);

    }
}
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};