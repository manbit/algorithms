import java.util.Iterator;

import edu.princeton.cs.algs4.In;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);
        In in = new In(args[1]);
        Deque<String> deque = new Deque<String>();
        while (!in.isEmpty()) {
            deque.addLast(in.readString());
        }

        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("Removing ------");

        while (k > 0) {
            System.out.println(deque.removeFirst());
            k--;
        }

        System.out.println("After ------");
        iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
