package javaCode.src;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Leemanshow
 * @description ${DESCRIPTION}
 * @date 2020-06-16-16:39
 */
public class TreeNodeCode {

    public static void get(List<List<Integer>> res, TreeNode root, int level) {
        if (root == null) {
            return;
        }

        List<Integer> temp;
        if (res.size () <= level) {
            temp = new ArrayList<Integer> ();
            res.add (temp);
        } else {
            temp = res.get (level);
        }
        temp.add (root.val);
        get (res, root.left, ++level);
        get (res, root.right, level);
    }

    public static void main(String[] args) {
        TreeNodeCode d = new TreeNodeCode ();
        TreeNode a = new TreeNode (1);
        d.levelOrder (a);
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<> ();
        get (res, root, 0);
        return res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
