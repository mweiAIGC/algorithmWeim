package class07;

/**
 * 路径可以从任何结点出发，但必须往下走到到达任意节点，返回最大路径和
 */
public class Code02_MaxSumInTree {
    public static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node(int val){
            this.val = val;
        }
    }
    public static class info{
        public int allNodeSum;
        public int fromHeadMaxSum;
        public info(int allNodeSum, int fromHeadMaxSum){
            this.allNodeSum = allNodeSum;
            this.fromHeadMaxSum = fromHeadMaxSum;
        }
    }
    // 1) node无关的时候 1：左树的整体最大路径和 2：右树的整体最大路径和
    // 2) node有关的时候 3：x自己  4： x左走  5：x右走
    public static info process(Node node){
        if(node == null) return null;
        int p1 = Integer.MIN_VALUE;
        if(node.left != null){
             p1 = process(node.left).allNodeSum;
        }
        int p2 = Integer.MIN_VALUE;
        if(node.right != null){
            p2 = process(node.right).allNodeSum;
        }
        int p3 = node.val;
        int p4 = Integer.MAX_VALUE;
        if(node.left != null){
            p4 = node.val + process(node.left).fromHeadMaxSum;
        }
        int p5 = Integer.MIN_VALUE;
        if(node.right != null){
            p5 = node.val + process(node.left).fromHeadMaxSum;
        }
        int allNodeSum = Math.max(Math.max(Math.max(p1,p2), Math.max(p3,p4)),p5);
        int fromNodeSum = Math.max(Math.max(p3,p4),p5);
        return new info(allNodeSum,fromNodeSum);
    }
}
