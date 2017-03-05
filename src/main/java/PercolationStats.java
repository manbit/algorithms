import edu.princeton.cs.algs4.StdStats;

import static edu.princeton.cs.algs4.StdRandom.uniform;

public class PercolationStats {
    private double[] openSites;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        openSites = new double[trials];
        for (int i = 0; i < trials; ++i) {
            Percolation p = new Percolation(n);
            int openSites = 0;
            while (!p.percolates()) {
                int row = uniform(1, n + 1);
                int column = uniform(1, n + 1);

                if (!p.isOpen(row, column)) {
                    ++openSites;
                    p.open(row, column);
                }
            }
            this.openSites[i] = ((double) openSites) / (n * n);
        }
    }    // perform trials independent experiments on an n-by-n grid

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats per = new PercolationStats(n, trials);
        System.out.println("mean                    = " + per.mean());
        System.out.println("stddev                  = " + per.stddev());
        System.out.println("95% confidence interval = " + per.confidenceLo() + ", " + per.confidenceHi());

    }        // test client (described below)

    public double mean() {
        return StdStats.mean(openSites);
    }                          // sample mean of percolation threshold

    public double stddev() {
        return StdStats.stddev(openSites);
    }                        // sample standard deviation of percolation threshold

    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(openSites.length);
    }                  // low  endpoint of 95% confidence interval

    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(openSites.length);
    }                  // high endpoint of 95% confidence interval
}
