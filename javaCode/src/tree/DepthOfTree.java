package tree;

import javaCode.src.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class DepthOfTree {
    int answer = 0;

    /**
     * 自上而下（前序遍历）
     */
    public void getDepth(TreeNode node,int depth){
        if(node.left==null&&node.right==null){
            answer = Math.max(answer,depth);
        }else{
            getDepth(node.left,++depth);
            getDepth(node.right,depth);
        }
    }


    /**
     * 自下而上（类后序遍历）
     */
    public int getDepth(TreeNode node){
        if(node!=null){
            return 1+Math.max(getDepth(node.left),getDepth(node.right));
        }else{
            return 0;
        }
    }

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。
     * https://leetcode-cn.com/problems/symmetric-tree/solution/
     */
    public boolean isSymmetric(TreeNode root) {
        // if(root==null){
        //     return true;
        // }
        // if(root.left==null&&root.right==null){
        //     return true;
        // }
        // if(isSymmetric)
        return desion(root.left,root.right);
    }
    public boolean desion(TreeNode node1,TreeNode node2){
        if((node1==null&&node2==null)){
            return true;
        }
        if(node1!=null && node2!=null && node1.val == node2.val){
            return desion(node1.left,node2.right);
        }
        return false;
    }


    /**
     * 路径总和
     * https://leetcode-cn.com/problems/path-sum/
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null){
            return false;
        }
        if(root.left==null&&root.right==null){
            return targetSum==root.val;
        }
        int temp = targetSum-root.val;
        return hasPathSum(root.left,temp)||hasPathSum(root.right,temp);
    }


    /**
     * 从中序与后序遍历序列构造二叉树
     * https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xo98qt/
     */
    TreeNode tree = new TreeNode(0);
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if(inorder==null||inorder.length<1)
            return null;
        return subNode(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
    }

    public TreeNode subNode(int[] inorder, int inStart,int inEnd, int[] postorder,int postStart,int postEnd){
        if(postStart>postEnd){
            return null;
        }
        int temp = postorder[postEnd];
        TreeNode tree = new TreeNode(temp);
        if(postStart==postEnd){
            return tree;
        }
        int index = findIndex(inorder,temp);
        int leftLength = index-inStart;
        int rightLength = inEnd-index;
        tree.left = subNode(inorder,inStart,index-1,postorder,postStart,postStart+leftLength-1);
        tree.right = subNode(inorder,index+1,inEnd,postorder,postStart+leftLength,postEnd-1);
        return tree;
    }

    public int findIndex(int[] array,int target){
        for(int i=0;i<array.length;i++){
            if(array[i]==target){
                return i;
            }
        }
        return -1;
    }


    /**
     * 从前序与中序遍历序列构造二叉树
     * https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xoei3r/
     */
    Map<Integer,Integer> map = new HashMap<>();
    int pos;
    int[] preorder;
    public TreeNode buildTreePre(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for(int i = 0;i<inorder.length;i++){
            map.put(inorder[i],i);
        }
        return subjob(0,inorder.length-1);
    }

    public TreeNode subjob(int start,int end){
        if(start>end){
            return null;
        }
        Integer root_val = preorder[pos++];
        TreeNode node = new TreeNode(root_val);
        int index = map.get(root_val);

        node.left=subjob(start,index-1);
        node.right =subjob(index+1,end);
        return node;
    }



    public static void main(String[] a){
        DepthOfTree d = new DepthOfTree();
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        int[] preorder = {3,9,20,15,7};
//        TreeNode treeNode = d.buildTree(inorder, postorder);
        TreeNode treeNode = d.buildTreePre(inorder, preorder);
        return;
    }

}
