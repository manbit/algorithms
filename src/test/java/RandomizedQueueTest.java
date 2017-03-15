public class RandomizedQueueTest {
    public static void main(String... args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(21);
        rq.isEmpty();//     ==> false
        rq.enqueue(7);
        rq.enqueue(4);
        rq.enqueue(43);
        rq.isEmpty();//     ==> false
        System.out.println(rq.dequeue());//     ==> 7
        System.out.println(rq.dequeue());;//     ==> 4
        rq.enqueue(46);
    }
}