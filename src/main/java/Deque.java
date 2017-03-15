import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head, tail;
    private int size;

    public Deque() {
        head = new Node();
        tail = head;
        head.next = tail;
        tail.privious = head;
    }                           // construct an empty deque

    public static void main(String[] args) {

    }   // unit testing (optional)

    public boolean isEmpty() {
        return size == 0;
    }                 // is the deque empty?

    public int size() {
        return size;
    }                        // return the number of items on the deque

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (head.value != null) {
            Node node = new Node();
            node.value = item;
            node.privious = head;
            head.next = node;
            head = node;
        } else {
            head.value = item;
        }
        size++;
    }          // add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (tail.value != null) {
            Node node = new Node();
            node.value = item;
            node.next = tail;
            tail.privious = node;
            tail = node;
        } else {
            tail.value = item;
        }
        size++;
    }           // add the item to the end

    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Node forRemove = head;
        head = forRemove.privious;
        head.next = null;
        size--;
        Item value = forRemove.value;
        clean(forRemove);
        return value;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node forRemove = tail;
        tail = forRemove.next;
        tail.privious = null;
        size--;
        Item value = forRemove.value;
        clean(forRemove);
        return value;
    }                 // remove and return the item from the end

    private void clean(Node node) {
        node.privious = null;
        node.next = null;
        node.value = null;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator<>();
    }         // return an iterator over items in order from front to end

    private class DequeIterator<Item> implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            this.current = head;
        }

        public boolean hasNext() {
            return current != null && current.next != null;
        }

        public Item next() {
            if (current.value == null) {
                throw new NoSuchElementException();
            }
            Item toRemove = (Item) current.value;
            current = current.next;
            return toRemove;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Node {
        private Item value;
        private Node next, privious;
    }

}
