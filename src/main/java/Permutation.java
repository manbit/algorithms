import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        String[] strings = StdIn.readAllStrings();

        for(int i = 1; i < strings.length; i++) {
            int randIndex = StdRandom.uniform(i + 1);
            String temp = strings[i];
            strings[i] = strings[randIndex];
            strings[randIndex] = temp;
        }

        //Output
        for(int j = 0; j < k; j++) {
            StdOut.println(strings[j]);
        }

    }
}
