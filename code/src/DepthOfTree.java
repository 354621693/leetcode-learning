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
        return subNode(inorder,0,inorder.length,postorder,0,postorder.length);
    }

    public TreeNode subNode(int[] inorder, int inStart,int inEnd, int[] postorder,int postStart,int postEnd){
        if(postStart>=postEnd){
            return null;
        }
        int temp = postorder[postEnd-1];
        TreeNode tree = new TreeNode(temp);
        int index = findIndex(inorder,temp);
        int leftLength = index-inStart;
        int rightLength = inEnd-index-1;
        tree.left = subNode(inorder,inStart,index,postorder,postStart,postStart+leftLength);
        tree.right = subNode(inorder,index+1,inEnd,postorder,inEnd-1-rightLength,inEnd-1);
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


    public static void main(String[] a){
        DepthOfTree d = new DepthOfTree();
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        TreeNode treeNode = d.buildTree(inorder, postorder);
        return;
    }

}
