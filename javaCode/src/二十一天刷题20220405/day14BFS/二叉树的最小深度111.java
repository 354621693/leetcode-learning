package 二十一天刷题20220405.day14BFS;

import tree.TreeNode;

import java.util.LinkedList;

public class 二叉树的最小深度111 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int step = 1;
        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return step;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            step++;
        }
        return step;
    }
}
