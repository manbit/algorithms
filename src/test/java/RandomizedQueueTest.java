public class RandomizedQueueTest {
    public static void main(String... args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.size();  //      ==> 0
        rq.isEmpty(); //     ==> true
        rq.isEmpty();//     ==> true
        rq.isEmpty();//     ==> true
        rq.enqueue(180);
        rq.dequeue();//     ==> 180
        rq.enqueue(8);
    }
}