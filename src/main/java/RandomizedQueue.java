import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    Item[] queue;
    int capacity;
    int currentIndex;

    public RandomizedQueue() {
        capacity = 0;
        queue = (Item[]) new Object[1];
    }                 // construct an empty randomized queue

    public static void main(String[] args) {

    }   // unit testing (optional)

    public boolean isEmpty() {
        return capacity == 0;
    }                 // is the queue empty?

    public int size() {
        return capacity;
    }                        // return the number of items on the queue

    public void enqueue(Item item) {
        adjustSize();
        queue[currentIndex++] = item;
        capacity++;

    }           // add the item

    private void adjustSize() {
        if (capacity == 0) {
            return;
        }
        if (queue.length == capacity) {
            resize(queue.length * 2);
        } else if (queue.length / capacity >= 4) {
            resize(queue.length / 2);
        }
    }

    private void resize(int i) {
        Item[] newOne = (Item[]) new Object[i];
        int index = 0;
        for (Item item : queue) {
            if (item != null) {
                newOne[index++] = item;
            }
        }
        queue = newOne;
    }

    public Item dequeue() {
        capacity--;
        int index;

        do {
            index = StdRandom.uniform(queue.length - 1);
        } while (queue[index] == null);

        Item forRemove = queue[index];
        queue[index] = null;
        adjustSize();
        return forRemove;
    }
    // remove and return a random item

    public Item sample() {
        int index;
        do {
            index = StdRandom.uniform(queue.length - 1);
        } while (queue[index] != null);

        Item notRemoved = queue[index];
        return notRemoved;
    }                     // return (but do not remove) a random item

    public Iterator<Item> iterator() {
        return new RandomizeQueueIterator<Item>();
    }// return an independent iterator over items in random order

    public class RandomizeQueueIterator<Item> implements Iterator<Item> {
        Item[] randomCopy;
        int index;

        public RandomizeQueueIterator() {
            randomCopy = (Item[]) Arrays.copyOf(queue, queue.length);
            StdRandom.shuffle(randomCopy);
            index = 0;
        }

        public boolean hasNext() {
            return randomCopy.length > index;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return randomCopy[index++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
