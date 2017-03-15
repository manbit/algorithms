import edu.princeton.cs.algs4.In;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);
        In in = new In(args[1]);

        RandomizedQueue<String> deque = new RandomizedQueue<>();
        while (!in.isEmpty()) {
            deque.enqueue(in.readString());
        }
        while (k > 0) {
            System.out.println(deque.dequeue());
            k--;
        }
    }
}
