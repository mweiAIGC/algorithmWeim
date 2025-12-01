package class08;

public class Code02_preAndInArrayToPosArray {
    public static class Node{
        public int val;
        public Node left;
        public Node right;
        public Node(int val){
            this.val = val;
        }
    }

    public static void process(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3){
        if(L1 <  R1){
            return ;
        }
        if(L1 == R1){
            pos[L3] = pre[L1];
            return ;
        }
        pos[R3] = pre[L1];
        int mid = L2;
        for(; mid<=R2;mid++){
            if(pre[L1] == in[mid]){
                break;
            }
        }
        int leftSize = mid - L2;
        process(pre,L1+1,L1+leftSize,in,L2, mid-1,pos,L3,L3+leftSize-1);
        process(pre,L1+leftSize-1,R1,in,mid+1,R2,pos,L3+leftSize,R3);
    }

}
