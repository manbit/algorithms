
public class DequeTest {
    public static void main(String... args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(0);
        deque.removeFirst();//     ==> 0
        deque.addFirst(2);
        deque.removeFirst();
    }
}