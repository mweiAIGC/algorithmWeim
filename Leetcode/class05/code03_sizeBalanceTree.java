package class05;

public class code03_sizeBalanceTree {
    public static class SBTNode<K, V> {
        public K key;
        public V val;
        public SBTNode<K, V> left;
        public SBTNode<K, V> right;
        public int size; // 不同key的数量

        public SBTNode(K key, V val) {
            this.key = key;
            this.val = val;
            size = 1;
        }
    }

    public static class SizeBalanceTreeMap<K extends Comparable<K>, V> {
        private SBTNode<K, V> root;

        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> leftNode = cur.left;
            cur.left = leftNode.right;
            leftNode.right = cur;
            leftNode.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0 )
                    + (cur.right != null ? cur.right.size : 0) + 1;
            return leftNode;
        }

        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> rightNode = cur.right;
            cur.right = rightNode.left;
            rightNode.left = cur;
            rightNode.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0)
                    + (cur.right != null ? cur.right.size : 0) + 1;
            return rightNode;
        }

        public boolean containsKey(K key) {
            if (key == null){
                throw new RuntimeException("invalid parameter");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && key.compareTo(lastNode.key) == 0 ? true : false;
        }

        public void put(K key, V val) {
            if(key == null){
                throw new RuntimeException("invalid parameter");
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            if(lastNode != null && key.compareTo(lastNode.key)==0){
                lastNode.val = val;
            }else {
                root = add(root, key, val);
            }
        }
        public void remove(K key){
            if (key == null){
                throw new RuntimeException("invalid parameter");
            }
            if(containsKey(key)){
                root = delete(root, key);
            }
        }
        private SBTNode<K,V> delete(SBTNode<K, V>cur, K key){
            cur.size--;
            if(key.compareTo(cur.key) > 0){
                cur.right = delete(cur.right, key);
            }else if(key.compareTo(cur.key) < 0){
                cur.left = delete(cur.left, key);
            }else {
                if(cur.left ==null && cur.right ==null){
                    cur = null;
                }else if(cur.left == null && cur.right != null){
                    cur = cur.right;
                }else if (cur.left != null && cur.right == null){
                    cur = cur.left;
                }else {
                    SBTNode<K, V> pre = null;
                    SBTNode<K, V> des = cur.right;
                    des.size--;
                    while(des.left != null){
                        pre = des;
                        des = des.left;
                        des.size --;
                    }
                    if(pre != null){
                        pre.left = des.right;
                        des.right = cur.right;
                    }
                    des.left = cur.left;
                    des.size = des.left.size + (des.right == null ? 0 : des.right.size) + 1;
                    cur = des;
                }
            }
            return cur;
        }


        private SBTNode<K, V> findLastIndex(K key) {
            SBTNode<K, V> pre = root;
            SBTNode<K, V> cur = root;
            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.key) == 0) {
                    break;
                }else if (key.compareTo(cur.key) < 0) {
                    cur = cur.left;
                }else {
                    cur = cur.right;
                }
            }
            return pre;
        }

        private SBTNode<K, V> add(SBTNode<K, V> cur, K key, V val){
            if(cur == null){
                cur = new SBTNode<>(key,val);
            }else {
                cur.size++;
                if(key.compareTo(cur.key)<0){
                    cur.left = add(cur.left, key, val);
                }else {
                    cur.right = add(cur.right, key, val);
                }
            }
            return maintain(cur);
        }

        private SBTNode maintain(SBTNode cur) {
            if (cur == null) {
                return null;
            }
            long leftSize = cur.left != null ? cur.left.size : 0;
            long leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
            long leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
            long rightSize = cur.right != null ? cur.right.size : 0;
            long rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
            long rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
            if (leftLeftSize > rightSize) {
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (leftRightSize > rightSize) {
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (rightRightSize > leftSize) {
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            } else if (rightLeftSize > leftSize) {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
        }
    }

}
