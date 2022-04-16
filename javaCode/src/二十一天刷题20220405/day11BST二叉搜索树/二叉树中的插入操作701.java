package 二十一天刷题20220405.day11BST二叉搜索树;

import tree.TreeNode;

/**
 * https://leetcode-cn.com/problems/insert-into-a-binary-search-tree/solution/by-leemanshow-lsdt/
 */
public class 二叉树中的插入操作701 {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null){
            return new TreeNode(val);
        }
        if(val<root.val){
            root.left = insertIntoBST(root.left,val);
        }
        if(val>root.val){
            root.right = insertIntoBST(root.right,val);
        }
        return root;
    }
}
