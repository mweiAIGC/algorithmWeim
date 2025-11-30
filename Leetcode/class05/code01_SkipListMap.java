package class05;
import java.util.ArrayList;
public class code01_SkipListMap {
    public static class SkipListNode<K extends Comparable<K>, V>{
        public K key;
        public V val;
        public ArrayList<SkipListNode<K,V>> nextNodes;
        public SkipListNode(K k, V v){
            key = k;
            val = v;
            nextNodes = new ArrayList<>();
        }
        public boolean isKeyLess(K otherKey) {
            //  otherKey == null -> false
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }
        public boolean isKeyEqual(K otherKey){
            return(key==null && otherKey==null)||(key != null && otherKey !=null && key.compareTo(otherKey)==0);
        }

    public static class SkipListMap<K extends Comparable<K>, V>{
        private static final double PROBABILITY = 0.5;
        private SkipListNode<K,V> head;
        private int size;
        private int maxLevel;
        public SkipListMap(){
            head = new SkipListNode<>(null,null);
            head.nextNodes.add(head);
            size = 0;
            maxLevel = 0;
        }

        private SkipListNode<K, V> mostRightLessNodeInTree(K key){
            if(key == null) return null;
            int level = maxLevel;
            SkipListNode<K, V> cur = head;
            while(level>=0){
                cur = mostRightLessNodeInLevel(key,cur,level--);
            }
            return cur;
        }
        private SkipListNode<K, V> mostRightLessNodeInLevel(K key, SkipListNode<K,V> cur, int level){
            SkipListNode<K, V> nextNode = cur.nextNodes.get(level);
            while(nextNode != null && nextNode.isKeyLess(key)){
                cur = nextNode;
                nextNode = nextNode.nextNodes.get(level);
            }
            return cur;
        }

        public boolean containsKey(K key){
            if (key == null) return false;
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next!=null && next.isKeyEqual(key);
        }

//      新增、改value
        public void put(K key, V value){
            if(key == null){
                return;
            }
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> find = less.nextNodes.get(0);
            if(find == null && find.isKeyEqual(key)){
                find.val = value;
            }else {
                size++;
                int newNodeLevel = 0;
                while(Math.random() < PROBABILITY){
                    newNodeLevel++;
                }
                while(newNodeLevel>maxLevel){
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode<K,V> newNode = new SkipListNode<>(key,value);
                for (int i = 0; i < newNodeLevel; i++){
                    newNode.nextNodes.add(null);
                }
                int level = maxLevel;
                SkipListNode<K,V> pre = head;
                while(level >=0){
                    pre = mostRightLessNodeInLevel(key,pre,level);
                    if(level <=newNodeLevel){
                        newNode.nextNodes.set(level, pre.nextNodes.get(level));
                        pre.nextNodes.set(level,newNode);
                    }
                    level--;
                }
            }
        }
        public V get(K key){
            if(key == null) return null;
            SkipListNode<K, V> less = mostRightLessNodeInTree(key);
            SkipListNode<K, V> next = less.nextNodes.get(0);
            return next!=null && next.isKeyEqual(key) ? next.val : null;
        }

        public void remove(K key){
            if(containsKey(key)){
                size--;
                int level = maxLevel;
                SkipListNode<K, V> pre = head;
                while(level >= 0){
                    pre = mostRightLessNodeInLevel(key,pre,level);
                    SkipListNode<K, V> next = pre.nextNodes.get(level);
                    if(next != null && next.isKeyEqual(key)){
                        pre.nextNodes.set(level, next.nextNodes.get(level));
                    }
//                    在level层只有一个节点，都是默认节点head
                    if(level !=0 && pre == head && pre.nextNodes.get(level) == null){
                        head.nextNodes.remove(level);
                        maxLevel--;
                    }
                    level--;
                }
            }

        }
    }
    }

}
