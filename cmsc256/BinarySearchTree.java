package cmsc256;

public class BinarySearchTree <E extends Comparable<? super E>> {

    private int size = 0;
    private BinaryNode root = null;

    class BinaryNode {
        protected E value;
        protected BinaryNode right;
        protected BinaryNode left;

        public BinaryNode(E valueIn) {
            value = valueIn;
        }
    }

    private boolean addToParent(BinaryNode parentNode, BinaryNode addNode){
        int compare = parentNode.value.compareTo(addNode.value);
        boolean wasAdded = false;
        // if the parentNode is higher the addNode would go left; however, if the addNode is higher it would go right

        if (compare > 0) {
            // null would be the base case
            // if parent has no left node, add new node as left
            if (parentNode.left == null) {
                parentNode.left = addNode; // fill in the null
                wasAdded = true;
            }
            else {
                // otherwise, add to parentNode's left (recursive) --> would go left until null
                wasAdded = addToParent(parentNode.left, addNode);
            }
        }
        else if (compare < 0) {
            // null would be the base case
            // if parent has no right node, add new node as right
            if (parentNode.right == null) {
                parentNode.right = addNode; // fill in the null
                wasAdded = true;
            }
            else {
                // otherwise, add to parentNode's right (recursive) --> would go right until null
                wasAdded = addToParent(parentNode.right, addNode);
            }
        }
        return wasAdded;
    }

    public boolean add(E inValue) {
        BinaryNode node = new BinaryNode(inValue);
        boolean wasAdded = true;
        // node would be the root, since no value
        if (root == null) {
            root = node;
        }

        else {
            wasAdded = addToParent(root, node);
        }
        if (wasAdded) {
            size++;
        }
        return wasAdded;
    }

    public boolean remove(E removeValue) {

        if (root == null) {
            return false;
        }

        if (root.value.compareTo(removeValue) == 0) {
            if (root.left == null) {
                root = root.right;
            }
            else if (root.right == null) {
                root = root.left;
            }
            else {
                BinaryNode formerRight = root.right;
                root = root.left;
                addToParent(root, formerRight);
            }
            size--;
            return true;
        }
        return removeSubNode(root, removeValue);
    }

    private boolean removeSubNode(BinaryNode parent, E removeValue) {
        int compareParent = parent.value.compareTo(removeValue);
        BinaryNode subTree = null;

        if (compareParent > 0) {
            // smaller than parent
            subTree = parent.left;
        }

            // larger than parent
        else {
            subTree = parent.right;
        }

        if (subTree == null) {
            return false;
        }

        if (subTree.value.compareTo(removeValue) == 0) {
            BinaryNode replacement;
            if (subTree.left == null) {
                replacement = subTree.right;
            }
            else if (subTree.right == null) {
                replacement = subTree.left;
            }
            else {
                BinaryNode formerRight = subTree.right;
                replacement = subTree.left;
                addToParent(replacement, formerRight);
            }

            // smaller than parent, so it replace the parent on the left side
            if (compareParent > 0) {
                parent.left = replacement;
            }
            // larger than parent, so it replace the parent in the right side
            else {
                parent.right = replacement;
            }

            size--;
            return true;
        }
        return removeSubNode(subTree, removeValue);
    }

    public int size() {
        return size;
    }

    public BinaryNode getRoot() {
        return root;
    }

    public void clear() {
        root = null;
        size = 0;
    }

}
