package tree;

import javaCode.src.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    List<Integer> res = new ArrayList<>();

    List<List<Integer>> gRes = new ArrayList<List<Integer>>();
    public List<Integer> preorderTraversal(TreeNode root) {
        preOrder(root);
        return res;
    }

    /**
     * 前序遍历
     */
    public void preOrder(TreeNode root){
        if(root!=null){
            res.add(root.val);
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder(TreeNode root){
        if(root!=null){
            inOrder(root.left);
            res.add(root.val);
            inOrder(root.right);
        }
    }

    /**
     * 后后序遍历
     */
    public void PostOrder(TreeNode root){
        if(root!=null){
            inOrder(root.left);
            inOrder(root.right);
            res.add(root.val);
        }
    }

    /**
     * 层序遍历（广度优先搜索）
     * https://leetcode-cn.com/leetbook/read/data-structure-binary-tree/xefh1i/
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        dothing(root,0);
        return null;

    }

    private void dothing(TreeNode root,Integer level) {
        if(root!=null){
            List<Integer> temp;
            if(gRes.size()<=level){
                temp = new ArrayList<>();
                gRes.add(level,temp);
            }else {
                temp = gRes.get(level);
            }
            temp.add(root.val);
            dothing(root.left,++level);
            dothing(root.right,level);
        }
    }


    public static void main(String atr[]){
        Solution s = new Solution();
        List<Integer> temp;
        if(s.gRes.size()<=0){
            temp = new ArrayList<>();
            s.gRes.add(0,temp);
        }else {
            temp = s.gRes.get(0);
        }
        return;
    }
}



























