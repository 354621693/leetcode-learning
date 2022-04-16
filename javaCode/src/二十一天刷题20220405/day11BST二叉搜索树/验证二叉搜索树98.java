package 二十一天刷题20220405.day11BST二叉搜索树;

import tree.TreeNode;

/**
 * https://leetcode-cn.com/problems/validate-binary-search-tree/
 */

public class 验证二叉搜索树98 {
    public boolean isValidBST(TreeNode root) {
        return check(root,null,null);
    }

    public boolean check(TreeNode root, TreeNode max,TreeNode min){
        if(root==null){
            return true;
        }
        if(max!=null&&root.val>=max.val){
            return false;
        }
        if(min!=null&&root.val<=min.val){
            return false;
        }
        return check(root.left,root,min)&&check(root.right,max,root);

    }
}
