



import java.util.*;
public class Solution {
    static List<Integer> preOrderQueue;
    static List<Integer> inOrderList;

    static TreeNode buildTree(int[] preorder, int inorder[]){
        preOrderQueue = new LinkedList<>();
        inOrderList = new ArrayList<>();
        for(int i : preorder){
            preOrderQueue.add(i);
        }
        for (int i : inorder){
            inOrderList.add(i);
        }
        TreeNode root = new TreeNode(preOrderQueue.get(0));
        preOrderQueue.remove(0);
        MybiudTree(-1, preorder.length, root);
        return root;
    }

    static void MybiudTree(int LB, int UB, TreeNode curNode){

        int MB = inOrderList.indexOf(curNode.val);
        //preOrderQueue.indexOf(curVal);
        boolean hasLeft = false;
        boolean hasRight = false;
        int MayLeftVal;
        if(preOrderQueue.size()>0)
            MayLeftVal = preOrderQueue.get(0);
        else
            return;
        //if null?
        int MayLeftIndex = inOrderList.indexOf(MayLeftVal);
        if(MayLeftIndex<MB && MayLeftIndex>LB){
            curNode.left = new TreeNode(MayLeftVal);
            preOrderQueue.remove(0);
            //hasLeft = true;
            MybiudTree(LB, MB, curNode.left);
        }

        int MayRightVal;
        if(preOrderQueue.size()>0)
            MayRightVal = preOrderQueue.get(0);
        else
            return;
        int MayRightIndex = inOrderList.indexOf(MayRightVal);
        if(MayRightIndex < UB && MayRightIndex > MB){
            curNode.right = new TreeNode(MayRightVal);
            preOrderQueue.remove(0);
            //hasRight = true;
            MybiudTree(MB, UB, curNode.right);
        }
        /*if(hasLeft){
            MybiudTree(LB, MB, curNode.left);
        }
        if(hasRight){
            MybiudTree(MB, UB, curNode.right);
        }*/

    }

    static void readTreePre(TreeNode root){
        System.out.print(root.val+" ");
        if(root.left != null)
            readTreePre(root.left);
        if (root.right != null)
            readTreePre(root.right);
    }

    static void readTreeIn(TreeNode root){

        if(root!=null){
            readTreeIn(root.left);
            System.out.print(root.val+" ");
            readTreePre(root.right);
        }
    }


    public static void main(String[] args){
        int[] pre = {3,9,20,15,7};
        int[] in = {9,3,15,20,7};
        //int[] pre = {3,1,2,4};
        //int[] in = {1,2,3,4};
        TreeNode root = buildTree(pre, in);
        readTreePre(root);
        System.out.println(" ");
        readTreeIn(root);
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
