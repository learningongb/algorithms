public class BinTree {
    Node root;

    private void swapColor(Node node)
    {
        node.color = Color.red;
        node.right.color = Color.black;
        node.left.color = Color.black;
    }

    private Node leftSwap(Node node)
    {
        Node left = node.left;
        Node between = left.right;
        left.right = node;
        node.left = between;
        left.color = node.color;
        node.color = Color.red;
        return left;
    }
    private Node rightSwap(Node node)
    {
        Node right = node.right;
        Node between = right.left;
        right.left = node;
        node.right = between;
        right.color = node.color;
        node.color = Color.red;
        return right;
    }
    private Node rebalance(Node node)
    {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.right != null && result.right.color == Color.red &&
                    (result.left == null || result.left.color == Color.black))
            {
                needRebalance = true;
                result = rightSwap(result);
            }
            if (result.left != null && result.left.color == Color.red &&
            result.left.left != null && result.left.left.color == Color.red)
            {
                needRebalance = true;
                result = leftSwap(result);
            }
            if (result.left != null && result.left.color == Color.red
            && result.right != null && result.right.color == Color.red)
            {
                needRebalance = true;
                swapColor(result);
            }
        } while (needRebalance);
        return result;
    }
    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            root = rebalance(root);
            root.color = Color.black;
            return true;
        }
        boolean added = addNode(root, value);
        root = rebalance((root));
        root.color = Color.black;
        return added;

    }
    private boolean addNode(Node node, int value) {
        if (node.value == value)
            return false;
        if (node.value > value) {
            if (node.left == null) {
                node.left = new Node(value);
                return true;
            } else {
                boolean added = addNode(node.left, value);
                node.left = rebalance(node.left);
                return added;
            }
        } else  {
            if (node.right == null) {
                node.right = new Node(value);
                return true;
            } else {
                boolean added = addNode(node.right, value);
                node.right = rebalance(node.right);
                return added;
            }
        }
    }
    public boolean contain(int value) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.value == value)
                return true;
            if (currentNode.value > value)
                currentNode = currentNode.left;
            else
                currentNode = currentNode.right;
        }
        return false;
    }



    private class Node {
        int value;
        Node left;
        Node right;
        Color color;
        Node() {
            this.color = Color.red;
        }

        Node(int _value) {
            this.value = _value;
            this.color = Color.red;
        }
    }
    enum Color {red, black}
}