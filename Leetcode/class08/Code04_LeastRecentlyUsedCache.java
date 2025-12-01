package class08;
import java.util.HashMap;
public class Code04_LeastRecentlyUsedCache {
    public static class Node<k,v>{
        public k key;
        public v value;
        public Node<k,v> next;
        public Node<k,v> last;
        public Node(k key, v value){
            this.key = key;
            this.value = value;
        }
    }
    public static class NodeDoubleLinkedList<k,v>{
        public Node<k,v> head;
        public Node<k,v> tail;
        public NodeDoubleLinkedList(){
            head = null;
            tail = null;
        }
        //    如果加入一个新的节点，就放在尾巴上
        public void addNode(Node<k,v> newNode){
            if(newNode == null) return;
            if(head == null){
                head = newNode;
                tail = newNode;
            }else {
                tail.next = newNode;
                newNode.last = tail;
                tail = newNode;
            }
        }
//        双向链表有这个Node，就将该Node前后位置重新链接，并将其移动到tail位置
        public void moveNodeToTail(Node<k,v> node){
            if(this.tail == node) return;
            if(this.head == node){
                this.head = node.next;
                this.head.last = null;
            }else {
                node.next.last = node.last;
                node.last.next = node.next;
            }
            node.last = this.tail;
            node.next = null;
            this.tail.next = node;
            this.tail = node;
        }

//        将头节点删除并返回
        public Node<k,v> removeHead(){
            if(this.head == null) return null;
            Node<k,v> temp = head;
            if(this.head == this.tail){
                this.head = null;
                this.tail = null;
            }else{
                this.head = temp.next;
                this.head.last = null;
                temp.next = null;
            }

            return temp;
        }

    }

    public static class MyCache<k,v>{
        private HashMap<k,Node<k,v>> keyNodeMap;
        private NodeDoubleLinkedList<k,v> nodeList;
        private final int capacity;
        public MyCache(int capacity){
            if(capacity < 1){
                throw new RuntimeException("should be more than 0");
            }
            keyNodeMap = new HashMap<>();
            nodeList = new NodeDoubleLinkedList<>();
            this.capacity = capacity;
        }

        public v get(k key){
            if(keyNodeMap.containsKey(key)){
                Node<k,v> node = keyNodeMap.get(key);
                nodeList.moveNodeToTail(node);
                return node.value;
            }
            return null;
        }

        public void set(k key, v value){
            if(keyNodeMap.containsKey(key)){
                Node<k,v> node = keyNodeMap.get(key);
                node.value = value;
                nodeList.moveNodeToTail(node);
            }else { // 这是新加的一条记录，可能会出现替换
                if(keyNodeMap.size() == capacity){
                    removeMostUnusedCache();
                }
                Node<k,v> newNode = new Node<>(key,value);
                keyNodeMap.put(key,newNode);
                nodeList.addNode(newNode);
            }
        }

        private void removeMostUnusedCache(){
            Node<k,v> head = nodeList.removeHead();
            keyNodeMap.remove(head.key);
        }
    }

}
