import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String[] strings = StdIn.readAllStrings();

        for(int i = 0; i < strings.length; i++) {
            int randomIndex = StdRandom.uniform(k);
            String tmp = strings[randomIndex];
            strings[randomIndex] = strings[i];
            strings[i] = tmp;
        }

        for(int y = 0; y < k; y++) {
            StdOut.print(strings[y]);
        }

    }
}
