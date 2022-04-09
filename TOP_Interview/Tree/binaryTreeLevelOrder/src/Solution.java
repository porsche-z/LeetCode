import java.util.*;

public class Solution {//solution of 102, N-tree level ordered iterate
    static TreeNode generateTree(List<Integer> generateQueue){
        if (generateQueue.get(0) == null)//ATTETION take null-root into consideration!
            return null;
        TreeNode cur = new TreeNode(generateQueue.get(0));
        generateQueue.remove(0);
        TreeNode res = cur;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        boolean flag = false;
        for(Integer i : generateQueue){
            if(i == null){
                flag = !flag;
                continue;
            }
            TreeNode newTreeNode = new TreeNode(i);
            queue.add(newTreeNode);
            if (!flag){
                cur.left = newTreeNode;
            }
            else {
                cur.right = newTreeNode;
                cur = queue.poll();
            }
            flag =!flag;
        }
        return res;
    }

    static List<List<Integer>> readTopToBottom(TreeNode root){
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> res= new ArrayList<List<Integer>>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        //Queue<Node> queueNext = new LinkedList<Node>();
        List<Integer> thisLeve = new ArrayList<>();
        queue.add(root);
        boolean flag = true;
        int sizeOfThisLeveLeft;
        while (!queue.isEmpty()){
            sizeOfThisLeveLeft = queue.size();
            while (sizeOfThisLeveLeft>0){//Nodes in This Level has been added to list;
                TreeNode curNode = queue.poll();
                if(curNode.left != null)
                    queue.add(curNode.left);
                if(curNode.right != null)
                    queue.add(curNode.right);
                sizeOfThisLeveLeft--;
                thisLeve.add(curNode.val);
            }
            if(flag)
                res.add(thisLeve);
            //else
              //  Collections.reverse(thisLeve);
                //res.add(thisLeve);
            //queue = queueNext;
            //queueNext = new LinkedList<Node>();
            thisLeve = new ArrayList<>();
            //flag = !flag;
        }
        return res;
    }

    public static void main(String[] args){
        //int[] input = {3,9,20,0,0,15,7};
        int[] input = {0};

        //int[] input = {0,0,2,3,4,5,0,0,6,7,0,8,0,9,10,0,0,11,0,12,0,13,0,0,14};
        List<Integer> Input = new ArrayList<>();
        for(int i : input){
            if(i == 0){
                Input.add(null);
            }
            else
                Input.add(i);
        }
        TreeNode root = generateTree(Input);
        List<List<Integer>> res = readTopToBottom(root);
        System.out.println(res);

    }

}
class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int _val) { this.val = _val; }
    public TreeNode(int _val, TreeNode _left, TreeNode _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}