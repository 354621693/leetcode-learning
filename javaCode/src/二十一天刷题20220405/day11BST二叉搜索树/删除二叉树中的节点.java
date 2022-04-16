package 二十一天刷题20220405.day11BST二叉搜索树;

import tree.TreeNode;

/**
 * 递归
 * 首先根据BST的特性找到要删除的节点node，分三种情况：
 * 1、node没有子树，可以直接删除
 * 2、node只有左树或者只有右树，则直接将不为空的子树父节点替换为node
 * 3、node的左右子树都不为空，这时候就要找一个节点来替换node了，根据BST的特性，容易想到要找的节点就是node的左子树里面最大的节点，或者右子树里面最小的节点。
 * 这时候替换的方式是先拿到该节点的引用，再删了该节点，最后把node的引用指向该节点。
 *
 * 作者：Leemanshow
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst/solution/by-leemanshow-zgrm/
 */

public class 删除二叉树中的节点 {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null){
            return null;
        }
        if(root.val==key){
            if(root.left==null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            //找到左子树的最大子节点或者右子树的最小子节点，这里找左子树的最大子节点
            TreeNode maxChild = root.left;
            while(maxChild.right!=null){
                maxChild = maxChild.right;
            }
            //删除掉该子节点，再把root和子节点对换
            root.left = deleteNode(root.left,maxChild.val);
            maxChild.left = root.left;
            maxChild.right = root.right;
            root = maxChild;
        }
        if(key>root.val){
            root.right = deleteNode(root.right,key);
        }
        if(key<root.val){
            root.left = deleteNode(root.left,key);
        }
        return root;
    }

}
