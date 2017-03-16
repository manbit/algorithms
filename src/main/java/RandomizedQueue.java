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
        if (this.count == queue.length) {
            Item[] resizedQueue = (Item[]) new Object[queue.length * 2];

            for(int i = 0; i < queue.length; i++) {
                resizedQueue[i] = queue[i];
            }

            this.queue = resizedQueue;
        }

        queue[count] = item;

        this.count++;
    }           // add the item

    public Item dequeue() {
        int rand = getRandomIndex();
        Item dequeued = queue[rand];

        this.count--;

        queue[rand] = queue[this.count];
        queue[this.count] = null;

        if (this.queue.length > 4 && this.count <= queue.length / 4) {
            Item [] resizedQueue = (Item[]) new Object[queue.length / 2];

            for(int i = 0; i < this.count; i++) {
                resizedQueue[i] = queue[i];
            }

            this.queue = resizedQueue;
        }

        return dequeued;
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
        return new RandomizeQueueIterator<>(queue, count);
    }   // return an independent iterator over items in random order

    private class RandomizeQueueIterator<Item> implements Iterator<Item> {
        private Item[] iteratorQueue;
        private int iteratorIndex = 0;

        public RandomizeQueueIterator(Item[] queue, int size) {

            iteratorQueue = (Item[]) new Object[size];

            //Copy items into iterator queue
            for(int i = 0; i < iteratorQueue.length; i++) {
                iteratorQueue[i] = queue[i];
            }

            //Knuth shuffle the iterator queue
            for(int j = 1; j < iteratorQueue.length; j++) {
                int swapIndex = StdRandom.uniform(j + 1);

                Item temp = iteratorQueue[j];
                iteratorQueue[j] = iteratorQueue[swapIndex];
                iteratorQueue[swapIndex] = temp;
            }
        }

        @Override
        public boolean hasNext() {
            return (iteratorIndex < iteratorQueue.length);
        }

        @Override
        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException("No more objects to iterate through");
            }

            Item item = iteratorQueue[iteratorIndex];
            iteratorIndex++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove method not supported");
        }
    }

}
