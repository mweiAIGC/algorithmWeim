package class08;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
public class Code05_WordMinPaths {
    public static List<List<String>> findMinPaths(String start,
                                                  String end,
                                                  List<String> list){
        list.add(start);
        HashMap<String, ArrayList<String>> nexts = getNexts(list);
        HashMap<String, Integer> distances = getDistance(start,nexts);

        LinkedList<String> pathList = new LinkedList<>();
        List<List<String>> res = new ArrayList<>();
        getShortestPaths(start,end,nexts,distances,pathList,res);
        return res;
    }

    public static HashMap<String, ArrayList<String>> getNexts(List<String> words){
        Set<String> dict = new HashSet<>(words);
        HashMap<String, ArrayList<String>> nexts = new HashMap<>();
        for(int i = 0; i< words.size();i++){
            nexts.put(words.get(i),getNext(words.get(i),dict));
        }
        return nexts;
    }

    private static ArrayList<String> getNext(String word, Set<String> dict){
        ArrayList<String> res  = new ArrayList<>();
        char[] chars = word.toCharArray();
        for(char cur = 'a'; cur <='z'; cur++){
            for(int i = 0; i< chars.length; i++){
                if(chars[i] != cur){
                    char temp = chars[i];
                    chars[i] = cur;
                    if(dict.contains(String.valueOf(chars))){
                        res.add(String.valueOf(chars));
                    }
                    chars[i] = temp;
                }
            }
        }
        return res;
    }

    public static HashMap<String, Integer>  getDistance(String start,
                                                        HashMap<String, ArrayList<String>>nexts){
        HashMap<String, Integer> distances = new HashMap<>();
        distances.put(start,0);

        Queue<String> queue  = new LinkedList<>();
        queue.add(start);

        HashSet<String> set = new HashSet<>();
        set.add(start);
        while(!queue.isEmpty()){
            String cur = queue.poll();
            for(String next: nexts.get(cur)){
                if(!set.contains(next)){
                    distances.put(next, distances.get(cur)+1);
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        return distances;
    }

    public static void getShortestPaths(
            String cur, String to,
            HashMap<String, ArrayList<String>> nexts,
            HashMap<String, Integer> distance,
            LinkedList<String> path,
            List<List<String>> res){
        path.add(cur);
        if(to.equals(cur)){
            res.add(new LinkedList<String>(path));
        }else {
            for(String next:nexts.get(cur)){
                if(distance.get(next) == distance.get(cur)+1){
                    getShortestPaths(next,to,nexts,distance,path,res);
                }
            }
        }
        path.pollLast();

    }

    public static void main(String[] args) {
        String start = "abc";
        String end = "cab";
        String[] test = { "abc", "cab", "acc", "cbc", "ccc", "cac", "cbb",
                "aab", "abb" };
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < test.length; i++) {
            list.add(test[i]);
        }
        List<List<String>> res = findMinPaths(start, end, list);
        for (List<String> obj : res) {
            for (String str : obj) {
                System.out.print(str + " -> ");
            }
            System.out.println();
        }

    }

}
