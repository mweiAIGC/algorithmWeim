package class01;

import java.util.ArrayList;
import java.util.Stack;
import java.util.List;

/**
 * 用单个栈实现找出一个列表中某字符左边和右边离得最近的比它小的字符,如果字符相同就合并
 * eg；[2,3,4,1]
 * return:[0:[-1,3],1:[0,3],2:[1,3],3:[-1,-1]]
 */
public class Code03_SingleStack {
    public static int[][] findLeastLeftAndRightChar(int[] arr) {
        int[][] ans = new int[arr.length][2];
        Stack<List<Integer>> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            //新入栈的字符比栈顶的字符小
            while (!st.isEmpty() && arr[st.peek().get(0)] > arr[i]) {
                List<Integer> temp = st.pop();
                int leftIndex = st.isEmpty() ? -1 : st.peek().get(st.peek().size() - 1);
                for (int j : temp) {
                    ans[j][0] = leftIndex;
                    ans[j][1] = i;
                }
            }
            if (!st.isEmpty() && st.peek().get(st.peek().size() - 1) == arr[i]) {
                st.peek().add(Integer.valueOf(i));
            } else {
                List<Integer> temp = new ArrayList<>();
                temp.add(i);
                st.push(temp);
            }
        }

        // 栈中剩下的元素，右边没有比它小的
        while (!st.isEmpty()) {
            List<Integer> topList = st.pop();
            int leftIndex = st.isEmpty() ? -1 : st.peek().get(st.peek().size() - 1);
            for (int idx : topList) {
                ans[idx][0] = leftIndex;
                ans[idx][1] = -1; // 没有右边更小的
            }
        }
        return ans;
    }
}
