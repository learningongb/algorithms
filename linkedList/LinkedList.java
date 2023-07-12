public class LinkedList {

    private Node root;
    private int size;

    public void add(int value) {
        if (root == null) {
            root = new Node(value);
            size = 1;
            return;
        }
        Node currentNode = root;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
        }
        currentNode.next = new Node(value);
        size++;
    }

    public void addAt(int index, int value)
    {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        Node newNode = new Node(value);
        size++;
        if (index == 0)
        {
            newNode.next = root;
            root = newNode;
        } else {
            Node currentNode = getNode(index - 1);
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        };
    }

    public int getSize()
    {
        return size;
    }

    public void clear()
    {
        root = null;
        size = 0;
    }

    public void swap(int index1, int index2)
    {
        if (index1 < 0 || index1 >= size)
            return;
        if (index2 < 0 || index2 >= size)
            return;
        Node node1 = getNode(index1);
        Node node2 = getNode(index2);
        int tmp = node1.value;
        node1.value = node2.value;
        node2.value = tmp;
    }

    public void quickSort()
    {
        quickSort(0, size - 1);
    }

    private void quickSort(int leftBorder, int rightBorder)
    {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = getValue((leftMarker + rightMarker) / 2);
        do {
            while (getValue(leftMarker) < pivot)
                leftMarker++;
            while (getValue(rightMarker) > pivot)
                rightMarker--;
            if (leftMarker <= rightMarker)
            {
                if (leftMarker < rightMarker)
                {
                    swap(leftMarker, rightMarker);
                }
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);
        if (leftMarker < rightBorder)
            quickSort(leftMarker, rightBorder);
        if (leftBorder < rightMarker)
            quickSort(leftBorder, rightMarker);
    }

    public void reverse()
    {
        if (root == null || root.next == null)
            return;
        reverse(root);
    }

    private void reverse(Node node)
    {
        if (node.next == null) {
            root = node;
            return;
        }
        Node tmp = node.next;
        reverse(node.next);
        tmp.next = node;
        node.next = null;
    }
    public boolean remove(int value)
    {
        if (root == null)
            return false;
        if (root.value == value) {
            root = root.next;
            size--;
            return true;
        }
        Node currentNode = root;
        while (currentNode.next != null)
        {
            if (currentNode.next.value == value)
            {
            currentNode.next = currentNode.next.next;
            size--;
            return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    public boolean removeAt(int index)
    {
        if (index < 0 || index >= size)
            return false;
        if (index == 0)
        {
            root = root.next;
            size--;
            return true;
        }
        Node currentNode = getNode(index - 1);
        currentNode.next = currentNode.next.next;
        size--;
        return false;
    }

    public int getValue(int index)
    {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node currentNode = getNode(index);
        return currentNode.value;

    }

    private Node getNode(int index)
    {
        if (index < 0 || index >= size)
            return null;
        if (index == 0)
        {
            return root;
        }
        Node currentNode = root;
        for (int i = 0; i < index; i++)
            currentNode = currentNode.next;
        return currentNode;
    }

    public void print()
    {
        Node currentRoot = root;
        while (currentRoot != null)
        {
            System.out.print(currentRoot.value + " ");
            currentRoot = currentRoot.next;
        }
        System.out.println();
    }
    private static class Node {
        int value;
        Node next;

        Node() {}
        Node(int _value) { this.value = _value;}

        Node(int _value, Node _next) {this.value = _value; this.next = _next;}
    }
}
