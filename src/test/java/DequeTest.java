
public class DequeTest {
    public static void main(String... args) {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(0);
        deque.isEmpty();
        System.out.println(deque.removeLast()); //      ==> 0
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);
        deque.isEmpty();
        deque.isEmpty();
        System.out.println(deque.removeLast());//      ==> 0
    }
}