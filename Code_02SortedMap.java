package class04;
import java.util.HashMap;
import java.util.TreeMap;
public class Code_02SortedMap {
    public static void main(String[] args){
        TreeMap<Integer, String> tree = new TreeMap<>();
        HashMap<Integer, String> HashMap = new HashMap<>();
        tree.put(1, "a");
        tree.put(2, "b");
//        tree.put(3, "c");
        tree.put(4, "d");
        tree.put(5, "e");
        tree.remove(2);
        tree.get(3);
        System.out.println(tree.ceilingKey(3));
        System.out.println(tree.firstKey());
        System.out.println(tree.lastKey());
//      hashmap 的增删改查 O(1)
//      treemap 的怎删改查 O(log(n))
    }

}
