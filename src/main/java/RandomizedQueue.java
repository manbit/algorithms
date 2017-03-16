import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int count;

    public RandomizedQueue() {
        count = 0;
        queue = (Item[]) new Object[1];
    }                 // construct an empty randomized queue

    public static void main(String[] args) {

    }   // unit testing (optional)

    public boolean isEmpty() {
        return count == 0;
    }                 // is the queue empty?

    public int size() {
        return count;
    }                        // return the number of items on the queue

    public void enqueue(Item item) {
        adjustSize();
        queue[count++] = item;
    }           // add the item

    public Item dequeue() {
        int randomIndex = getRandomIndex();
        Item forRemove = queue[randomIndex];
        count--;
        queue[randomIndex] = queue[count];
        queue[count] = null;
        adjustSize();
        return forRemove;
    }

    private void adjustSize() {
        if (count == 0) {
            return;
        } else if (queue.length == count) {
            resize(queue.length * 2);
        } else if (queue.length / count >= 4) {
            resize(queue.length / 2);
        }
    }

    private void resize(int i) {
        Item[] newOne = (Item[]) new Object[i];
        int index = 0;
        for (Item item : queue) {
            newOne[index++] = item;
        }
        queue = newOne;
    }

    private int getRandomIndex() {
        int randomIndex;
        do {
            randomIndex = StdRandom.uniform(queue.length);
        } while (queue[randomIndex] == null);
        return randomIndex;
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
        return new RandomizeQueueIterator<>();
    }   // return an independent iterator over items in random order

    private class RandomizeQueueIterator<Item> implements Iterator<Item> {
        private Item[] randomCopy;
        private int index;

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
