import edu.princeton.cs.algorithms.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.In;

public class Percolation {

    private int[] openClose;
    private WeightedQuickUnionUF uf;
    private int size;
    private int n;
    private int openedNumber;

    public Percolation(int n) {
        this.n = n;
        size = n * n + 2;
        uf = new WeightedQuickUnionUF(size);
        openClose = new int[size];
//        union first row with virtual top
        for (int i = 1; i <= n; i++) {
            uf.union(0, i);
        }
//        union last row with virtual buttom
        for (int i = size - 2; i >= size - n - 1; i--) {
            uf.union(size - 1, i);
        }
    }                // create n-by-n grid, with all sites blocked

    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int n = in.readInt();         // n-by-n percolation system

        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(n);
        while (!in.isEmpty() || perc.percolates()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
    }   // test client (optional)

    public int yxToId(int y, int x) {
        return (y - 1) * n + x;
    }

    public int getTopIndex(int row, int col) {
        if (row > 1) {
            return yxToId(row - 1, col);
        }
        return -1;
    }

    public int getButtomIndex(int row, int col) {
        if (row < n) {
            return yxToId(row + 1, col);
        }
        return -1;
    }

    public int getLeftIndex(int row, int col) {
        if (col > 1) {
            return yxToId(row, col - 1);
        }
        return -1;
    }

    public int getRightIndex(int row, int col) {
        if (col < n && col > 1) {
            return yxToId(row, col - 1);
        } else if (col < n) {
            return yxToId(row, 2);
        }
        return -1;
    }

    public void open(int row, int col) {
        int index = yxToId(row, col);
        if (isOpen(row, col)) {
            return;
        } else {
            int topIndex = getTopIndex(row, col);
            if (topIndex > -1 && isOpen(topIndex)) {
                uf.union(index, topIndex);
            }
            int buttomIndex = getButtomIndex(row, col);
            if (buttomIndex > -1 && isOpen(buttomIndex)) {
                uf.union(index, buttomIndex);
            }
            int leftIndex = getLeftIndex(row, col);
            if (leftIndex > -1 && isOpen(leftIndex)) {
                uf.union(index, leftIndex);
            }
            int rigthIndex = getRightIndex(row, col);
            if (rigthIndex > -1 && isOpen(rigthIndex)) {
                uf.union(index, rigthIndex);
            }
        }
        openClose[index] = 1;
        openedNumber++;
    }    // open site (row, col) if it is not open already

    public boolean isOpen(int row, int col) {
        return openClose[yxToId(row, col)] == 1;
    }  // is site (row, col) open?

    public boolean isOpen(int index) {
        return openClose[index] == 1;
    }  // is site (row, col) open?

    public boolean isFull(int row, int col) {
        return openClose[yxToId(row, col)] == 0;
    }  // is site (row, col) full?

    public int numberOfOpenSites() {
        return openedNumber;
    }       // number of open sites

    public boolean percolates() {
        return uf.connected(0, size - 1);
    }              // does the system percolate?
}
