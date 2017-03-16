import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node head, tail;
    private int size;

    public Deque() {
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

        Node newNode = new Node();
        newNode.value = item;

        size++;

        if (size == 1) {
            head = newNode;
            tail = newNode;
        } else {
            Node exHead = head;
            head = newNode;
            head.next = exHead;
            exHead.privious = head;
        }
    }          // add the item to the front

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node newLast = new Node();
        newLast.value = item;

        size++;

        if (size == 1) {
            head = newLast;
            tail = newLast;
        } else {
            Node exLast = tail;
            tail = newLast;
            tail.privious = exLast;
            exLast.next = tail;
        }

    }           // add the item to the end

    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Item value = head.value;
        size--;

        head = head.next;

        if (size == 0) {
            tail = null;
        } else {
            head.privious = null;
        }

        return  value;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item value = tail.value;

        size--;

        tail = tail.privious;

        if (size == 0) {
            head = null;
        } else {
            tail.next = null;
        }

        return value;
    }                 // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeIterator<>();
    }         // return an iterator over items in order from front to end

    private class DequeIterator<Item> implements Iterator<Item> {
        private Node current;

        public DequeIterator() {
            this.current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (current.value == null) {
                throw new NoSuchElementException();
            }
            Item toReturn = (Item) current.value;
            current = current.next;
            return toReturn;
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
