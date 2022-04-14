package 二十一天刷题20220405.day10二叉树;

import tree.TreeNode;

public class 将二叉树转换成链表114 {
    public void flatten(TreeNode root) {
        if(root==null){
            return;
        }
        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        //以root为起点开始循环，即可处理当left为null的情况
        TreeNode p = root;
        while(p.right!=null){
            p = p.right;
        }
        p.right = right;
    }
}
