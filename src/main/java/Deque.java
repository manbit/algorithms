import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    Node head, tail;
    int size;

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
            node.next = head;
            head.privious = node;
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
            node.privious = tail;
            tail.next = node;
            tail = node;
        } else {
            tail.value = item;
        }
        size++;
    }           // add the item to the end

    public Item removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Node forRemove = head;
        head = forRemove.next;
        head.privious = null;
        size--;
        return forRemove.value;
    }                // remove and return the item from the front

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node forRemove = tail;
        tail = forRemove.privious;
        tail.next = null;
        size--;
        return forRemove.value;
    }                 // remove and return the item from the end

    public Iterator<Item> iterator() {
        return new DequeIterator<Item>();
    }         // return an iterator over items in order from front to end

    public class DequeIterator<Item> implements Iterator<Item> {
        Node current;

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
        Item value;
        Node next, privious;
    }

}
