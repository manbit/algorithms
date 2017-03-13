import edu.princeton.cs.algs4.In;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);
        In in = new In(args[1]);
        Deque<String> deque = new Deque<String>();
        while (!in.isEmpty()) {
            deque.addFirst(in.readString());
        }

        while (k > 0) {
            deque.removeLast();
            k--;
        }
    }
}
